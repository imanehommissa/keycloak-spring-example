package com.example.keyvalue.model;


import com.fasterxml.jackson.annotation.JsonView;

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

    @JsonView(Views.Public.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(Views.Public.class)
    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @JsonView(Views.Internal.class)
    public List<ValueEntity> getValues() {
        return values;
    }

    public void setValues(List<ValueEntity> values) {
        this.values = values;
    }

    public static final class Views {
        // show only public data
        public interface Public {}

        // show public and internal data
        public interface Internal extends Public {}
    }
}
