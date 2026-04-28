package sn.cours.exception;

// Levée quand on cherche un produit qui n'existe pas en base
public class ProduitNotFoundException extends MetierException {

    public ProduitNotFoundException(Long id) {
        super("Produit introuvable avec l'id : " + id);
    }
}
