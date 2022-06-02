package com.nickyerlangga.resttemplate.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "LinkAja")
@Data
public class LinkAja {
    @Id
    Long id;
    String avatar;
    String name;

    @Column(name = "created_at")
    String createdAt;
}
