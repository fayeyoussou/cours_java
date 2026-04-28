package sn.cours.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // permet de créer un objet avec le pattern Builder : Module.builder().code("INF201").build()
public class Module {

    private Long id;
    private String code;
    private String libelle;
    private int coefficient;
}
