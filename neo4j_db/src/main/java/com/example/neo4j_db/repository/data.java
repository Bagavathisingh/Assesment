package com.example.neo4j_db.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Node("Record")
public class data {

    @Id
    @GeneratedValue
    private Long id;

    private String sourceFile;

    @CompositeProperty
    private Map<String, String> properties = new LinkedHashMap<>();

    public data() {}

    public data(String sourceFile, Map<String, String> properties) {
        this.sourceFile = sourceFile;
        this.properties = properties;
    }
}