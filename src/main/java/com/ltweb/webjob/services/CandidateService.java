package com.ltweb.webjob.services;

import com.ltweb.webjob.dtos.CandidateDto;
import com.ltweb.webjob.dtos.CandidateRequestDto;
import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.FileDB;
import com.ltweb.webjob.entities.User;
import com.ltweb.webjob.exceptions.NotFoundException;
import com.ltweb.webjob.mappers.CandidateMapper;
import com.ltweb.webjob.repositories.CandidateRepository;
import com.ltweb.webjob.repositories.FileDBRepository;
import com.ltweb.webjob.repositories.UserRepository;
import org.hibernate.dialect.PointbaseDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private UserRepository userRepository;


    public List<CandidateDto> getAll(){
        return CandidateMapper.toDtoList(candidateRepository.findAll());
    }

    public CandidateDto getById(int id){
        return  CandidateMapper.toDto(candidateRepository.findById(id).orElseThrow(() -> new NotFoundException("Candidate with id: " + id + " not found"))) ;
    }

    public CandidateDto getByUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow();
        return  CandidateMapper.toDto(candidateRepository.findByUser(user).orElseThrow(() -> new NotFoundException("Candidate with email: " + email + " not found"))) ;
    }

    public List<CandidateDto> getAllBySearchTerm(CandidateRequestDto candidateRequestDto){
        List<Candidate> candidates = new ArrayList<>();
        if(candidateRequestDto.getDegree()!= null) {
            candidates = candidateRepository.findAllBySearchTerm(candidateRequestDto.getCareer(), candidateRequestDto.getDegree());
        }
        else {
            candidates = candidateRepository.findAllBySearchTermNoDegree(candidateRequestDto.getCareer());
        }
        return CandidateMapper.toDtoList(candidates);
    }


    public CandidateDto save(CandidateRequestDto candidateRequestDto, String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));
        Candidate candidate = new Candidate();
        candidate.setCareer(candidateRequestDto.getCareer());
        candidate.setDegree(candidateRequestDto.getDegree());
        candidate.setUser(user);
        return CandidateMapper.toDto(candidateRepository.save(candidate));
    }

    public CandidateDto edit(CandidateRequestDto candidateRequestDto, String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));
        Candidate candidate =candidateRepository.findByUser(user).orElseThrow();
        candidate.setCareer(candidateRequestDto.getCareer());
        candidate.setDegree(candidateRequestDto.getDegree());
        return CandidateMapper.toDto(candidateRepository.save(candidate));
    }
}
