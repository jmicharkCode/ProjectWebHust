package com.ltweb.webjob.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "user_id")
    private int userId;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    public FileDB(int userId,String name, String type, byte[] data) {
        this.userId=userId;
        this.name = name;
        this.type = type;
        this.data = data;
    }

}
