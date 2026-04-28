# Cours Maven, SQL, JPA/Hibernate
## Fil rouge : Étudiant / Module / Note

### Slide 1 — Titre du cours
- Maven, SQL, JPA/Hibernate
- Fil rouge : système de gestion des notes
- Niveau : L2
- Durée : 2h
- Java 21 compatible

### Slide 2 — Objectifs du cours
- Maîtriser Maven pour structurer un projet Java moderne
- Comprendre les bases d’une base de données relationnelle
- Manipuler les requêtes SQL essentielles
- Introduire JPA/Hibernate pour la persistance
- Relier le tout au fil rouge Étudiant / Module / Note

### Slide 3 — Plan du cours
- Partie 1 : Maven
- Partie 2 : SQL et bases relationnelles
- Partie 3 : JPA/Hibernate
- Étude de cas continue : Étudiant, Module, Note

### Slide 4 — Pourquoi Maven ?
- Maven automatise le build
- Il standardise la structure du projet
- Il gère les dépendances automatiquement
- Il rend le build reproductible

### Slide 5 — Intuition : automatiser le build
- Compiler, tester, empaqueter, installer
- Réduire le travail manuel
- Unifier les pratiques entre machines et équipes
- Éviter le chaos du classpath

### Slide 6 — Problèmes sans Maven
- JARs téléchargés manuellement
- Versions incompatibles
- Classpath fragile
- Build différent selon la machine

### Slide 7 — Avantages de Maven
- Structure de projet standard
- Résolution automatique des dépendances
- Cycle de vie clair
- Facilité d’intégration continue

### Slide 8 — Comparaison : sans Maven vs avec Maven
- Gestion des dépendances : manuelle vs automatique
- Structure du projet : variable vs standard
- Build : complexe vs simple
- Reproductibilité : faible vs élevée

### Slide 9 — Structure standard d’un projet Maven
- `src/main/java`
- `src/main/resources`
- `src/test/java`
- `pom.xml`
- Importance de respecter la convention

### Slide 10 — Le fichier pom.xml
- Rôle central du projet Maven
- Coordonnées Maven : groupId, artifactId, version
- Packaging du projet
- Propriétés Java

### Slide 11 — Exemple minimal de pom.xml
- Structure XML du projet
- Déclaration de Java 21
- Encodage UTF-8
- Packaging JAR

### Slide 12 — Commandes Maven essentielles
- `mvn compile`
- `mvn test`
- `mvn package`
- `mvn install`
- Sens de chaque commande

### Slide 13 — Dépendances Maven
- Dépendances directes
- Dépendances transitives
- Résolution automatique
- Exemple avec Hibernate et H2

### Slide 14 — Arbre des dépendances
- Notion de dépendances transitives
- Exemple : Hibernate amène d’autres bibliothèques
- Importance de `mvn dependency:tree`

### Slide 15 — Conflits de versions
- Deux bibliothèques peuvent demander des versions différentes
- Maven tranche selon ses règles
- Importance de contrôler les versions

### Slide 16 — Cycle de vie Maven
- validate
- compile
- test
- package
- install
- deploy

### Slide 17 — Commandes Maven utiles en pratique
- `mvn clean`
- `mvn verify`
- `mvn dependency:tree`
- `mvn dependency:analyze`
- `mvn clean install`

### Slide 18 — Java 21 avec Maven
- Configuration `source` / `target`
- `maven-compiler-plugin`
- Centralisation de `java.version`
- Compatibilité outil / version Java

### Slide 19 — Démarrage technique du projet
- Dépendances JDBC + Hibernate
- JPA API
- H2 pour le développement
- Importance des versions compatibles

### Slide 20 — Build reproductible
- Même code + même `pom.xml` = même build
- Versions figées
- Plugins versionnés
- Éviter les `SNAPSHOT` en production

### Slide 21 — Pourquoi une base de données ?
- Persister les données au-delà du programme
- Structurer les informations
- Faire des requêtes
- Garantir l’intégrité

### Slide 22 — RAM vs fichier vs base de données
- RAM : rapide mais volatile
- Fichier : persistant mais limité
- Base de données : structurée et requêtable
- Quand choisir chaque solution

### Slide 23 — Concepts relationnels de base
- Table
- Ligne
- Colonne
- Schéma
- Entité métier

### Slide 24 — Clé primaire et clé étrangère
- Clé primaire : identifiant unique
- Clé étrangère : relation entre tables
- Intégrité référentielle
- Exemple avec Étudiant, Module, Note

