package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imageData")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageData {
    @Id
    @SequenceGenerator(name = "image_data_gen", sequenceName = "image_data_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_data_gen")
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata", length = 10000)
    private byte[] imageData;


}
