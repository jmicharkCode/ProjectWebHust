package com.ltweb.webjob.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Notification> notifies;

    @Column(name = "degree")
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column(name = "career")
    private String career;
}
