package sn.cours.service.impl;

import sn.cours.db.DatabaseConnection;
import sn.cours.exception.CategorieNotFoundException;
import sn.cours.model.Produit;
import sn.cours.service.CrudService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduitServiceSQL implements CrudService<Produit, Long> {

    @Override
    public Produit save(Produit produit) {
        verifierCategorieExiste(produit.getIdCategorie());

        String sql = "INSERT INTO produit (code, libelle, id_categorie) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produit.getCode());
            stmt.setString(2, produit.getLibelle());
            stmt.setLong(3, produit.getIdCategorie());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                produit.setId(keys.getLong(1));
            }
            return produit;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur save Produit", e);
        }
    }

    @Override
    public Optional<Produit> findById(Long id) {
        String sql = "SELECT * FROM produit WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection()
                .prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findById Produit", e);
        }
    }

    @Override
    public List<Produit> findAll() {
        String sql = "SELECT * FROM produit";
        List<Produit> produits = new ArrayList<>();
        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                produits.add(mapRow(rs));
            }
            return produits;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findAll Produit", e);
        }
    }

    @Override
    public Produit update(Produit produit) {
        verifierCategorieExiste(produit.getIdCategorie());

        String sql = "UPDATE produit SET code = ?, libelle = ?, id_categorie = ? WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection()
                .prepareStatement(sql)) {

            stmt.setString(1, produit.getCode());
            stmt.setString(2, produit.getLibelle());
            stmt.setLong(3, produit.getIdCategorie());
            stmt.setLong(4, produit.getId());
            stmt.executeUpdate();
            return produit;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur update Produit", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM produit WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection()
                .prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur delete Produit", e);
        }
    }

    // Requête JOIN : retrouver les produits avec le libellé de leur catégorie
    public void afficherAvecCategorie() {
        String sql = """
            SELECT p.id, p.code, p.libelle, c.libelle AS categorie
            FROM produit p
            INNER JOIN categorie c ON p.id_categorie = c.id
            ORDER BY c.libelle, p.libelle
        """;
        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Produits avec leur catégorie ---");
            while (rs.next()) {
                System.out.printf("  [%d] %s - %s  (Catégorie : %s)%n",
                        rs.getLong("id"),
                        rs.getString("code"),
                        rs.getString("libelle"),
                        rs.getString("categorie"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur JOIN produit/categorie", e);
        }
    }

    // Vérifie qu'une catégorie existe — lève CategorieNotFoundException sinon
    private void verifierCategorieExiste(Long idCategorie) {
        String sql = "SELECT COUNT(*) FROM categorie WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection()
                .prepareStatement(sql)) {

            stmt.setLong(1, idCategorie);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                throw new CategorieNotFoundException(idCategorie);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur vérification catégorie", e);
        }
    }

    private Produit mapRow(ResultSet rs) throws SQLException {
        return Produit.builder()
                .id(rs.getLong("id"))
                .code(rs.getString("code"))
                .libelle(rs.getString("libelle"))
                .idCategorie(rs.getLong("id_categorie"))
                .build();
    }
}
