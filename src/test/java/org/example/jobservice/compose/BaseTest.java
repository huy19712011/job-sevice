package org.example.jobservice.compose;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public abstract class BaseTest {

    private static final int MONGO_PORT = 27017;
    private static final String MONGO = "mongo";
    private static final String MONGO_URI_FORMAT = "mongodb://job_user:job_password@%s:%s/job";

    public static final ComposeContainer compose =
            new ComposeContainer(new File("docker-compose.yaml"))
                    .withEnv("HOST_PORT", "0")
                    .withExposedService(MONGO, MONGO_PORT, Wait.forListeningPort());

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry){
        compose.start();
        var host = compose.getServiceHost(MONGO, MONGO_PORT);
        var port = compose.getServicePort(MONGO, MONGO_PORT);
        registry.add("spring.mongodb.uri", () -> String.format(MONGO_URI_FORMAT, host, port));
    }
}
