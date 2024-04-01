package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @SequenceGenerator(name = "doctor_gen", sequenceName = "doctor_seq",
                        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_gen")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(length = 10000)
    private String image;
    private String position;
    private String description;
    private Boolean isActive;
    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Appointments> appointments;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "doctor",orphanRemoval = true)
    private Schedule schedule;
    @ManyToOne(cascade = {REFRESH, PERSIST, MERGE, DETACH})
    @JoinColumn(name = "service_id")
    private Service service;
    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Reviews> reviews;
}
