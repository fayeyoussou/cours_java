package sn.youdev.hib.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "etudiants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    @Column(name = "matricule", nullable = false, unique = true, length = 20)
    private String matricule;

    @Column(nullable = false, length = 80)
    private String nom;

    @Column(nullable = false, length = 80)
    private String prenom;

    @Column(name = "date_naiss")
    private LocalDate dateNaiss;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classe_code")
    private Classe classe;

    public Etudiant(Long id, String matricule, String nom, String prenom, LocalDate dateNaiss) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
    }
}
