package com.med.check.db.model;

import com.med.check.db.model.enums.Days;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    @Id
    @SequenceGenerator(name = "schedule_gen", sequenceName = "schedule_seq",
            allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_gen")
    private Long id;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;
    private int interval;
    @ElementCollection
    private Map<Days, Boolean> repeatDay;
    @ManyToOne(cascade = {REFRESH, PERSIST, MERGE, DETACH})
    @JoinColumn(name = "service_id")
    private Service service;
    @OneToMany(mappedBy = "schedule", orphanRemoval = true)
    private List<ScheduleDateAndTime> scheduleDateAndTimes;
}
