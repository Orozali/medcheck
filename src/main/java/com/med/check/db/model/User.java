package com.med.check.db.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq",
                        allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String telNumber;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
    @OneToMany(cascade = ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Results> results;
    @OneToMany(cascade = ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Notes> notes;
}
