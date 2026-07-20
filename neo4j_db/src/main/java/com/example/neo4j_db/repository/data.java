package com.example.neo4j_db.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@Node("Food")
public class data {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String quantity;
    private String location;

    public data() {
    }
    public data(String name, String quantity, String location) {
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }
}
