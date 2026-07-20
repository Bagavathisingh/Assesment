package com.example.neo4j_db.repository;

import com.example.neo4j_db.repository.data;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FoodRepository extends Neo4jRepository<data, Long> {

}