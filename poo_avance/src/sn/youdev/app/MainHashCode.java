package sn.youdev.app;

import sn.youdev.model.equality.Produit;
import sn.youdev.model.equality.ProduitAvecEquals;
import sn.youdev.model.equality.ProduitCorrect;

import java.util.HashMap;
import java.util.HashSet;

public class MainHashCode {
    public static void main(String[] args) {

        // ============================================================
        // DÉMONSTRATION 1 : Sans override (Produit)
        // equals() compare les RÉFÉRENCES → deux objets avec le même
        // code sont considérés DIFFÉRENTS par Java
        // ============================================================
        System.out.println("===== DÉMO 1 : Sans equals() ni hashCode() =====");

        Produit p1 = new Produit("P001", "Stylo", 1.5);
        Produit p2 = new Produit("P001", "Stylo", 1.5); // même code, objet différent

        System.out.println("p1 == p2         : " + (p1 == p2));          // false (références différentes)
        System.out.println("p1.equals(p2)    : " + p1.equals(p2));       // false (Object.equals = ==)
        System.out.println("hashCode p1      : " + p1.hashCode());       // adresse mémoire
        System.out.println("hashCode p2      : " + p2.hashCode());       // adresse mémoire différente

        HashSet<Produit> setProduits = new HashSet<>();
        setProduits.add(p1);
        setProduits.add(p2);
        // On s'attend à 1 seul élément (même code), mais on en a 2
        System.out.println("Taille du Set    : " + setProduits.size());  // 2 → PROBLÈME !

        HashMap<Produit, String> map = new HashMap<>();
        map.put(p1, "premier");
        // On cherche avec un objet au même code → non trouvé !
        System.out.println("Trouvé dans Map  : " + map.get(new Produit("P001", "Stylo", 1.5))); // null → PROBLÈME !


        // ============================================================
        // DÉMONSTRATION 2 : Seulement equals() overridé (ProduitAvecEquals)
        // equals() fonctionne sur le code, MAIS le contrat hashCode est brisé
        // → Les collections basées sur le hachage ne fonctionnent PAS
        // ============================================================
        System.out.println("\n===== DÉMO 2 : Seulement equals() overridé =====");

        ProduitAvecEquals pe1 = new ProduitAvecEquals("P002", "Cahier", 2.0);
        ProduitAvecEquals pe2 = new ProduitAvecEquals("P002", "Cahier", 2.0); // même code

        System.out.println("pe1.equals(pe2)  : " + pe1.equals(pe2));    // true ✓ equals fonctionne
        System.out.println("hashCode pe1     : " + pe1.hashCode());     // basé sur l'adresse
        System.out.println("hashCode pe2     : " + pe2.hashCode());     // DIFFÉRENT → contrat brisé !
        // CONTRAT BRISÉ : equals=true mais hashCode différent !

        HashSet<ProduitAvecEquals> setPE = new HashSet<>();
        setPE.add(pe1);
        setPE.add(pe2);
        // Java utilise hashCode pour choisir le "bucket" → objets dans des buckets différents
        System.out.println("Taille du Set    : " + setPE.size());       // 2 → PROBLÈME malgré equals !

        HashMap<ProduitAvecEquals, String> mapPE = new HashMap<>();
        mapPE.put(pe1, "premier");
        System.out.println("Trouvé dans Map  : " + mapPE.get(new ProduitAvecEquals("P002", "Cahier", 2.0))); // null → PROBLÈME !


        // ============================================================
        // DÉMONSTRATION 3 : equals() ET hashCode() correctement overridés
        // Le contrat est respecté → tout fonctionne parfaitement
        // code est final → hashCode stable même si nom/prix change
        // ============================================================
        System.out.println("\n===== DÉMO 3 : equals() ET hashCode() corrects =====");

        ProduitCorrect pc1 = new ProduitCorrect("P003", "Crayon", 0.5);
        ProduitCorrect pc2 = new ProduitCorrect("P003", "Crayon", 0.5); // même code

        System.out.println("pc1.equals(pc2)  : " + pc1.equals(pc2));   // true ✓
        System.out.println("hashCode pc1     : " + pc1.hashCode());    // identique
        System.out.println("hashCode pc2     : " + pc2.hashCode());    // identique ✓

        HashSet<ProduitCorrect> setPC = new HashSet<>();
        setPC.add(pc1);
        setPC.add(pc2); // même code → refusé par le Set
        System.out.println("Taille du Set    : " + setPC.size());      // 1 ✓

        // HashMap : clé trouvée même avec un nouvel objet
        HashMap<ProduitCorrect, String> mapPC = new HashMap<>();
        mapPC.put(pc1, "premier");
        System.out.println("Trouvé dans Map  : " + mapPC.get(new ProduitCorrect("P003", "Crayon", 0.5))); // "premier" ✓

        // Bonus : modifier nom/prix après insertion → toujours trouvable car code est final
        pc1.setNom("Crayon HB");
        pc1.setPrix(0.8);
        System.out.println("Après modif nom/prix, toujours dans Set : " + setPC.contains(pc1)); // true ✓


        // ============================================================
        // DÉMONSTRATION 4 : Réflexivité, Symétrie, Null-safety
        // Les 5 propriétés contractuelles de equals()
        // ============================================================
        System.out.println("\n===== DÉMO 4 : Propriétés de equals() =====");

        ProduitCorrect a = new ProduitCorrect("P004", "Règle", 1.0);
        ProduitCorrect b = new ProduitCorrect("P004", "Règle", 1.0);
        ProduitCorrect c = new ProduitCorrect("P004", "Règle", 1.0);

        System.out.println("Réflexivité  a.equals(a)         : " + a.equals(a));       // true
        System.out.println("Symétrie     a.equals(b)         : " + a.equals(b));       // true
        System.out.println("Symétrie     b.equals(a)         : " + b.equals(a));       // true (même résultat)
        System.out.println("Transitivité a.equals(b) && b.equals(c) → a.equals(c) : "
                + (a.equals(b) && b.equals(c) && a.equals(c)));                        // true
        System.out.println("Null-safe    a.equals(null)      : " + a.equals(null));    // false (pas d'exception)
        System.out.println("Type diff.   a.equals(\"texte\") : " + a.equals("texte")); // false


        // ============================================================
        // RÉCAPITULATIF VISUEL
        // ============================================================
        System.out.println("\n===== RÉCAPITULATIF =====");
        System.out.println("| Cas                  | equals() | HashSet/HashMap |");
        System.out.println("|----------------------|----------|-----------------|");
        System.out.println("| Aucun override        |    ✗     |       ✗         |");
        System.out.println("| Seulement equals()    |    ✓     |       ✗         |");
        System.out.println("| equals() + hashCode() |    ✓     |       ✓         |");
    }
}
