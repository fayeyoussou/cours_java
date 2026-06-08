package sn.l2gl.youssoupha.hibernate.model.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * MODÈLE — Produit du stock.
 *
 * Relations :
 *  - Un produit appartient à UNE seule catégorie  (ManyToOne).
 *  - Un produit peut figurer dans PLUSIEURS ventes (ManyToMany), et une vente
 *    contient plusieurs produits. Produit est le côté PROPRIÉTAIRE de cette
 *    relation : c'est lui qui définit la table de jointure « vente_produits ».
 */
@Entity
@Table(name = "produits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    // Clé primaire technique auto-générée
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Code métier unique du produit
    @Column(length = 3, unique = true)
    private String code;

    @Column(length = 200)
    private String libelle;

    // Plusieurs produits -> une catégorie. EAGER : la catégorie est chargée avec le produit.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categorie_code")
    private Categorie categorie;

    // Côté propriétaire de la relation ManyToMany : la table de jointure est décrite ici.
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "vente_produits",
            joinColumns = {@JoinColumn(name = "produit_id")},
            inverseJoinColumns = {@JoinColumn(name = "vente_numero")}
    )
    private List<Vente> ventes = new ArrayList<>();

    // Prix unitaire
    private int pu;

    @Override
    public String toString() {
        return code + " - " + libelle;
    }
}
