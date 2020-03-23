package com.example.keyvalue.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name="keyys")
public class KeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=2, message="Name should have at least 2 characters")
    private String keyName;

    @OneToMany(mappedBy = "key")
    private List<ValueEntity> values;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public List<ValueEntity> getValues() {
        return values;
    }

    public void setValues(List<ValueEntity> values) {
        this.values = values;
    }
}
