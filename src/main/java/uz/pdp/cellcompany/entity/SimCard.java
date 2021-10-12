package uz.pdp.cellcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"country_number", "company_code"})})
public class SimCard {
    @Id
    @GeneratedValue
    private Long id;

    private String countryName = "+998";

    private String companyCode;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServiceEntertainment> serviceEntertainmentList;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    private SimCardPocket scPocket;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    private SimCardTariff scTariff;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @ManyToOne
    private Branch boughtFrom; // sotib olgan filliali
    private Timestamp boughtAt; // qachon sotib olingani

}
