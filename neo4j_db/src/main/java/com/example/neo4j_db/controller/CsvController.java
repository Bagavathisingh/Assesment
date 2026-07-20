package com.example.neo4j_db.controller;
import com.example.neo4j_db.repository.data;
import com.example.neo4j_db.service.service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
@CrossOrigin(origins = "*")
public class CsvController {

    private final service csvService;

    public CsvController(service csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(
            @RequestParam("file") MultipartFile file) {

        try {

            csvService.uploadCSV(file);

            return ResponseEntity.ok("CSV Uploaded Successfully");

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }
    }

    @GetMapping("/data")
    public List<data> getAllData() {
        return csvService.getAllData();
    }

}