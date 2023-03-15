package com.ltweb.webjob.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id",  referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "company",  cascade = CascadeType.ALL)
    private List<Job> jobS;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Notification> notifies;

    @Column(name = "company_name")
    String companyName;

    @Column(name = "company_website")
    String companyWebsite;

    @Column(name = "company_logo")
    String companyLogo;

    @Column(name = "company_description")
    String companyDescription;

    @Column(name = "company_image")
    String companyImage;


//    @Column(name = "is_deleted")
//    @NotNull
//    Boolean isDeleted;
}
