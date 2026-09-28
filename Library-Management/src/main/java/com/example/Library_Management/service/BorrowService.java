package com.example.Library_Management.service;

import com.example.Library_Management.dto.BorrowRequestDto;
import com.example.Library_Management.entity.Book;
import com.example.Library_Management.entity.BorrowRecord;
import com.example.Library_Management.entity.Reader;
import com.example.Library_Management.repository.BookRepository;
import com.example.Library_Management.repository.BorrowRecordRepository;
import com.example.Library_Management.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    @Transactional
    public BorrowRecord borrowBook(BorrowRequestDto request) {
        // 1. Kiểm tra Độc giả
        Reader reader = readerRepository.findById(request.getReaderId())
                .orElseThrow(() -> new RuntimeException("Độc giả không tồn tại"));

        // 2. Kiểm tra Sách
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Sách không tồn tại"));

        // 3. Kiểm tra Số lượng kho
        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Sách '" + book.getTitle() + "' đã hết trong kho");
        }

        // 4. Giảm số lượng tồn kho đi 1
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // 5. Lưu phiếu mượn
        BorrowRecord record = new BorrowRecord();
        record.setReader(reader);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(request.getBorrowDays()));
        record.setStatus(BorrowRecord.Status.BORROWED);

        return borrowRecordRepository.save(record);
    }

    @Transactional
    public BorrowRecord returnBook(Long recordId) {
        // 1. Tìm phiếu mượn
        BorrowRecord record = borrowRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu mượn ID: " + recordId));

        if (record.getStatus() == BorrowRecord.Status.RETURNED) {
            throw new RuntimeException("Phiếu mượn này đã được trả trước đó rồi!");
        }

        // 2. Cập nhật ngày trả và trạng thái
        record.setReturnDate(LocalDate.now());
        record.setStatus(BorrowRecord.Status.RETURNED);

        // 3. Cộng lại số lượng tồn kho
        Book book = record.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        return borrowRecordRepository.save(record);
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }
}