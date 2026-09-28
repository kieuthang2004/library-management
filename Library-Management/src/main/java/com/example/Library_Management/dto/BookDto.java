package com.example.Library_Management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDto {
    @NotBlank(message = "Tên sách không được để trống")
    private String title;

    @NotBlank(message = "Tác giả không được để trống")
    private String author;

    private String category;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng sách không được nhỏ hơn 0")
    private Integer quantity;
}