# Audit des supports PPTX et du code Java

Date de l'audit : 4 juin 2026  
Environnement de verification : JDK 21.0.1, Maven 3.9.6

## 1. Perimetre

- 280 fichiers Java, environ 12 807 lignes.
- 16 projets Maven.
- 4 PPTX sources uniques, 137 diapositives :
  - `swing-cours/.../Java swing cours 1 (1).pptx` : 22 slides.
  - `java_swing/src/main/resources/cours/Swing_UI_L2.pptx` : 50 slides.
  - `java_swing/src/main/resources/cours/Swing_Dynamic.pptx` : 30 slides.
  - `java_avance/java_moderne_dates.pptx` : 35 slides.
- Les deux PPTX sous `java_swing/target/classes/cours` sont des copies generees.
- 58 points d'entree `main`, mais seulement 4 vrais tests JUnit. Ces 4 tests sont des
  placeholders `assertTrue(true)`.

Le depot est un espace de cours multi-projets, pas une application unique. Il montre une
progression : bases/POO, tableaux et collections, lambdas, exceptions/fichiers, Java
moderne, Swing, JDBC, Hibernate et JPA.

## 2. Verdict global

La couverture pedagogique est large et les nouveaux supports Swing sont bien structures.
Les exemples Swing, JDBC et Hibernate montrent correctement beaucoup de concepts utiles :
layouts, listeners, EDT, `SwingWorker`, `PreparedStatement`, `Optional`, transactions et
DAO.

Le depot n'est toutefois pas reproductible de bout en bout. Plusieurs dossiers ne
compilent pas, un module Maven est casse, les versions Java ne sont pas uniformes, les
configurations de base de donnees sont codees en dur, et presque aucun comportement n'est
teste. Certains exemples sont volontairement incomplets, mais ils ne sont pas clairement
marques comme tels.

## 3. Resultats de compilation

### Projets Maven

15 projets Maven sur 16 passent `mvn test`.

Echec :

- `jdbc_g3` : `EtudiantDao.supprimer()` utilise la variable inexistante `conn`
  (`jdbc_g3/src/main/java/sn/l2gl/youssoupha/dao/EtudiantDao.java:90`).

Les builds Maven qui passent ne prouvent pas la correction fonctionnelle : la plupart
n'ont aucun test, et les rares tests sont des placeholders.

### Dossiers Java sans Maven

Compilation independante avec JDK 21 :

- Passent : `donnees-et-comparable`, `elamdaif`, `exemple`, `poo_avance`.
- Echouent : `compilation`, `fichier-exceptions`, `java_avance`, `lambda`,
  `struc-data`, `tableaux`, `untitled`.

Causes principales :

- `compilation` declare deux fois `compteur` et appelle une methode absente sur le mauvais
  type d'`Etudiant`.
- `struc-data/MainIF.java` utilise l'identifiant inexistant `A`.
- `untitled/Vehicule.java` depend d'un `EtatVehicule` absent et n'appelle pas le
  constructeur obligatoire de `Entite`.
- `fichier-exceptions`, `java_avance`, `lambda` et `tableaux` utilisent `IO.println` /
  `IO.print`, indisponibles dans le JDK 21 configure dans l'environnement. Cette exigence
  de version/API n'est pas documentee.
- `lambda/test/Calculatrice.java` tente aussi d'appliquer `+` a deux valeurs generiques
  `T extends Number`, operation interdite en Java.

## 4. Analyse des PPTX

### Ancien cours Swing, 22 slides

Points positifs :

- Introduction accessible a `JFrame`, `JLabel`, `JButton`, `JTextField`, evenements et
  trois layouts.
- Des exemples Java correspondants existent dans `swing-cours`.

Problemes :

- Le support est incomplet par rapport aux deux nouveaux decks Swing.
- Les slides 15 et 16 renvoient seulement vers "Voir code".
- La slide 22 est un reste de template Freepik avec faux email, faux telephone et faux
  site.
- La slide 4 contient encore le texte parasite "A portfolio is composed of three main
  elements".
- Le code `swing-cours` ne montre pas l'EDT avec `SwingUtilities.invokeLater`, alors que
  c'est une regle fondamentale Swing.

