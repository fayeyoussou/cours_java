package sn.l2gl.youssoupha.hibernate_simple.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * MODÈLE — Étudiant.
 *
 * Le matricule sert de clé primaire (clé naturelle), comme le code pour Categorie.
 * La date de création est renseignée automatiquement à l'insertion (@PrePersist)
 * et n'est jamais modifiable ensuite.
 */
@Entity
@Table(name = "etudiants")
@Getter
@Setter
@NoArgsConstructor
public class Etudiant {

    // Clé primaire : matricule (clé naturelle saisie par l'utilisateur)
    @Id
    @Column(length = 20)
    private String matricule;

    @Column(length = 50)
    private String prenom;

    @Column(length = 50)
    private String nom;

    // Date de naissance saisie dans le formulaire
    private LocalDate dateNaissance;

    // Date de création : posée automatiquement, jamais mise à jour
    @Column(updatable = false)
    private LocalDate dateCreation;

    // La classe est stockée par son nom (L1, L2, ...) plutôt que par son ordinal
    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private Classe classe;

    /** Renseigne la date de création juste avant la première insertion en base. */
    @PrePersist
    private void avantInsertion() {
        if(matricule == null) {
            throw new NullPointerException("matricule is null");
        }else {
            matricule = matricule.trim().toUpperCase();
        }
        if (dateCreation == null) {
            dateCreation = LocalDate.now();
        }
    }

    @Override
    public String toString() {
        return matricule + " - " + prenom + " " + nom;
    }
}
