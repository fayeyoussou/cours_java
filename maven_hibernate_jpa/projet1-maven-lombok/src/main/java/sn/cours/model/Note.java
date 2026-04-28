package sn.cours.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    private Long id;
    private Double valeur;
    private Etudiant etudiant; // Lombok gère le getter/setter automatiquement
    private Module module;
}
