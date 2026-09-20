package org.example.jobservice.util;

import org.example.jobservice.dto.JobDto;
import org.example.jobservice.entity.Job;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static JobDto toDto(Job job) {

        JobDto dto = new JobDto();
        BeanUtils.copyProperties(job, dto);
        //dto.setHostName(AppUtil.getHostName());

        return dto;
    }

    public static Job toEntity(JobDto dto) {

        Job job = new Job();
        BeanUtils.copyProperties(dto, job);

        return job;
    }
}
