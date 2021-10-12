package uz.pdp.cellcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String firstName;

    @Column(unique = true, nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isPhysicalPerson; // true - agar jismoniy shaxs  |  false - yuridik shaxs

    @ManyToMany
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<SimCard> simCard;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Branch branch;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "manager")
    private Branch branchManager;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Turnstile turnstile; // turniket

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }
}
