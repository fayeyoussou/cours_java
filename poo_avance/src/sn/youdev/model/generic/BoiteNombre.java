package sn.youdev.model.generic;

// ============================================================
// EXEMPLE 4 : Héritage + générique borné (bounded type)
// T extends Number : on LIMITE le générique aux types numériques
// Cela permet d'utiliser des méthodes comme doubleValue()
// On hérite de BoiteAbstraite et on OVERRIDE afficherDetails()
// ============================================================
public class BoiteNombre<T extends Number> extends BoiteAbstraite<T> {

    public BoiteNombre(T valeur) {
        super(valeur);
    }

    // On peut ajouter des méthodes spécifiques aux nombres
    // grâce à la borne "T extends Number"
    public double doubler() {
        return valeur.doubleValue() * 2;
    }

    public boolean estPositif() {
        return valeur.doubleValue() > 0;
    }

    // Override obligatoire car la méthode est abstraite dans BoiteAbstraite
    @Override
    public void afficherDetails() {
        System.out.println("=== Boîte de Nombre ===");
        System.out.println("Valeur : " + valeur);
        System.out.println("Type   : " + valeur.getClass().getSimpleName());
        System.out.println("Double : " + doubler());
        System.out.println("Positif: " + estPositif());
    }
}
