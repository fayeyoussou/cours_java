package sn.youdev.model;

import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Etudiant {
    private final String matricule;
    private String prenom;
    private String nom;
    private LocalDate dateNaissace;
}
