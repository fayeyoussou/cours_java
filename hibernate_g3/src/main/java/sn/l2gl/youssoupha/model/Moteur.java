package sn.l2gl.youssoupha.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "moteys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Moteur {
    @Id
    private String modele;
    private String nom;
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicule vehicule;
}
