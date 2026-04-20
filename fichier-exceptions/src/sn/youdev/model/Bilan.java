package sn.youdev.model;

import java.util.List;

// Résultat d'un import CSV : lignes acceptées, rejetées et messages d'erreur
public record Bilan(int ok, int ko, List<String> logs) {

    public void afficher() {
        System.out.println("Import terminé → " + ok + " OK, " + ko + " KO");
        logs.forEach(l -> System.err.println("  ! " + l));
    }
}
