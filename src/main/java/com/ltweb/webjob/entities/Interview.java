package com.ltweb.webjob.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "interview")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL)
    private List<Notification> notifies;

    String link;
    String description;

    @Column(name = "time_start")
    LocalDateTime timeStart;

    @Column(name = "time_finish")
    LocalDateTime timeFinish;

    @Column(name = "is_deleted")
    boolean isDeleted;
}
