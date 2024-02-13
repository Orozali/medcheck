package com.med.check.db.model;

import com.med.check.db.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    @Id
    @SequenceGenerator(name = "user_info_gen", sequenceName = "user_info_seq",
                        allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_info_gen")
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userInfo",orphanRemoval = true)
    private User user;
}
