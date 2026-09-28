package com.example.Library_Management.service;

import com.example.Library_Management.dto.ReaderDto;
import com.example.Library_Management.entity.Reader;
import com.example.Library_Management.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader createReader(ReaderDto dto) {
        if (readerRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email độc giả đã tồn tại!");
        }
        Reader reader = new Reader();
        reader.setName(dto.getName());
        reader.setEmail(dto.getEmail());
        reader.setPhone(dto.getPhone());
        return readerRepository.save(reader);
    }
}