package sn.l2g2.faye.jdbc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "classes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Classe {
    @Id
    @Column(length = 4)
    private String code;
    @Column(length = 50,nullable = false)
    private String nom;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "classe")
    private List<EtudiantHibernate> etudiants;

    public Classe(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }
}
