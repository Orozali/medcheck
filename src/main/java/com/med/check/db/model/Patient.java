package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    @Id
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq",
                        allocationSize = 1)
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String telNumber;
    @Column(length = 1000000)
    private String image;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = ALL, mappedBy = "patient", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Results> results;
    @OneToMany(cascade = ALL, mappedBy = "patient", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Appointments> appointments;
}
