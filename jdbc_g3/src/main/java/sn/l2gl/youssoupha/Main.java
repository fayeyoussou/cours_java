package sn.l2gl.youssoupha;

import sn.l2gl.youssoupha.dao.EtudiantDao;
import sn.l2gl.youssoupha.model.Etudiant;

import java.sql.SQLException;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        EtudiantDao dao = new EtudiantDao();
        Etudiant etudiant = new Etudiant(null,"A289","Diallo","Zeynab", LocalDate.of(2000,9,9));
        dao.inserer(etudiant);
        dao.supprimer(3l);
    }
}