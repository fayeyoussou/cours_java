package sn.cours.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categorie {

    private Long id;
    private String code;
    private String libelle;
}
