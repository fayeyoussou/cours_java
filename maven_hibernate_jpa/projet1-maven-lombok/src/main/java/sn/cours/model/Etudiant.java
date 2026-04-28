package sn.cours.model;

import lombok.*;

// Avec Lombok : tout est généré automatiquement à la compilation
@Data               // génère getters + setters + toString + equals + hashCode
@NoArgsConstructor  // génère le constructeur vide
@AllArgsConstructor // génère le constructeur avec tous les champs
@ToString // genere un ToString directement
public class Etudiant {

    private Long id;
    private String matricule;
    private String nom;
    private String prenom;
    private int age;
}