### Slide 25 — Présentation du fil rouge
- Table `etudiant`
- Table `module`
- Table `note`
- Une note relie un étudiant à un module

### Slide 26 — Relations du modèle
- Étudiant 1 → N Notes
- Module 1 → N Notes
- Note N → 1 Étudiant
- Note N → 1 Module

### Slide 27 — Types SQL utiles
- `VARCHAR`
- `INT`
- `DECIMAL`
- `DATE`
- `BOOLEAN`

### Slide 28 — SQL CREATE TABLE
- Création de `etudiant`
- Création de `module`
- Création de `note`
- Clés primaires et étrangères
- Contraintes de base

### Slide 29 — SQL INSERT
- Ajouter des étudiants
- Ajouter des modules
- Ajouter des notes
- Respecter l’ordre logique d’insertion

### Slide 30 — SQL SELECT, WHERE, ORDER BY
- Lecture des données
- Filtrage avec `WHERE`
- Tri avec `ORDER BY`
- Exemple sur les notes

### Slide 31 — Contraintes SQL
- `NOT NULL`
- `PRIMARY KEY`
- `FOREIGN KEY`
- `UNIQUE`
- `CHECK`

### Slide 32 — SQL UPDATE et DELETE
- Modifier des lignes avec `UPDATE`
- Supprimer avec `DELETE`
- Danger d’un `DELETE` sans `WHERE`
- Bonnes pratiques avant modification

### Slide 33 — SQL JOIN
- Relier plusieurs tables
- `INNER JOIN`
- Retrouver nom étudiant + module + note
- Lire des données plus parlantes que de simples IDs

### Slide 34 — Limites du JDBC manuel
- Beaucoup de code répétitif
- Gestion manuelle des connexions
- Mapping manuel `ResultSet` → objet
- Maintenance difficile

### Slide 35 — Décalage objet / relationnel
- Java manipule des objets liés
- SQL manipule des tables liées par clés
- Ce décalage complique le code manuel
- Besoin d’un ORM

### Slide 36 — Pourquoi JPA / Hibernate ?
- JPA = spécification standard
- Hibernate = implémentation
- Mapping automatique objet ↔ table
- Réduction du boilerplate JDBC

### Slide 37 — Annotations JPA de base
- `@Entity`
- `@Table`
- `@Id`
- `@GeneratedValue`
- `@Column`

### Slide 38 — Relations JPA
- `@ManyToOne`
- `@OneToMany`
- `@JoinColumn`
- `mappedBy`
- Application au fil rouge

### Slide 39 — Dépendances Maven pour JPA/Hibernate
- Dépendance JPA API
- Dépendance Hibernate Core
- Dépendance H2
- Rôle de chaque dépendance

### Slide 40 — persistence.xml
- Emplacement du fichier
- Unité de persistance
- Déclaration des entités
- URL JDBC, driver, dialect, DDL auto

### Slide 41 — Entité Étudiant
- Attributs principaux
- Contraintes métier
- Exemple d’annotations
- Relation avec les notes

### Slide 42 — Entité Module
- Code unique
- Libellé
- Coefficient
- Bonnes pratiques JPA

### Slide 43 — Entité Note
- Valeur
- Date de saisie
- Références vers Étudiant et Module
- Relations `@ManyToOne`

### Slide 44 — Persister une entité
- `EntityManager`
- `EntityTransaction`
- `persist()`
- `commit()`
- Gestion d’erreur et rollback

### Slide 45 — Lire une entité
- `find()`
- Requêtes JPQL simples
- Retour `null` si absent
- Importance du cycle de vie des entités

### Slide 46 — Cycle de vie d’une entité JPA
- NEW
- MANAGED
- DETACHED
- Synchronisation avec la base

### Slide 47 — Architecture propre
- model
- dao
- service
- séparation des responsabilités
- éviter la logique métier partout

### Slide 48 — Checklist finale
- Maven configuré
- Tables SQL pensées correctement
- JPA/Hibernate configuré
- CRUD et relations compris
- Architecture propre amorcée

### Slide 49 — Conclusion
- Maven structure et automatise
- SQL stocke et interroge
- JPA/Hibernate relie Java et la base
- Le fil rouge montre la continuité du projet
- Transition possible vers un mini-projet complet

### Slide 11 — Exemple minimal de pom.xml
- Structure XML du projet
- Déclaration de Java 21
- Encodage UTF-8
- Packaging JAR

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
  <modelVersion>4.0.0</modelVersion>
  <groupId>sn.etudiant</groupId>
  <artifactId>gestion-notes</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
