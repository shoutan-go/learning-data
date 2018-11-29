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
@Table(name = "goinfo")
public class GoInfo {
    @Id
    @GeneratedValue
    private String id;
    @Column
    private String rule;
    @Column
    private int boardsize;
    @Column
    private int handicap;
    @Column
    private float komi;
    @Column
    private UUID black;
    @Column
    private UUID white;
    @Column
    private String result;
    @Column
    private Integer goal;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}
