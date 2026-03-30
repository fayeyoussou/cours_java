package sn.youdev.implementation;

import java.util.Objects;

public class Etudiant {
    private final String matricule;
    private  String classe;
    private int age;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o==null || !(o instanceof Etudiant)) return false;
        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(matricule, etudiant.matricule) && Objects.equals(classe, etudiant.classe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule,classe);
    }

    public Etudiant(String matricule, String classe, int age) {
        this.matricule = matricule;
        this.classe = classe;
        this.age = age;
    }

    public Etudiant(String matricule) {
        this.matricule = matricule;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
