package com.ltweb.webjob.mappers;

import com.ltweb.webjob.dtos.JobApplicationDto;
import com.ltweb.webjob.dtos.NotificationDto;
import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.JobApplication;
import com.ltweb.webjob.entities.Notification;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationMapper {
    public static JobApplicationDto toDto(JobApplication entity) {
        JobApplicationDto jobApplicationDto = new JobApplicationDto();
        jobApplicationDto.setId(entity.getId());
        jobApplicationDto.setInterviewId(entity.getInterviewId());
        jobApplicationDto.setCandidate(CandidateMapper.toDto(entity.getCandidate()));
        jobApplicationDto.setJob(JobMapper.toDto(entity.getJob()));
        jobApplicationDto.setStatus(entity.getStatus());
        jobApplicationDto.setExperience(entity.getExperience());
        jobApplicationDto.setFeedBack(entity.getFeedBack());
        jobApplicationDto.setCv(entity.getCv());

        return jobApplicationDto;
    }
    public static JobApplication toEntity(JobApplicationDto jobDto) {
        JobApplication entity = new JobApplication();
        entity.setId(jobDto.getId());
        entity.setInterviewId(jobDto.getInterviewId());
        entity.setCandidate(CandidateMapper.toEntity(jobDto.getCandidate()));
        entity.setJob(JobMapper.toEntity(jobDto.getJob()));
        entity.setStatus(jobDto.getStatus());
        entity.setExperience(jobDto.getExperience());
        entity.setFeedBack(jobDto.getFeedBack());
        entity.setCv(jobDto.getCv());
        return entity;
    }

    public static List<JobApplicationDto> toDtoList(List<JobApplication> entities){
        return entities.stream().map(JobApplicationMapper::toDto).collect(Collectors.toList());
    }

    public static List<JobApplicationDto> toDtoList(Iterable<JobApplication> entities) {
        List<JobApplicationDto> jobApplicationDto = new LinkedList<>();
        entities.forEach(e -> jobApplicationDto.add(toDto(e)));
        return jobApplicationDto;
    }
}
