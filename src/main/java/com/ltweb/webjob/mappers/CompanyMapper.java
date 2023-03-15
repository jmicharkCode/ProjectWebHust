package com.ltweb.webjob.mappers;

import com.ltweb.webjob.dtos.CompanyDto;
import com.ltweb.webjob.dtos.JobDto;
import com.ltweb.webjob.entities.Company;
import com.ltweb.webjob.entities.Job;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {
    public static CompanyDto toDto(Company entity) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(entity.getId());
        companyDto.setUser(UserMapper.toDto(entity.getUser()));
        companyDto.setCompanyWebsite(entity.getCompanyWebsite());
        companyDto.setCompanyName(entity.getCompanyName());
        companyDto.setCompanyImage(entity.getCompanyImage()) ;
        companyDto.setCompanyLogo(entity.getCompanyLogo());
        companyDto.setCompanyDescription(entity.getCompanyDescription());
        return companyDto;
    }
    public static Company toEntity(CompanyDto companyDto) {
        Company entity = new Company();
        entity.setId(companyDto.getId());
        entity.setUser(UserMapper.toEntity(companyDto.getUser()));
        entity.setCompanyWebsite(companyDto.getCompanyWebsite());
        entity.setCompanyName(companyDto.getCompanyName());
        entity.setCompanyImage(companyDto.getCompanyImage()) ;
        entity.setCompanyLogo(companyDto.getCompanyLogo());
        entity.setCompanyDescription(companyDto.getCompanyDescription());
        return entity;
    }

    public static List<CompanyDto> toDtoList(List<Company> entities){
        return entities.stream().map(CompanyMapper::toDto).collect(Collectors.toList());
    }

    public static List<CompanyDto> toDtoList(Iterable<Company> entities) {
        List<CompanyDto> companyDtos = new LinkedList<>();
        entities.forEach(e -> companyDtos.add(toDto(e)));
        return companyDtos;
    }
}
