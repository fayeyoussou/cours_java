package sn.youdev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Etudiant {
    private String matricule;
    private String nom;
    private String module;
    private double note;
    private LocalDate dateSaisie;
}
