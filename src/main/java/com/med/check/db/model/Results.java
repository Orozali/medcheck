package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Results {
    @Id
    @SequenceGenerator(name = "results_gen", sequenceName = "results_seq",
                        allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "results_gen")
    private Long id;
    private String orderNumber;
    private LocalDate dateOfIssue;
    @Column(length = 10000)
    private String resultFile;
    private String answer;
    @ManyToOne(cascade = {REFRESH, PERSIST, MERGE, DETACH})
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
