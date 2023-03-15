package com.ltweb.webjob.mappers;

import com.ltweb.webjob.dtos.CandidateDto;
import com.ltweb.webjob.dtos.CompanyDto;
import com.ltweb.webjob.dtos.JobDto;
import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.Company;
import com.ltweb.webjob.entities.Job;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CandidateMapper {
    public static CandidateDto toDto(Candidate entity) {
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setId(entity.getId());
        candidateDto.setUser(UserMapper.toDto(entity.getUser()));
        candidateDto.setDegree(entity.getDegree());
        candidateDto.setCareer(entity.getCareer());
        return candidateDto;
    }

    public static Candidate toEntity(CandidateDto candidateDto) {
        Candidate entity = new Candidate();
        entity.setId(candidateDto.getId());
        entity.setUser(UserMapper.toEntity(candidateDto.getUser()));
        entity.setDegree(candidateDto.getDegree());
        entity.setCareer(candidateDto.getCareer());
        return entity;
    }

    public static List<CandidateDto> toDtoList(List<Candidate> entities){
        return entities.stream().map(CandidateMapper::toDto).collect(Collectors.toList());
    }

    public static List<CandidateDto> toDtoList(Iterable<Candidate> entities) {
        List<CandidateDto> candidateDtos = new LinkedList<>();
        entities.forEach(e -> candidateDtos.add(toDto(e)));
        return candidateDtos;
    }
}

