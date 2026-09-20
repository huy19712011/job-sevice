package org.example.jobservice.service;

import lombok.RequiredArgsConstructor;
import org.example.jobservice.dto.JobDto;
import org.example.jobservice.repository.JobRepository;
import org.example.jobservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository repository;

    public Flux<JobDto> allJobs() {
        return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Flux<JobDto> jobsBySkillsIn(Set<String> skills) {
        return this.repository.findBySkillsIn(skills)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<JobDto> save(Mono<JobDto> mono) {
        return mono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }
}
