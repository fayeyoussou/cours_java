package sn.youdev.hib.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Classe {

    @Id
    @EqualsAndHashCode.Include
    @Column(length = 20)
    private String code;

    @EqualsAndHashCode.Include
    @Column(nullable = false, unique = true, length = 80)
    private String nom;

    @OneToMany(mappedBy = "classe", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private List<Etudiant> etudiants = new ArrayList<>();

    public Classe(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        etudiants.add(etudiant);
        etudiant.setClasse(this);
    }

    public void retirerEtudiant(Etudiant etudiant) {
        etudiants.remove(etudiant);
        etudiant.setClasse(null);
    }
}
