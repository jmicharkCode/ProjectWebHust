package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.Company;
import com.ltweb.webjob.entities.Interview;
import com.ltweb.webjob.entities.StatusNoti;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class NotifyDto {
    int candidateId;
    int companyId;
    String link;
    String description;
    Boolean status;
}
