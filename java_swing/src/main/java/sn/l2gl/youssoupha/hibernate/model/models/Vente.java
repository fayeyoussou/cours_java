package sn.l2gl.youssoupha.hibernate.model.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * MODÈLE — Vente (ticket / facture) identifiée par un numéro saisi par l'utilisateur.
 *
 * Côté INVERSE de la relation ManyToMany avec Produit : « mappedBy = "ventes" »
 * indique que la table de jointure est gérée par la classe Produit (propriétaire).
 */
@Entity
@Table(name = "ventes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vente {

    // Clé primaire : numéro de la vente
    @Id
    @Column(length = 8, unique = true)
    private String numero;

    // Montant total de la vente
    private int montant;

    // Date et heure de la vente
    private LocalDateTime date;

    // Côté inverse de la relation ManyToMany (lecture). Le lien est persisté via Produit.
    @ManyToMany(mappedBy = "ventes", fetch = FetchType.LAZY)
    private List<Produit> produits = new ArrayList<>();

    @Override
    public String toString() {
        return numero + " (" + montant + ")";
    }
}
