package uz.pdp.cellcompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Turnstile {

    @Id
    @GeneratedValue
    private UUID id;

    @JsonIgnore
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private User user;

}
