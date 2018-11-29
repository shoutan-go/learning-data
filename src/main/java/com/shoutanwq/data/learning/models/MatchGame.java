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
@Table(name = "matchgame")
public class MatchGame {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String match;
    @Column
    private UUID player;
    @Column
    private String game;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}
