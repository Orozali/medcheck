package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;
import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reviews {
    @Id
    @SequenceGenerator(name = "reviews_gen", sequenceName = "reviews_seq",
            allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_gen")
    private Long id;
    private String comment;
    private int grade;
    @ManyToOne(cascade = {REFRESH, PERSIST, MERGE, DETACH})
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne(cascade = {REFRESH, PERSIST, MERGE, DETACH})
    @JoinColumn(name = "user_id")
    private User user;
}
