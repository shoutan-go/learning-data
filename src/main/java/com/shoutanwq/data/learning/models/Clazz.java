package com.shoutanwq.data.learning.models;

import com.shoutanwq.data.learning.UuidConverter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class")
public class Clazz {
    @Id
    @GeneratedValue
    private UUID id;
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

