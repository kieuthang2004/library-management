package com.example.Library_Management.controller;

import com.example.Library_Management.dto.ReaderDto;
import com.example.Library_Management.entity.Reader;
import com.example.Library_Management.service.ReaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping
    public ResponseEntity<List<Reader>> getAllReaders() {
        return ResponseEntity.ok(readerService.getAllReaders());
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@Valid @RequestBody ReaderDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(readerService.createReader(dto));
    }
}