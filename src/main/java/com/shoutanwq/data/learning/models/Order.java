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
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private UUID user;
    @Column(name = "class")
    private UUID clazz;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
}
