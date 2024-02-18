package com.med.check.db.model;

import com.med.check.db.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo implements UserDetails {
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
    private String resetPasswordToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
