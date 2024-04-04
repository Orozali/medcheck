package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {
    @Id
    @SequenceGenerator(name = "refresh_token_gen", sequenceName = "refresh_token_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refresh_token_gen")
    private Long id;
    private String token;
    private Instant expiryDate;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userInfo;

}
