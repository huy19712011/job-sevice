package org.example.jobservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.jobservice.dto.JobDto;
import org.example.jobservice.service.JobService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("job")
@RequiredArgsConstructor
public class JobController {

    private final JobService service;

    @GetMapping("all")
    public Flux<JobDto> all() {

        return this.service.allJobs();
    }

    @GetMapping("search")
    public Flux<JobDto> search(@RequestParam Set<String> skills) {

        return this.service.jobsBySkillsIn(skills);
    }

    @PostMapping
    public Mono<JobDto> save(@RequestBody Mono<JobDto> mono) {

        return this.service.save(mono);
    }
}
