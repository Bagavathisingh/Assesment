package com.example.neo4j_db.service;

import com.example.neo4j_db.repository.data;
import com.example.neo4j_db.repository.FoodRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class service {

    private static final int BATCH_SIZE = 500;

    private final FoodRepository repository;

    public service(FoodRepository repository) {
        this.repository = repository;
    }

    public Map<String, String> uploadCSV(MultipartFile[] files) {
        Map<String, String> results = new LinkedHashMap<>();
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            try {
                int count = uploadCSV(file);
                results.put(name, count + " rows stored");
            } catch (Exception e) {
                results.put(name, "FAILED: " + e.getMessage());
            }
        }
        return results;
    }

    public List<data> getAllData() {
        return repository.findAll();
    }
}