package com.shoutanwq.data.learning.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    private String id;
    @Column
    private String course;
    @Column
    private String image;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String video;
    @Column
    private int validatedIn;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}
