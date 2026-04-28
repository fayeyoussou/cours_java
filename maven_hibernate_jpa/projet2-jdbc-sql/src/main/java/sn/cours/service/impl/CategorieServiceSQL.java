package sn.cours.service.impl;

import sn.cours.db.DatabaseConnection;
import sn.cours.model.Categorie;
import sn.cours.service.CrudService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategorieServiceSQL implements CrudService<Categorie, Long> {

    @Override
    public Categorie save(Categorie categorie) {
        String sql = "INSERT INTO categorie (code, libelle) VALUES (?, ?)";
        try (PreparedStatement stmt = DatabaseConnection.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, categorie.getCode());
            stmt.setString(2, categorie.getLibelle());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                categorie.setId(keys.getLong(1));
            }
            return categorie;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur save Categorie", e);
        }
    }

    @Override
    public Optional<Categorie> findById(Long id) {
        String sql = "SELECT * FROM categorie WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection()
                .prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findById Categorie", e);
        }
    }

    @Override
    public List<Categorie> findAll() {
        String sql = "SELECT * FROM categorie";
        List<Categorie> categories = new ArrayList<>();
        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categories.add(mapRow(rs));
            }
            return categories;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findAll Categorie", e);
        }
    }

    @Override
    public Categorie update(Categorie categorie) {
        String sql = "UPDATE categorie SET code = ?, libelle = ? WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection()
                .prepareStatement(sql)) {

            stmt.setString(1, categorie.getCode());
            stmt.setString(2, categorie.getLibelle());
            stmt.setLong(3, categorie.getId());
            stmt.executeUpdate();
            return categorie;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur update Categorie", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM categorie WHERE id = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection()
                .prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur delete Categorie", e);
        }
    }

    private Categorie mapRow(ResultSet rs) throws SQLException {
        return Categorie.builder()
                .id(rs.getLong("id"))
                .code(rs.getString("code"))
                .libelle(rs.getString("libelle"))
                .build();
    }
}