</project>
```

### Slide 12 — Commandes Maven essentielles
- `mvn compile`
- `mvn test`
- `mvn package`
- `mvn install`
- Sens de chaque commande

```bash
mvn compile
mvn test
mvn package
mvn install
```

### Slide 13 — Dépendances Maven
- Dépendances directes
- Dépendances transitives
- Résolution automatique
- Exemple avec Hibernate et H2

```xml
<dependencies>
  <dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.4.4.Final</version>
  </dependency>

  <dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>2.2.224</version>
    <scope>runtime</scope>
  </dependency>
</dependencies>
```

### Slide 14 — Arbre des dépendances
- Notion de dépendances transitives
- Exemple : Hibernate amène d’autres bibliothèques
- Importance de `mvn dependency:tree`

```bash
mvn dependency:tree
```

### Slide 15 — Conflits de versions
- Deux bibliothèques peuvent demander des versions différentes
- Maven tranche selon ses règles
- Importance de contrôler les versions

```xml
<properties>
  <hibernate.version>6.4.4.Final</hibernate.version>
</properties>

<dependency>
  <groupId>org.hibernate.orm</groupId>
  <artifactId>hibernate-core</artifactId>
  <version>${hibernate.version}</version>
</dependency>
```

### Slide 16 — Cycle de vie Maven
- validate
- compile
- test
- package
- install
- deploy

```bash
mvn clean compile
mvn test
mvn package
mvn install
```

### Slide 17 — Commandes Maven utiles en pratique
- `mvn clean`
- `mvn verify`
- `mvn dependency:tree`
- `mvn dependency:analyze`
- `mvn clean install`

```bash
mvn clean
mvn verify
mvn dependency:tree
mvn dependency:analyze
mvn clean install
```

### Slide 18 — Java 21 avec Maven
- Configuration `source` / `target`
- `maven-compiler-plugin`
- Centralisation de `java.version`
- Compatibilité outil / version Java

```xml
<properties>
  <java.version>21</java.version>
  <maven.compiler.source>21</maven.compiler.source>
  <maven.compiler.target>21</maven.compiler.target>
</properties>

<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.11.0</version>
      <configuration>
        <source>21</source>
        <target>21</target>
      </configuration>
    </plugin>
  </plugins>
</build>
```

### Slide 19 — Démarrage technique du projet
- Dépendances JDBC + Hibernate
- JPA API
- H2 pour le développement
- Importance des versions compatibles

```xml
<dependencies>
  <dependency>
    <groupId>jakarta.persistence</groupId>
    <artifactId>jakarta.persistence-api</artifactId>
    <version>3.1.0</version>
  </dependency>

  <dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.4.4.Final</version>
  </dependency>

  <dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>2.2.224</version>
    <scope>runtime</scope>
  </dependency>
</dependencies>
```

### Slide 20 — Build reproductible
- Même code + même `pom.xml` = même build
- Versions figées
- Plugins versionnés
- Éviter les `SNAPSHOT` en production

```xml
<properties>
  <java.version>21</java.version>
  <hibernate.version>6.4.4.Final</hibernate.version>
</properties>
```

### Slide 21 — Pourquoi une base de données ?
- Persister les données au-delà du programme
- Structurer les informations
- Faire des requêtes
- Garantir l’intégrité

### Slide 22 — RAM vs fichier vs base de données
- RAM : rapide mais volatile
- Fichier : persistant mais limité
- Base de données : structurée et requêtable
- Quand choisir chaque solution

### Slide 23 — Concepts relationnels de base
- Table
- Ligne
- Colonne
- Schéma
- Entité métier

### Slide 24 — Clé primaire et clé étrangère
- Clé primaire : identifiant unique
- Clé étrangère : relation entre tables
- Intégrité référentielle
- Exemple avec Étudiant, Module, Note

### Slide 25 — Présentation du fil rouge
- Table `etudiant`
- Table `module`
- Table `note`
- Une note relie un étudiant à un module

### Slide 26 — Relations du modèle
- Étudiant 1 → N Notes
- Module 1 → N Notes
- Note N → 1 Étudiant
- Note N → 1 Module

### Slide 27 — Types SQL utiles
- `VARCHAR`
- `INT`
- `DECIMAL`
- `DATE`
- `BOOLEAN`

### Slide 28 — SQL CREATE TABLE
- Création de `etudiant`
- Création de `module`
- Création de `note`
- Clés primaires et étrangères
- Contraintes de base

```sql
CREATE TABLE etudiant (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  matricule VARCHAR(20) NOT NULL UNIQUE,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  date_naissance DATE
);

