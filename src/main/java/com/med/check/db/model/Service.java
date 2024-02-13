package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Service {
    @Id
    @SequenceGenerator(name = "service_gen", sequenceName = "service_seq",
            allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_gen")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "service", orphanRemoval = true)
    private List<Notes> notes;
}
