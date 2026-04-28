package sn.cours;

import sn.cours.db.SchemaInitializer;
import sn.cours.exception.CategorieNotFoundException;
import sn.cours.model.Categorie;
import sn.cours.model.Produit;
import sn.cours.service.impl.CategorieServiceSQL;
import sn.cours.service.impl.ProduitServiceSQL;

public class Main {

    public static void main(String[] args) throws Exception {

        // Création des tables (DDL SQL pur)
        SchemaInitializer.init();

        CategorieServiceSQL categorieService = new CategorieServiceSQL();
        ProduitServiceSQL   produitService   = new ProduitServiceSQL();

        // --- INSERT ---
        Categorie c1 = categorieService.save(Categorie.builder().code("INFO").libelle("Informatique").build());
        Categorie c2 = categorieService.save(Categorie.builder().code("ELECT").libelle("Electronique").build());
        System.out.println("Catégories créées : " + c1 + " | " + c2);

        Produit p1 = produitService.save(Produit.builder().code("CLAV01").libelle("Clavier mécanique").idCategorie(c1.getId()).build());
        Produit p2 = produitService.save(Produit.builder().code("SOUD01").libelle("Fer à souder").idCategorie(c2.getId()).build());
        Produit p3 = produitService.save(Produit.builder().code("SOURIS01").libelle("Souris sans fil").idCategorie(c1.getId()).build());
        System.out.println("Produits créés : " + p1 + " | " + p2 + " | " + p3);

        // --- SELECT ALL ---
        System.out.println("\n--- Toutes les catégories ---");
        categorieService.findAll().forEach(System.out::println);

        // --- FIND BY ID ---
        categorieService.findById(1L).ifPresent(c -> System.out.println("\nFindById(1) : " + c));

        // --- UPDATE ---
        c1.setLibelle("Informatique & Bureautique");
        categorieService.update(c1);
        System.out.println("Après update : " + categorieService.findById(c1.getId()).orElseThrow());

        // --- JOIN SQL ---
        produitService.afficherAvecCategorie();

        // --- DELETE ---
        produitService.delete(p2.getId());
        System.out.println("\nAprès suppression de p2 :");
        produitService.findAll().forEach(System.out::println);

        // --- DEMO : catégorie inexistante → CategorieNotFoundException ---
        System.out.println("\n--- Test exception métier ---");
        try {
            produitService.save(Produit.builder()
                    .code("TEST99")
                    .libelle("Produit fantôme")
                    .idCategorie(999L) // catégorie inexistante
                    .build());
        } catch (CategorieNotFoundException e) {
            System.out.println("Erreur interceptée : " + e.getMessage());
        }
    }
}
