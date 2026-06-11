package sn.l2gl.youssoupha.hibernate_simple.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2gl.youssoupha.exception.EtudiantNonTrouveException;
import sn.l2gl.youssoupha.hibernate.model.dao.Dao;
import sn.l2gl.youssoupha.hibernate.util.HibernateUtil;
import sn.l2gl.youssoupha.hibernate_simple.exception.MatriculeDejaExistantException;
import sn.l2gl.youssoupha.hibernate_simple.model.Etudiant;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/** Accès aux données pour l'entité Etudiant (matricule = clé primaire). */
public class EtudiantDao implements Dao<Etudiant, String> {

    /** Ouvre une nouvelle session Hibernate à partir de la SessionFactory partagée. */
    private Session ouvrir() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Insère un nouvel étudiant en base.
     *
     * @param entity l'étudiant à enregistrer
     * @return l'étudiant persisté
     * @throws MatriculeDejaExistantException si le matricule est déjà utilisé
     */
    @Override
    public Etudiant inserer(Etudiant entity) {
        try (Session session = ouvrir()) {
            trouver(entity.getMatricule()).ifPresent(e -> {
                throw new MatriculeDejaExistantException(entity.getMatricule()); // matricule déjà pris
            });
            
            Transaction tx = session.beginTransaction(); // démarre la transaction
            session.persist(entity); // enregistre la nouvelle entité dans le contexte de persistance
            tx.commit(); // valide la transaction (écriture effective en base)
            return entity;
        }
    }

    /**
     * Recherche un étudiant par son matricule (clé primaire).
     *
     * @param matricule le matricule recherché
     * @return un Optional contenant l'étudiant si il existe, vide sinon
     */
    @Override
    public Optional<Etudiant> trouver(String matricule) {
        try (Session session = ouvrir()) {
            return Optional.ofNullable(session.find(Etudiant.class, matricule)); // find peut renvoyer null -> encapsulé dans un Optional
        }
    }

    /**
     * Liste tous les étudiants triés par matricule.
     *
     * @return la liste complète des étudiants
     */
    @Override
    public List<Etudiant> listerTous() {
        try (Session session = ouvrir()) {
            return session.createQuery("from Etudiant order by matricule", Etudiant.class).list(); // requête HQL récupérant tous les étudiants
        }
    }

    /**
     * Met à jour un étudiant existant (le matricule, clé primaire, n'est pas modifiable).
     *
     * @param entity l'étudiant portant les nouvelles valeurs
     * @return un Optional contenant l'étudiant mis à jour
     */
    @Override
    public Optional<Etudiant> modifier(Etudiant entity) {
        try (Session session = ouvrir()) {
            Transaction tx = session.beginTransaction(); // démarre la transaction
            Etudiant etudiant = session.merge(entity); // fusionne l'entité détachée et renvoie l'instance gérée
            tx.commit(); // valide la mise à jour en base
            return Optional.of(etudiant);
        }
    }

    /**
     * Supprime l'étudiant identifié par son matricule.
     *
     * @param matricule le matricule de l'étudiant à supprimer
     * @return true si la suppression a eu lieu, false si l'étudiant n'existe pas
     */
    @Override
    public boolean supprimer(String matricule) {
        try (Session session = ouvrir()) {
            Etudiant etudiant = trouver(matricule).orElseThrow(()->new EtudiantNonTrouveException(matricule));
            Transaction tx = session.beginTransaction(); // démarre la transaction
            session.remove(etudiant); // marque l'entité pour suppression
            tx.commit();
            return true;

        }
    }

    /**
     * Indique si un matricule est déjà présent en base (contrôle « au focus » du formulaire).
     *
     * @param matricule le matricule à tester
     * @return true si un étudiant porte déjà ce matricule
     */
    public boolean existe(String matricule) {
        try (Session session = ouvrir()) {
            AtomicReference<Boolean> trouve = new AtomicReference<>(false);
             trouver(matricule).ifPresent(e->{
                trouve.set(true);
            });// présence directe via la clé primaire
            return trouve.get();
        }
    }
}
