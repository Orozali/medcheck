package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "analysis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalysisCategory {
    @Id
    @SequenceGenerator(name = "analysis_category_gen", sequenceName = "analysis_category_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analysis_category_gen")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "analysisCategory")
    private List<Analysis> analysisList;
}