CREATE TABLE module (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  code VARCHAR(10) NOT NULL UNIQUE,
  libelle VARCHAR(200) NOT NULL
);

CREATE TABLE note (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  valeur DECIMAL(4,2) NOT NULL,
  date_saisie DATE DEFAULT CURRENT_DATE,
  etudiant_id BIGINT NOT NULL,
  module_id BIGINT NOT NULL,
  FOREIGN KEY (etudiant_id) REFERENCES etudiant(id),
  FOREIGN KEY (module_id) REFERENCES module(id)
);
```

### Slide 29 — SQL INSERT
- Ajouter des étudiants
- Ajouter des modules
- Ajouter des notes
- Respecter l’ordre logique d’insertion

```sql
INSERT INTO etudiant (matricule, nom, prenom, date_naissance)
VALUES ('ET001', 'Diop', 'Amadou', '2002-06-12');

INSERT INTO module (code, libelle)
VALUES ('JAVA201', 'Programmation Java');

INSERT INTO note (valeur, etudiant_id, module_id)
VALUES (15.50, 1, 1);
```

### Slide 30 — SQL SELECT, WHERE, ORDER BY
- Lecture des données
- Filtrage avec `WHERE`
- Tri avec `ORDER BY`
- Exemple sur les notes

```sql
SELECT e.matricule, e.nom, m.libelle, n.valeur, n.date_saisie
FROM note n
JOIN etudiant e ON n.etudiant_id = e.id
JOIN module m ON n.module_id = m.id
WHERE n.valeur >= 10
ORDER BY n.valeur DESC;
```

### Slide 31 — Contraintes SQL
- `NOT NULL`
- `PRIMARY KEY`
- `FOREIGN KEY`
- `UNIQUE`
- `CHECK`

```sql
CREATE TABLE module (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  code VARCHAR(10) NOT NULL UNIQUE,
  libelle VARCHAR(100) NOT NULL,
  coefficient INT CHECK (coefficient > 0)
);
```

### Slide 32 — SQL UPDATE et DELETE
- Modifier des lignes avec `UPDATE`
- Supprimer avec `DELETE`
- Danger d’un `DELETE` sans `WHERE`
- Bonnes pratiques avant modification

```sql
UPDATE etudiant
SET nom = 'DIOP'
WHERE matricule = 'ET001';

DELETE FROM note
WHERE valeur < 0;
```

### Slide 33 — SQL JOIN
- Relier plusieurs tables
- `INNER JOIN`
- Retrouver nom étudiant + module + note
- Lire des données plus parlantes que de simples IDs

```sql
SELECT e.nom, e.prenom, m.libelle, n.valeur
FROM note n
INNER JOIN etudiant e ON n.etudiant_id = e.id
INNER JOIN module m ON n.module_id = m.id;
```

### Slide 34 — Limites du JDBC manuel
- Beaucoup de code répétitif
- Gestion manuelle des connexions
- Mapping manuel `ResultSet` → objet
- Maintenance difficile

```java
Connection conn = DriverManager.getConnection(url, user, pass);
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM etudiant");
ResultSet rs = stmt.executeQuery();

while (rs.next()) {
    Etudiant e = new Etudiant();
    e.setId(rs.getLong("id"));
    e.setNom(rs.getString("nom"));
}

rs.close();
stmt.close();
conn.close();
```

### Slide 35 — Décalage objet / relationnel
- Java manipule des objets liés
- SQL manipule des tables liées par clés
- Ce décalage complique le code manuel
- Besoin d’un ORM

```java
class Etudiant {
    private List<Note> notes;
}
```

```sql
SELECT *
FROM note
JOIN etudiant ON note.etudiant_id = etudiant.id;
```

### Slide 36 — Pourquoi JPA / Hibernate ?
- JPA = spécification standard
- Hibernate = implémentation
- Mapping automatique objet ↔ table
- Réduction du boilerplate JDBC

### Slide 37 — Annotations JPA de base
- `@Entity`
- `@Table`
- `@Id`
- `@GeneratedValue`
- `@Column`

```java
import jakarta.persistence.*;

@Entity
@Table(name = "etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricule;

    @Column(nullable = false)
    private String nom;
}
```

### Slide 38 — Relations JPA
- `@ManyToOne`
- `@OneToMany`
- `@JoinColumn`
- `mappedBy`
- Application au fil rouge

```java
@ManyToOne
@JoinColumn(name = "etudiant_id", nullable = false)
private Etudiant etudiant;

