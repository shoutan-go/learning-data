package com.shoutanwq.data.learning.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String image;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}