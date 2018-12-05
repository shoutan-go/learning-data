package com.shoutanwq.data.learning.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matchinfo")
public class MatchInfo {
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
    private int expiredIn;

    @Column
    private Integer goal;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;
}
