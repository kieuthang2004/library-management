package com.example.Library_Management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "readers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
}