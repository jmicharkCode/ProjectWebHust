package com.ltweb.webjob.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "user")
    private Company company;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "password is required")
    private String password;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "birthdate")
    @NotNull
    private LocalDate birthDate;

    @Column(name = "gender")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "location")
    @NotNull
    private String location;

    @Column(name = "created_time")
    @NotNull
    private LocalDateTime createdTime;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public User(int useId, String email, String password, Role role) {
        this.id = useId;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int userId, String password, String firstName, String lastName, LocalDate birthDate, Gender gender, String email, Boolean isDeleted, String location, Role role) {
        this.id = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.isDeleted = isDeleted;
        this.role = role;
        this.location = location;
    }

    public User( String password, String firstName, String lastName, LocalDate birthDate, Gender gender, String email, Boolean isDeleted, String location, Role role) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.isDeleted = isDeleted;
        this.role = role;
        this.location= location;
    }


    @Override
    public String toString() {
        return "User{" +
                "staffCode='" + id + '\'' +
                ", email='" + email + '\'' +
                ",role='"  +role+ '\''+
                '}';
    }
}
