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
public class ServiceEntertainment {
    @Id
    @GeneratedValue
    private Long id;
    private String serviceName;
    private float price;

    @JsonIgnore
    @ManyToMany(mappedBy = "serviceEntertainmentList", cascade = CascadeType.ALL)
    private List<SimCard> simCard;
}
