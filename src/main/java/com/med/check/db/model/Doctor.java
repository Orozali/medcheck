package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @SequenceGenerator(name = "doctor_gen", sequenceName = "doctor_seq",
                        allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_gen")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(length = 10000)
    private String image;
    private String department;
    private String post;
    private String description;
    private Boolean isActive;
    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Notes> notes;
}
