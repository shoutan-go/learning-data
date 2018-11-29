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
@Table(name = "userclaim")
public class UserClaim {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String type;
    @Column
    private String value;
    @Column
    private UUID userId;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}
