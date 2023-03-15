package com.ltweb.webjob.services;

import com.ltweb.webjob.dtos.CompanyDto;
import com.ltweb.webjob.dtos.CompanyRequestDto;
import com.ltweb.webjob.dtos.JobDto;
import com.ltweb.webjob.dtos.JobRequestDto;
import com.ltweb.webjob.entities.Company;
import com.ltweb.webjob.entities.Job;
import com.ltweb.webjob.entities.User;
import com.ltweb.webjob.exceptions.NotFoundException;
import com.ltweb.webjob.mappers.CompanyMapper;
import com.ltweb.webjob.mappers.JobMapper;
import com.ltweb.webjob.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    public List<CompanyDto> getAll(){
        return CompanyMapper.toDtoList(companyRepository.findAll());
    }

    public CompanyDto getById(int id){
        return  CompanyMapper.toDto(companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company with id: " + id + " Not Found"))) ;
    }

    public CompanyDto getByUser(String email){
        User user=userRepository.findByEmail(email).orElseThrow();
        return  CompanyMapper.toDto(companyRepository.findByUser(user).orElseThrow(() -> new NotFoundException("Company with email: " + email + " Not Found"))) ;
    }

    public CompanyDto save(CompanyRequestDto companyRequestDto, String email){
        User user= userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email: " + email + " Not Found"));
        Company company = new Company();
        company.setUser(user);
        company.setCompanyDescription(companyRequestDto.getCompanyDescription());
        company.setCompanyImage(companyRequestDto.getCompanyImage());
        company.setCompanyLogo(companyRequestDto.getCompanyLogo());
        company.setCompanyName(companyRequestDto.getCompanyName());
        company.setCompanyWebsite(companyRequestDto.getCompanyWebsite());
        return CompanyMapper.toDto(companyRepository.save(company));
    }

    public CompanyDto edit(String email, CompanyRequestDto companyRequestDto){
        User user= userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email: " + email + " Not Found"));
        Company company= companyRepository.findByUser(user).orElseThrow();
        company.setCompanyDescription(companyRequestDto.getCompanyDescription());
        company.setCompanyImage(companyRequestDto.getCompanyImage());
        company.setCompanyLogo(companyRequestDto.getCompanyLogo());
        company.setCompanyName(companyRequestDto.getCompanyName());
        company.setCompanyWebsite(companyRequestDto.getCompanyWebsite());
        return CompanyMapper.toDto(companyRepository.save(company));
    }
}
