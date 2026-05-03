package sn.l2g2.faye.jdbc.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "etudiants")
public class EtudiantHibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    @Column(name = "mon_matricule",unique = true,length = 5)
    private String matricule;
    @Column(length = 60)
    private String nom;
    @Column(length = 200)
    private String prenom;
    @Temporal(TemporalType.DATE)
    private LocalDate dateNaiss;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Classe classe;

}
