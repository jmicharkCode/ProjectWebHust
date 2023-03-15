package com.ltweb.webjob.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDto {
    String companyName;

    String companyWebsite;

    String companyLogo;

    String companyDescription;

    String companyImage;
}
