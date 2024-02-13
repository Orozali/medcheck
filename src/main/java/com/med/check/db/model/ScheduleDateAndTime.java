package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "schedule_date_time")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDateAndTime {
    @Id
    @SequenceGenerator(name = "schedule_date_time_gen", sequenceName = "schedule_date_time_seq",
            allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_date_time_gen")
    private Long id;
    private LocalDate timeFrom;
    private LocalDate timeTo;
    private LocalDate date;
    private Boolean isBusy;
    @ManyToOne(cascade = {REFRESH, PERSIST, MERGE, DETACH})
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
