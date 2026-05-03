package sn.l2gl.youssoupha.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "table_vehicule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vehicule {
    public Vehicule(Long id, String immatricule, String marque) {
        this.id = id;
        this.immatricule = immatricule;
        this.marque = marque;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicule")
    private Long id;
    @Column( unique = true, nullable = false , length = 8)
    private String immatricule;
    @Column(length = 50)
    private String marque;
    @OneToMany(mappedBy = "vehicule")
    private List<Moteur> moteurs;
}