@ManyToOne
@JoinColumn(name = "module_id", nullable = false)
private Module module;
```

### Slide 39 — Dépendances Maven pour JPA/Hibernate
- Dépendance JPA API
- Dépendance Hibernate Core
- Dépendance H2
- Rôle de chaque dépendance

```xml
<dependency>
  <groupId>jakarta.persistence</groupId>
  <artifactId>jakarta.persistence-api</artifactId>
  <version>3.1.0</version>
</dependency>

<dependency>
  <groupId>org.hibernate.orm</groupId>
  <artifactId>hibernate-core</artifactId>
  <version>6.4.4.Final</version>
</dependency>
```

### Slide 40 — persistence.xml
- Emplacement du fichier
- Unité de persistance
- Déclaration des entités
- URL JDBC, driver, dialect, DDL auto

```xml
<persistence version="3.0"
    xmlns="https://jakarta.ee/xml/ns/persistence">
  <persistence-unit name="gestion-notes" transaction-type="RESOURCE_LOCAL">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <class>sn.etudiant.model.Etudiant</class>
    <class>sn.etudiant.model.Module</class>
    <class>sn.etudiant.model.Note</class>
    <properties>
      <property name="jakarta.persistence.jdbc.driver" value="org.h2.Driver"/>
      <property name="jakarta.persistence.jdbc.url" value="jdbc:h2:./gestion-notes"/>
      <property name="jakarta.persistence.jdbc.user" value="sa"/>
      <property name="jakarta.persistence.jdbc.password" value=""/>
      <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
      <property name="hibernate.hbm2ddl.auto" value="update"/>
    </properties>
  </persistence-unit>
</persistence>
```

### Slide 41 — Entité Étudiant
- Attributs principaux
- Contraintes métier
- Exemple d’annotations
- Relation avec les notes

```java
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricule;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;
}
```

### Slide 42 — Entité Module
- Code unique
- Libellé
- Coefficient
- Bonnes pratiques JPA

```java
@Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String libelle;

    private Integer coefficient;
}
```

### Slide 43 — Entité Note
- Valeur
- Date de saisie
- Références vers Étudiant et Module
- Relations `@ManyToOne`

```java
@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valeur;

    private LocalDate dateSaisie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;
}
```

### Slide 44 — Persister une entité
- `EntityManager`
- `EntityTransaction`
- `persist()`
- `commit()`
- Gestion d’erreur et rollback

```java
EntityManager em = emf.createEntityManager();
EntityTransaction tx = em.getTransaction();

try {
    tx.begin();
    em.persist(etudiant);
    tx.commit();
} catch (Exception e) {
    if (tx.isActive()) tx.rollback();
} finally {
    em.close();
}
```

### Slide 45 — Lire une entité
- `find()`
- Requêtes JPQL simples
- Retour `null` si absent
- Importance du cycle de vie des entités

```java
Etudiant e = em.find(Etudiant.class, 1L);

List<Etudiant> etudiants = em.createQuery(
    "SELECT e FROM Etudiant e WHERE e.nom LIKE :nom",
    Etudiant.class
).setParameter("nom", "%Di%")
 .getResultList();
```

### Slide 46 — Cycle de vie d’une entité JPA
- NEW
- MANAGED
- DETACHED
- Synchronisation avec la base

```java
Etudiant e = new Etudiant();      // NEW
em.persist(e);                    // MANAGED
em.detach(e);                     // DETACHED
```

### Slide 47 — Architecture propre
- model
- dao
- service
- séparation des responsabilités
- éviter la logique métier partout

```java
public class EtudiantService {
    private final EtudiantDAO etudiantDAO;

    public EtudiantService(EtudiantDAO etudiantDAO) {
        this.etudiantDAO = etudiantDAO;
    }
}
```

### Slide 48 — Checklist finale
- Maven configuré
- Tables SQL pensées correctement
- JPA/Hibernate configuré
- CRUD et relations compris
- Architecture propre amorcée

### Slide 49 — Conclusion
- Maven structure et automatise
- SQL stocke et interroge
- JPA/Hibernate relie Java et la base
- Le fil rouge montre la continuité du projet
- Transition possible vers un mini-projet complet

## Notes de travail
- Le code a été ajouté sur les slides techniques
- On peut maintenant retravailler chaque slide avec plus de détails
- On peut aussi réduire le tout pour revenir à 38 slides exactes
- On peut enfin transformer ça en vrai support enseignant plus dense

