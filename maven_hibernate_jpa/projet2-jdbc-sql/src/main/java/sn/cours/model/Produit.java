package sn.cours.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produit {

    private Long id;
    private String code;
    private String libelle;
    private Long idCategorie;
}
