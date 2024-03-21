package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "analysis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Analysis {
    @Id
    @SequenceGenerator(name = "analysis_gen", sequenceName = "analysis_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analysis_gen")
    private Long id;
    private String name;
    private int price;
    private String material;
    private String conditionPreparingForAnalysis;
    private String materialCollectionTime;
    private String timeIssuingResults;

    @ManyToOne(cascade = {REFRESH, PERSIST, MERGE, DETACH})
    private AnalysisCategory analysisCategory;
}
