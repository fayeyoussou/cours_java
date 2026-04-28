package sn.cours.persistence;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Fournit une unique EntityManagerFactory pour toute l'application
public class JPAUtil {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("gestion-produits");

    public static EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }

    public static void close() {
        if (EMF != null && EMF.isOpen()) {
            EMF.close();
        }
    }
}
