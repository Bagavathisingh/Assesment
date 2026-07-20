package com.example.neo4j_db.controller;
import com.example.neo4j_db.repository.data;
import com.example.neo4j_db.service.service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/csv")
@CrossOrigin(origins = "*")
public class CsvController {

    private final service csvService;

    public CsvController(service csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            int count = csvService.uploadCSV(file);
            return ResponseEntity.ok(count + " rows stored in Neo4j");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<Map<String, String>> uploadMultipleCSV(
            @RequestParam("files") MultipartFile[] files) {

        if (files == null || files.length == 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "No files were provided"));
        }

        return ResponseEntity.ok(csvService.uploadCSV(files));
    }

    @GetMapping("/data")
    public List<data> getAllData() {
        return csvService.getAllData();
    }

}