Conclusion : ce deck doit etre archive ou remplace par les deux supports `java_swing`.

### `Swing_UI_L2.pptx`, 50 slides

Points positifs :

- Bonne progression : hierarchie Swing, composants, layouts, design, composants avances,
  application complete et guidelines.
- Les exemples correspondent largement aux classes de `java_swing/ui` et
  `java_swing/ui/layouts`.
- Le deck explique correctement pourquoi eviter le placement absolu.
- Le contenu est plus coherent, moderne et exploitable que l'ancien cours.

Ecarts :

- Le support annonce FlatLaf, mais le `pom.xml` ne declare pas FlatLaf et aucun exemple
  Java ne l'utilise.
- Le depot ne contient pas les icones/images montrees par plusieurs extraits.
- La formulation "ne jamais ajouter directement a JFrame" est trop absolue, puisque
  `JFrame.add()` delegue justement vers le content pane.

### `Swing_Dynamic.pptx`, 30 slides

Points positifs :

- Bonne separation avec le deck UI : listeners, classes adapters, EDT, taches longues,
  `SwingWorker`, mise a jour progressive et MVC.
- Le code correspondant est riche : exemples pour Action, Item, Document, ListSelection,
  Mouse, Key, Window/Focus, `SwingWorker` et MVC.
- Les demonstrations asynchrones sont adaptees au niveau L2.

Risques :

- Lancement central manuel dans `java_swing/Main.java` : activer plusieurs appels
  `afficher()` lance plusieurs fenetres et rend la demonstration moins controlee.
- Seules quelques classes construisent explicitement l'UI sur l'EDT. Les methodes
  `afficher()` sont donc sures seulement si l'appelant respecte lui-meme l'EDT.
- Aucun test ne verifie les modeles Swing, les transitions MVC ou les traitements
  `SwingWorker`.

### `java_moderne_dates.pptx`, 35 slides

Points positifs :

- Bonne couverture des enums, records, `Optional`, `Objects`, `LocalDate`,
  `LocalDateTime`, `DateTimeFormatter`, migration depuis `Date`/`Calendar` et pieges de
  format.
- Les recommandations sur immutabilite, `orElseGet`, `orElseThrow`, `isBefore`,
  `isAfter`, `MM` contre `mm`, et `yyyy` contre `YYYY` sont utiles.

Problemes de fond :

- Contradiction sur `Optional` : le deck dit de ne jamais le mettre en attribut, puis le
  met dans les records du fil rouge.
- Dire "Fin des NullPointerException surprises" est trop fort : `Optional` ne supprime
  pas les NPE.
- `Calendar.getInstance()` n'est pas un singleton ; il retourne une nouvelle instance.
- La promesse que les records seront "directement mappes" avec Hibernate/JPA est
  trompeuse. Les records conviennent aux DTO/projections, pas directement aux entites JPA
  classiques.
- Le fil rouge des slides 28 a 32 n'existe pas reellement dans le code. Le record
  `Etudiant` du depot a une autre structure, `Note` n'a pas de commentaire optionnel, et
  il n'existe pas de modele integre avec modules, notes, moyenne et export CSV.
- `MainEnum` construit `LocalDate.of(2026, 2, 31)` et echoue immediatement a l'execution.
- Les bornes de `Mention.fromNote` sont incoherentes avec les intervalles declares :
  `12`, `14`, `16` et `18` tombent dans la mention inferieure a cause des `<=`.

## 5. Findings Java prioritaires

### Critiques

1. `jdbc_g3` ne compile pas : remplacer ou injecter correctement `conn` dans
   `EtudiantDao`.
2. `struc-data/Chauffeur.verifierPermis()` convertit le permis en minuscules puis compare
   a `"A"` et `"B"` majuscules. Tout permis non vide est rejete.
3. `java_avance/MainEnum.java` echoue toujours avec le 31 fevrier 2026.
4. `compilation/src/sn/youdev/test/Etudiant.java` contient une declaration dupliquee de
   `compteur`.

### Importants

1. Credentials MySQL `root/root`, ports et noms de bases sont dupliques en dur dans
   plusieurs projets. Ils doivent venir de variables d'environnement ou de fichiers de
   configuration non secrets.
