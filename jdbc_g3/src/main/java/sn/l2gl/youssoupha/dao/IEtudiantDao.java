package sn.l2gl.youssoupha.dao;

import sn.l2gl.youssoupha.model.Etudiant;

import java.sql.SQLException;

import java.util.List;

import java.util.Optional;
 
public interface IEtudiantDao {

    /**

     * Insère un nouvel étudiant dans la base de données et retourne son identifiant généré.

     */

    Long inserer(Etudiant e) throws SQLException;

    /**

     * Recherche un étudiant par son identifiant.

     */

    Optional<Etudiant> trouver(Long id) throws SQLException;

    /**

     * Retourne la liste de tous les étudiants enregistrés.

     */

    List<Etudiant> listerTous() throws SQLException;

    /**

     * Modifie les informations d’un étudiant existant.

     */

    boolean modifier(Etudiant e) throws SQLException;

    /**

     * Supprime un étudiant à partir de son identifiant.

     */

    boolean supprimer(Long id) throws SQLException;

}
