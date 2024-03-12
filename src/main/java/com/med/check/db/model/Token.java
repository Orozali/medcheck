package com.med.check.db.model;

import com.med.check.db.model.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    @Id
    @SequenceGenerator(name = "token_gen", sequenceName = "token_seq"
            ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_gen")
    private Long id;
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;
    private boolean expired;
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
