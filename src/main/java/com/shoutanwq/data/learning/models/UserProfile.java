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
@Table(name = "userprofile")
public class UserProfile {
    @Id
    @GeneratedValue
    private UUID userId;
    @Column
    private String displayName;
    @Column
    private String picture;
    @Column
    private int gender;
    @Column
    private String location;
    @Column
    private String website;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}
