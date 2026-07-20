package com.example.neo4j_db.service;

import com.example.neo4j_db.repository.data;
import com.example.neo4j_db.repository.FoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class service {

    private final FoodRepository repository;

    public service(FoodRepository repository) {
        this.repository = repository;
    }

    public void uploadCSV(MultipartFile file) throws Exception {

        List<data> batch = new ArrayList<>();
        int batchSize = 500;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            reader.readLine(); // skip header

            String line;
            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) continue;

                String[] cols = line.split(",", -1);
                if (cols.length < 3) continue; // or throw, depending on how strict you want to be

                data node = new data();
                node.setName(cols[0].trim());
                node.setQuantity(cols[1].trim());
                node.setLocation(cols[2].trim());

                batch.add(node);

                if (batch.size() >= batchSize) {
                    repository.saveAll(batch);
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                repository.saveAll(batch);
            }
        }
    }

    public List<data> getAllData() {
        return repository.findAll();
    }

}