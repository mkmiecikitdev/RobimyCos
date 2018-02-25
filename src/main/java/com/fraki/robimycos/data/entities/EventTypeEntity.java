package com.fraki.robimycos.data.entities;

import javax.persistence.*;

/**
 * Created by bambo on 09.10.2017.
 */

@Entity
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
