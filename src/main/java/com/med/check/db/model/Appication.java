package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "application")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appication {
    @Id
    @SequenceGenerator(name = "doctor_gen", sequenceName = "doctor_seq",
            allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_gen")
    private Long id;
    private String name;
    private String phoneNumber;
    private LocalDate date;
    private Boolean isProcessed;
}
