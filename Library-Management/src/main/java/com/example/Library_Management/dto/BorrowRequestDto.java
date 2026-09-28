package com.example.Library_Management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BorrowRequestDto {
    @NotNull(message = "Reader ID không được để trống")
    private Long readerId;

    @NotNull(message = "Book ID không được để trống")
    private Long bookId;

    private Integer borrowDays = 14; // Mặc định cho mượn 14 ngày
}