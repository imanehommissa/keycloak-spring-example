package com.example.keyvalue.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name="valuues")
public class ValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private KeyEntity key;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public KeyEntity getKey() {
        return key;
    }

    public void setKey(KeyEntity key) {
        this.key = key;
    }
}
