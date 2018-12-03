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
@Table(name = "class")
public class Clazz {
    @Id
    private String id;
    @Column
    private String teacher;
    @Column
    private String course;
    @Column
    private Date beginAt;
    @Column
    private int price;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}

