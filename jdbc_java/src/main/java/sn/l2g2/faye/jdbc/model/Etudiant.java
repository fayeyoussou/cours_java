package sn.l2g2.faye.jdbc.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Etudiant {
    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
}