2. `ConnexionTest.getConnection()` ouvre une nouvelle connexion a chaque appel dans les
   exemples JDBC. Plusieurs DAO ferment le statement/result set, mais pas la connexion.
3. `DatabaseConnection` conserve une connexion globale mutable. Ce modele est fragile
   pour concurrence, transactions et reprise apres erreur.
4. Plusieurs requetes JDBC n'encadrent pas les `ResultSet` par un try-with-resources
   (`ProduitServiceSQL`, `CategorieServiceSQL`).
5. `inserer()` retourne `null` quand aucune cle generee n'est disponible. Une exception ou
   un `OptionalLong` rendrait le contrat explicite.
6. `ProfesseurService` et `lambda/implementation/CrudPersonne` contiennent des methodes
   CRUD qui retournent seulement `null`.
7. Les setters de `Chauffeur` contournent les validations effectuees par le constructeur.
8. Les dossiers Maven, IntelliJ, sources, `target`, `out` et copies de PPTX sont melanges
   dans Git, ce qui augmente le bruit et les divergences.

### Qualite pedagogique

1. Certains exemples montrent volontairement de mauvaises pratiques, mais sans convention
   uniforme (`broken`, `anti-pattern`, exercice a corriger).
2. La duplication entre `mysql`, `jdbc_java` et `jdbc_g3` rend difficile d'identifier la
   version canonique.
3. Les packages `sn.youdev` sont reutilises dans plusieurs projets non relies, ce qui
   produit des classes dupliquees si l'on compile le depot comme un ensemble.
4. Le code melange francais, anglais, noms de groupes et noms de personnes dans les
   packages.
5. Les exemples modernes utilisent des APIs plus recentes sans fichier racine expliquant
   la version Java requise par module.

## 6. Correspondance cours / code

| Theme | Support | Code | Evaluation |
|---|---|---|---|
| Swing composants/layouts | `Swing_UI_L2.pptx` | `java_swing/ui`, `ui/layouts` | Bonne |
| Swing evenements/EDT/workers/MVC | `Swing_Dynamic.pptx` | `java_swing/events` | Bonne, tests absents |
| Ancien Swing introductif | ancien deck 22 slides | `swing-cours` | Obsolete et incomplet |
| Enum/record/Optional/date | `java_moderne_dates.pptx` | `java_avance` | Partielle, fil rouge absent |
| JDBC | guide Markdown + projets JDBC | 4 variantes JDBC | Riche mais duplique |
| Hibernate/JPA | guide Markdown | `hibernate_core`, projets 2/3 | Bonne progression technique |
| Exceptions/I/O | texte Markdown | `fichier-exceptions` | Bonne intention, build non reproductible |

## 7. Plan de remise en etat recommande

1. Ajouter un README racine avec la carte des modules, la version Java requise et les
   commandes de compilation/execution.
2. Corriger les erreurs de compilation reelles : `jdbc_g3`, `compilation`, `struc-data`,
   `untitled`, puis clarifier l'usage de `IO`.
3. Choisir une version canonique pour chaque sequence dupliquee, notamment Swing et JDBC.
4. Implementer exactement le fil rouge de `java_moderne_dates.pptx`, ou modifier les
   slides pour correspondre au code existant.
5. Corriger les affirmations PPTX sur `Optional`, `Calendar`, records/JPA et supprimer les
   restes de template de l'ancien deck Swing.
6. Externaliser les configurations de base de donnees et fermer explicitement les
   connexions.
7. Ajouter des tests ciblant au minimum les validations, mentions, comparateurs, parsing
   de fichiers, mapping JDBC et services CRUD.
8. Ajouter un parent Maven ou un script de verification qui compile chaque module
   independamment et produit une matrice de resultat.

## 8. Priorite pedagogique

Pour enseigner maintenant :

1. Utiliser `Swing_UI_L2.pptx` puis `Swing_Dynamic.pptx` avec le projet `java_swing`.
2. Utiliser `java_moderne_dates.pptx` seulement apres correction du fil rouge et des
   affirmations signalees.
3. Ne plus utiliser l'ancien deck Swing de 22 slides sauf comme brouillon historique.
4. Presenter les variantes cassees comme exercices explicites, jamais comme solution de
   reference.
