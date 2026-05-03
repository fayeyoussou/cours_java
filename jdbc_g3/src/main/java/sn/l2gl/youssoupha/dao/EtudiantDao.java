package sn.l2gl.youssoupha.dao;

import sn.l2gl.youssoupha.ConnexionTest;
import sn.l2gl.youssoupha.model.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EtudiantDao {
    public Long inserer(Etudiant e) throws SQLException {
        String sql = "INSERT INTO etudiant(matricule, nom, prenom, date_naiss) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = ConnexionTest.getConnection().prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getMatricule());
            ps.setString(2, e.getNom());
            ps.setString(3, e.getPrenom());
            if (e.getDateNaiss() != null) {
                ps.setDate(4, Date.valueOf(e.getDateNaiss()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            int lignes = ps.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("Aucune ligne insérée");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    Long id = keys.getLong(1);
                    e.setId(id);
                    return id;
                }
            }
        }
        return null;
    }
    public Optional<Etudiant> trouver(Long id) throws SQLException {
        String sql = "SELECT id, matricule, nom, prenom, date_naiss "
                + "FROM etudiant WHERE id = ?";

        try (PreparedStatement ps = ConnexionTest.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper(rs));
                }
                return Optional.empty();
            }
        }

    }
    public List<Etudiant> listerTous() throws SQLException {
        String sql = "SELECT id, matricule, nom, prenom, date_naiss "
                + "FROM etudiant ORDER BY nom, prenom";
        List<Etudiant> resultat = new ArrayList<>();

        try (PreparedStatement ps = ConnexionTest.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resultat.add(mapper(rs));
            }
        }
        return resultat;
    }

    private Etudiant mapper(ResultSet rs) throws SQLException {
        Etudiant e = new Etudiant();
        e.setId(rs.getLong("id"));
        e.setMatricule(rs.getString("matricule"));
        e.setNom(rs.getString("nom"));
        e.setPrenom(rs.getString("prenom"));

        Date d = rs.getDate("date_naiss");
        if (d != null) {
            e.setDateNaiss(d.toLocalDate());
        }
        return e;
    }

    public boolean supprimer(Long id) throws SQLException {
        String sql = "DELETE FROM etudiant WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }



}
