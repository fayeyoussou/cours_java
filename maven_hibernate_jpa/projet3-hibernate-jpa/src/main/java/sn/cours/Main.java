package sn.cours;

import sn.cours.model.Categorie;
import sn.cours.model.Produit;
import sn.cours.persistence.JPAUtil;
import sn.cours.service.impl.CategorieServiceJPA;
import sn.cours.service.impl.ProduitServiceJPA;

public class Main {

    public static void main(String[] args) {

        CategorieServiceJPA categorieService = new CategorieServiceJPA();
        ProduitServiceJPA   produitService   = new ProduitServiceJPA();

        // --- SAVE ---
        Categorie c1 = categorieService.save(Categorie.builder().code("INFO").libelle("Informatique").build());
        Categorie c2 = categorieService.save(Categorie.builder().code("ELECT").libelle("Electronique").build());
        System.out.println("Catégories créées : " + c1 + " | " + c2);

        // Avec JPA : on passe l'objet Categorie, pas un id brut
        Produit p1 = produitService.save(Produit.builder().code("CLAV01").libelle("Clavier mécanique").categorie(c1).build());
        Produit p2 = produitService.save(Produit.builder().code("SOUD01").libelle("Fer à souder").categorie(c2).build());
        Produit p3 = produitService.save(Produit.builder().code("SOURIS01").libelle("Souris sans fil").categorie(c1).build());
        System.out.println("Produits créés : " + p1 + " | " + p2 + " | " + p3);

        // --- FIND ALL ---
        System.out.println("\n--- Toutes les catégories ---");
        categorieService.findAll().forEach(System.out::println);

        // --- FIND BY ID ---
        categorieService.findById(1L).ifPresent(c -> System.out.println("\nFindById(1) : " + c));

        // --- UPDATE ---
        c1.setLibelle("Informatique & Bureautique");
        categorieService.update(c1);
        System.out.println("Après update : " + categorieService.findById(c1.getId()).orElseThrow());

        // --- JPQL JOIN FETCH : équivalent du JOIN SQL du projet 2 ---
        System.out.println("\n--- Produits avec leur catégorie (JPQL JOIN FETCH) ---");
        produitService.findAllAvecCategorie().forEach(p ->
                System.out.printf("  [%d] %s - %s  (Catégorie : %s)%n",
                        p.getId(),
                        p.getCode(),
                        p.getLibelle(),
                        p.getCategorie().getLibelle())
        );

        // --- DELETE ---
        produitService.delete(p2.getId());
        System.out.println("\nAprès suppression de p2 :");
        produitService.findAll().forEach(System.out::println);

        JPAUtil.close();
    }
}
