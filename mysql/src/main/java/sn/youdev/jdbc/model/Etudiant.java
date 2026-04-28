package sn.youdev.jdbc.model;


import lombok.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Etudiant {
    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Include
    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
}
