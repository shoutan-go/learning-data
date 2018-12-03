package com.shoutanwq.data.learning.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gomove")
public class GoMove {
    @Id
    @GeneratedValue
    private String id;

    @Column
    private String move;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;
}
