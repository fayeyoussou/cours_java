package sn.cours.exception;

// Levée quand on référence une catégorie qui n'existe pas en base
public class CategorieNotFoundException extends MetierException {

    public CategorieNotFoundException(Long id) {
        super("Catégorie introuvable avec l'id : " + id);
    }
}
