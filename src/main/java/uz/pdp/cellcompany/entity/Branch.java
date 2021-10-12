package uz.pdp.cellcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Branch {

    @Id
    @GeneratedValue
    private Long id;

    private String region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
    private List<User> user;  // employee

    @OneToOne(fetch = FetchType.LAZY)
    private User manager;

    @JsonIgnore
    @OneToMany(mappedBy = "boughtFrom", cascade = CascadeType.ALL)
    private List<SimCard> simCard;
}
