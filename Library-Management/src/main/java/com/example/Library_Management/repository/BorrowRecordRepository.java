package com.example.Library_Management.repository;

import com.example.Library_Management.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByReaderId(Long readerId);
    List<BorrowRecord> findByStatus(BorrowRecord.Status status);
}