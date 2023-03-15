package com.ltweb.webjob.mappers;

import com.ltweb.webjob.dtos.InterviewDto;
import com.ltweb.webjob.dtos.JobApplicationDto;
import com.ltweb.webjob.entities.Interview;
import com.ltweb.webjob.entities.JobApplication;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InterviewMapper {
    public static InterviewDto toDto(Interview entity) {
        InterviewDto interviewDto = new InterviewDto();
        interviewDto.setId(entity.getId());
        interviewDto.setJob(JobMapper.toDto(entity.getJob()));
        interviewDto.setTimeFinish(entity.getTimeFinish());
        interviewDto.setTimeStart(entity.getTimeStart());
        interviewDto.setDescription(entity.getDescription());
        interviewDto.setLink(entity.getLink());
        return interviewDto;
    }
    public static Interview toEntity(InterviewDto interviewDto) {
        Interview entity = new Interview();
        entity.setId(interviewDto.getId());
        entity.setTimeFinish(interviewDto.getTimeFinish());
        entity.setTimeStart(interviewDto.getTimeStart());
        entity.setJob(JobMapper.toEntity(interviewDto.getJob()));
        entity.setDescription(interviewDto.getDescription());
        entity.setLink(interviewDto.getLink());
        return entity;
    }

    public static List<InterviewDto> toDtoList(List<Interview> entities){
        return entities.stream().map(InterviewMapper::toDto).collect(Collectors.toList());
    }

    public static List<InterviewDto> toDtoList(Iterable<Interview> entities) {
        List<InterviewDto> interviewDto = new LinkedList<>();
        entities.forEach(e -> interviewDto.add(toDto(e)));
        return interviewDto;
    }
}
