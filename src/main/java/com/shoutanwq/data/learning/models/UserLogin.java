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
@Table(name = "userlogin")
public class UserLogin {

    @Column
    private String name;
    @Column
    private String key;
    @Column
    @Id
    private UUID userId;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}
