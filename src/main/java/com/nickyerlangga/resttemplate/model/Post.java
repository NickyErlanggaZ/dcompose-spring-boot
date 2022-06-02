package com.nickyerlangga.resttemplate.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "posts")
@Data
public class Post implements Serializable {
    @Id
    Long id;

    @Column(name = "user_id")
    Long userId;

    String title;

    String body;
}
