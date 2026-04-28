# Guide — Créer la base de données MySQL pour les projets du cours

## Prérequis

- MySQL installé et démarré sur le port **3307**
- Un client MySQL : ligne de commande, MySQL Workbench, DBeaver, ou autre
- Identifiants : **root / root**

---

## Étape 1 — Se connecter à MySQL

### Via la ligne de commande

```bash
mysql -u root -p -P 3307 -h 127.0.0.1
```

Saisissez le mot de passe `root` lorsqu'il est demandé.

### Via MySQL Workbench / DBeaver

Créer une nouvelle connexion avec les paramètres :

| Paramètre       | Valeur      |
|-----------------|-------------|
| Hôte            | 127.0.0.1   |
| Port            | 3307        |
| Utilisateur     | root        |
| Mot de passe    | root        |

---

## Étape 2 — Créer la base de données

```sql
CREATE DATABASE IF NOT EXISTS gestion_produits
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE gestion_produits;
```

> **utf8mb4** est le jeu de caractères recommandé pour MySQL — il prend en charge tous les caractères Unicode, y compris les emojis.

---

## Étape 3 — Créer les tables

### Table `categorie`

```sql
CREATE TABLE IF NOT EXISTS categorie (
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    code    VARCHAR(20)  NOT NULL,
    libelle VARCHAR(200) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uq_categorie_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

**Explication des colonnes :**

| Colonne   | Type         | Contrainte         | Rôle                           |
|-----------|--------------|--------------------|--------------------------------|
| `id`      | BIGINT       | PRIMARY KEY, AUTO_INCREMENT | Identifiant unique généré automatiquement |
| `code`    | VARCHAR(20)  | NOT NULL, UNIQUE   | Code court de la catégorie     |
| `libelle` | VARCHAR(200) | NOT NULL           | Nom complet de la catégorie    |

---

### Table `produit`

```sql
CREATE TABLE IF NOT EXISTS produit (
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    code         VARCHAR(20)  NOT NULL,
    libelle      VARCHAR(200) NOT NULL,
    id_categorie BIGINT       NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uq_produit_code (code),
    CONSTRAINT fk_produit_categorie
        FOREIGN KEY (id_categorie) REFERENCES categorie(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

**Explication des colonnes :**

| Colonne        | Type         | Contrainte              | Rôle                                     |
|----------------|--------------|-------------------------|------------------------------------------|
| `id`           | BIGINT       | PRIMARY KEY, AUTO_INCREMENT | Identifiant unique généré automatiquement |
| `code`         | VARCHAR(20)  | NOT NULL, UNIQUE        | Code court du produit                    |
| `libelle`      | VARCHAR(200) | NOT NULL                | Nom complet du produit                   |
| `id_categorie` | BIGINT       | NOT NULL, FOREIGN KEY   | Référence vers la table `categorie`      |

> **ON DELETE RESTRICT** : impossible de supprimer une catégorie si des produits y sont rattachés.
> **ON UPDATE CASCADE** : si l'`id` d'une catégorie change, la référence dans `produit` se met à jour automatiquement.

---

## Étape 4 — Insérer des données de test

```sql
-- Catégories
INSERT INTO categorie (code, libelle) VALUES ('INFO',  'Informatique');
INSERT INTO categorie (code, libelle) VALUES ('ELECT', 'Electronique');
INSERT INTO categorie (code, libelle) VALUES ('BURO',  'Bureautique');

-- Produits
INSERT INTO produit (code, libelle, id_categorie) VALUES ('CLAV01',   'Clavier mécanique', 1);
INSERT INTO produit (code, libelle, id_categorie) VALUES ('SOURIS01', 'Souris sans fil',   1);
INSERT INTO produit (code, libelle, id_categorie) VALUES ('SOUD01',   'Fer à souder',      2);
INSERT INTO produit (code, libelle, id_categorie) VALUES ('STYLO01',  'Stylo bille',       3);
```

---

## Étape 5 — Vérifier les données

```sql
-- Lister toutes les catégories
SELECT * FROM categorie;

-- Lister tous les produits avec leur catégorie
SELECT
    p.id,
    p.code       AS code_produit,
    p.libelle    AS produit,
    c.libelle    AS categorie
FROM produit p
INNER JOIN categorie c ON p.id_categorie = c.id
ORDER BY c.libelle, p.libelle;
```

---

## Résumé de la structure

```
gestion_produits
├── categorie
│   ├── id           BIGINT  PK AUTO_INCREMENT
│   ├── code         VARCHAR(20)  UNIQUE NOT NULL
│   └── libelle      VARCHAR(200) NOT NULL
│
└── produit
    ├── id           BIGINT  PK AUTO_INCREMENT
    ├── code         VARCHAR(20)  UNIQUE NOT NULL
    ├── libelle      VARCHAR(200) NOT NULL
    └── id_categorie BIGINT  FK → categorie(id)
```

---

## Utilisation dans les projets

| Projet                  | Connexion                                          | Ce que fait l'application                        |
|-------------------------|----------------------------------------------------|--------------------------------------------------|
| `projet2-jdbc-sql`      | `DatabaseConnection.java` — JDBC direct            | `SchemaInitializer` crée les tables si absentes  |
| `projet3-hibernate-jpa` | `META-INF/persistence.xml` — Hibernate `hbm2ddl.auto=update` | Hibernate met à jour le schéma automatiquement |

> Pour `projet2-jdbc-sql`, la base et les tables doivent **obligatoirement** exister avant de lancer l'application (ou `SchemaInitializer.init()` les crée via `CREATE TABLE IF NOT EXISTS`).
>
> Pour `projet3-hibernate-jpa`, Hibernate crée ou met à jour les tables automatiquement grâce à `hbm2ddl.auto=update` — seule **la base `gestion_produits`** doit être créée manuellement (étape 2).

---

## Exemple PostgreSQL (pour référence)

Le même schéma en PostgreSQL utilise `SERIAL` ou `GENERATED ALWAYS AS IDENTITY` à la place de `AUTO_INCREMENT` :

```sql
-- PostgreSQL — connexion : owod / owod, port 5432
CREATE DATABASE gestion_produits;

CREATE TABLE categorie (
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code    VARCHAR(20)  NOT NULL UNIQUE,
    libelle VARCHAR(200) NOT NULL
);

CREATE TABLE produit (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code         VARCHAR(20)  NOT NULL UNIQUE,
    libelle      VARCHAR(200) NOT NULL,
    id_categorie BIGINT NOT NULL,
    CONSTRAINT fk_produit_categorie
        FOREIGN KEY (id_categorie) REFERENCES categorie(id)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
```

> Les fichiers de configuration PostgreSQL pour chaque projet se trouvent dans :
> - `projet2-jdbc-sql/src/main/resources/postgres-exemple.xml`
> - `projet3-hibernate-jpa/src/main/resources/persistence-postgres-exemple.xml`
