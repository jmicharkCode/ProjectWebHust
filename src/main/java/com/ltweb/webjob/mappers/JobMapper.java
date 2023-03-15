package com.ltweb.webjob.mappers;

import com.ltweb.webjob.dtos.JobDto;
import com.ltweb.webjob.dtos.UserDto;
import com.ltweb.webjob.entities.Job;
import com.ltweb.webjob.entities.User;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JobMapper {

    public static JobDto toDto(Job entity) {
        JobDto jobDto = new JobDto();
        jobDto.setId(entity.getId());
        jobDto.setCompany(CompanyMapper.toDto(entity.getCompany()));
        jobDto.setJobName(entity.getJobName());
        jobDto.setCareer(entity.getCareer());
        jobDto.setGender(entity.getGender());
        jobDto.setAddress(entity.getAddress());
        jobDto.setDegree(entity.getDegree());
        jobDto.setJobDescription(entity.getJobDescription());
        jobDto.setSalary(entity.getSalary());
        jobDto.setDeadline(entity.getDeadline());
        jobDto.setAge(entity.getAge());
        jobDto.setExperience(entity.getExperience());
        jobDto.setLevel(entity.getLevel());
        jobDto.setDegree(entity.getDegree());
        jobDto.setImage(entity.getImage());
        jobDto.setCreatedTime(entity.getCreatedTime());
        jobDto.setStatus(entity.getStatus());
        return jobDto;
    }
    public static Job toEntity(JobDto jobDto) {
        Job entity = new Job();
        entity.setId(jobDto.getId());
        entity.setCompany(CompanyMapper.toEntity(jobDto.getCompany()));
        entity.setJobName(jobDto.getJobName());
        entity.setCareer(jobDto.getCareer());
        entity.setDegree(jobDto.getDegree());
        entity.setGender(jobDto.getGender());
        entity.setSalary(jobDto.getSalary());
        entity.setJobDescription(jobDto.getJobDescription());
        entity.setAddress(jobDto.getAddress());
        entity.setDeadline(jobDto.getDeadline());
        entity.setAge(jobDto.getAge());
        entity.setExperience(jobDto.getExperience());
        entity.setLevel(jobDto.getLevel());
        entity.setDegree(jobDto.getDegree());
        entity.setImage(jobDto.getImage());
        entity.setCreatedTime(jobDto.getCreatedTime());
        entity.setStatus(jobDto.getStatus());

        return entity;
    }

    public static List<JobDto> toDtoList(List<Job> entities){
        return entities.stream().map(JobMapper::toDto).collect(Collectors.toList());
    }

    public static List<JobDto> toDtoList(Iterable<Job> entities) {
        List<JobDto> jobDtos = new LinkedList<>();
        entities.forEach(e -> jobDtos.add(toDto(e)));
        return jobDtos;
    }

}
