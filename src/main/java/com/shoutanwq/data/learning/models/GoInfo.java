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
@Table(name = "goinfo")
public class GoInfo {
    @Id
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
    private String black;

    @Column
    private String white;

    @Column
    private String result;

    @Column
    private Integer goal;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;
}
