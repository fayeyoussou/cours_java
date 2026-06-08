package sn.l2gl.youssoupha.hibernate.model.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MODÈLE — Catégorie d'un produit (ex. « Boissons », « Informatique »).
 * La clé primaire est un code saisi par l'utilisateur (et non auto-généré).
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {

    // Clé primaire : code unique saisi par l'utilisateur
    @Id
    @Column(length = 200, unique = true)
    private String code;

    // Libellé lisible de la catégorie
    private String libelle;

    // Affichage pratique dans les listes / combos de l'interface
    @Override
    public String toString() {
        return code + " - " + libelle;
    }
}
