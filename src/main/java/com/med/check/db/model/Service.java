package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import javax.print.Doc;
import java.util.ArrayList;
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
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_gen")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "service", orphanRemoval = true)
    private List<Appointments> appointments;
    @OneToMany(mappedBy = "service")
    private List<Doctor> doctors;

    public void addDoctor(Doctor doctor){
        if (doctors == null){
            doctors = new ArrayList<>();
            doctors.add(doctor);
        }
        doctors.add(doctor);
    }
}
