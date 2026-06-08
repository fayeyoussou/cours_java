package sn.l2gl.youssoupha.hibernate.util;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Fournit l'unique SessionFactory de l'application (pattern singleton).
 * Construire une SessionFactory est coûteux : on ne le fait qu'une seule fois.
 */
public class HibernateUtil {

    private HibernateUtil() {
        /* Classe utilitaire : ne pas instancier */
    }

    @Getter
    private static final SessionFactory sessionFactory = build();

    private static SessionFactory build() {
        try {
            // Chargement de la configuration (hibernate.cfg.xml)
            StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .configure("hibernate.cfg.xml")
                            .build();

            Metadata metadata = new MetadataSources(registry)
                    .getMetadataBuilder()
                    .build();

            return metadata.getSessionFactoryBuilder().build();

        } catch (Exception e) {
            throw new RuntimeException("Impossible d'initialiser Hibernate : " + e.getMessage(), e);
        }
    }

    /** À appeler à la fermeture de l'application pour libérer les ressources. */
    public static void shutdown() {
        sessionFactory.close();
    }
}
