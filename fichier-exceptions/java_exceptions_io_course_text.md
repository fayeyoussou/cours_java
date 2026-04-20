# Java Moderne → Swing → Hibernate

## Exceptions & Fichiers I/O
**Fil rouge : Étudiant / Module / Note**

- **Niveau** : L2
- **Pré-requis** : POO C++
- **Durée** : 2h Cours Magistral
- **Stack Technique** : Java 21 • NIO.2 • Zéro Lib

---

## 2. Pourquoi des exceptions ?
Une exception signale un événement anormal qui interrompt le flux d'exécution standard. Contrairement au C (`errno`, codes de retour), Java unifie la gestion des erreurs via des objets dédiés.

### Règle fondamentale
Ne retournez jamais une valeur spéciale pour signaler une erreur technique. L'absence de résultat valide doit toujours lever une exception.

### Codes de retour
- Utilisation de valeurs "magiques" (ex: `-1`, `NULL`)
- Mélange code métier / gestion erreur
- Risque élevé d’ignorer l’erreur

### Exceptions objets
- Séparation stricte : Happy Path vs Error Path
- Impossible d’ignorer
- Objet riche : Type, Message, Stacktrace

---

## 3. Pont C++ → Java
Vous connaissez déjà `try / catch / throw` en C++. Java reprend cette syntaxe mais impose une rigueur orientée objet absolue.

### Souvenirs C++
- On peut tout lancer : `throw 404;`, `throw "Erreur";`
- Pas de racine commune obligatoire
- Gestion mémoire manuelle

### Standard Java
- Objets uniquement : `throw new Exception("...");`
- Hiérarchie unique : tout dérive de `Throwable`
- Gestion mémoire automatique

### Le bonus Java
L’objet exception capture automatiquement la stacktrace.

---

## 4. Hiérarchie Java
Toutes les erreurs en Java héritent de `Throwable`.

- **Error** : problèmes JVM irrécupérables  
  Ex: `OutOfMemoryError`, `StackOverflowError`

- **Exception** : problèmes applicatifs et métier  
  Ex: `IOException`, `SQLException`, `NullPointerException`

### Note
C’est la classe `Exception` qui nous intéresse à 99% du temps.

---

## 5. Exceptions courantes
### RuntimeException
- `NullPointerException`
- `IllegalArgumentException`
- `IndexOutOfBoundsException`

### Exception (Checked)
- `IOException`
- `SQLException`
- `TimeoutException`

### Règle d’or
Une `RuntimeException` signale souvent un bug.  
Une exception checked doit être anticipée et gérée.

---

## 6. Checked vs Unchecked — Intuition
La distinction repose sur la responsabilité.

### Checked
- Dépend de l’environnement
- Fichier introuvable, réseau coupé, disque plein
- Le compilateur oblige à gérer

### Unchecked
- Erreur de logique
- `null`, division par zéro, hors limites
- Le compilateur ne dit rien

### Intuition du “if”
Si vous pouvez éviter l’erreur avec un simple test préalable, c’est souvent de l’unchecked.

---

## 7. Checked vs Unchecked — La règle
### Checked = aléa externe récupérable
Ex: `IOException`, `SQLException`

### Unchecked = bug
Ex: `NullPointerException`, `IndexOutOfBoundsException`

### Règle d’or
Si l’appelant peut raisonnablement se rétablir, utilisez une checked exception.

---

## 8. Checked vs Unchecked : les pièges
### Anti-pattern absolu : swallowing exceptions
Ne jamais écrire un `catch` vide.

### Excès #1 : Tout Checked
- Verbeux
- Couplage fort
- Pousse aux `catch(Exception e){}` vides

### Excès #2 : Tout Unchecked
- Contrat invisible
- Confusion entre bug et règle métier
- Oubli de gestion

---

## 9. Checked vs Unchecked : mini-scénarios
### Scénario 1 : Lecture de fichier CSV
Contexte : `importCSV(Path p)`  
Problème : fichier absent ou disque illisible  
Verdict : **Checked** (`IOException`)

### Scénario 2 : Note invalide
Contexte : `new Note(-5.0)`  
Problème : note hors bornes  
Verdict : **Unchecked** (`IllegalArgumentException`)

---

## 10. Tableau comparatif : Checked vs Unchecked
### Checked
- Erreur externe et récupérable
- `try/catch` ou `throws` obligatoire
- L’appelant doit prévoir un plan B
- Ex: `IOException`, `SQLException`, `ParseException`

### Unchecked
- Erreur de programmation
- Aucune obligation compilation
- Le développeur doit corriger son code
- Ex: `NullPointerException`, `IllegalArgumentException`

---

## 11. try / catch / finally
### Exemple
```java
try {
    String input = "vingt";
    System.out.println("Début traitement...");
    int note = Integer.parseInt(input);
    System.out.println("Note validée : " + note);
} catch (NumberFormatException e) {
    System.err.println("Erreur : Pas un entier !");
} finally {
    System.out.println("Fin de l'opération.");
}
```

### Bloc try
- Code qui peut échouer

### Bloc catch
- Intercepte et traite l’erreur

### Bloc finally
- Toujours exécuté

---

## 12. finally — Le nettoyage garanti
Le bloc `finally` est exécuté systématiquement après le `try`, erreur ou non.

### Pattern legacy
```java
BufferedReader br = null;
try {
    br = new BufferedReader(...);
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (br != null) {
        try {
            br.close();
        } catch (IOException e) {
        }
    }
}
```

---

## 13. Multi-catch & ordre
### Exemple
```java
try {
    service.sauvegarderNote(etudiant, note);
} catch (NoteInvalideException e) {
    System.err.println("Note hors limite : " + e.getMessage());
} catch (IOException | SQLException e) {
    System.err.println("Erreur I/O ou BDD : " + e.getMessage());
} catch (Exception e) {
    System.err.println("Erreur inattendue : " + e);
}
```

### Règles
- Du plus spécifique au plus général
- `Exception` en dernier
- `|` évite la duplication

---

## 14. try-with-resources — Intuition
Les ressources créées dans `try(...)` sont automatiquement fermées à la sortie du bloc.

### Avant Java 7
- Nettoyage manuel
- Verbeux
- Risque d’oubli de `close()`

### Recommandé
- `try (Ressource r = ...) { ... }`
- Fermeture automatique
- Code plus lisible

---

## 15. AutoCloseable — La clé du TWR
Tout objet qui implémente `AutoCloseable` est compatible avec `try-with-resources`.

```java
public interface AutoCloseable {
    void close() throws Exception;
}
```

### Exemples
- `InputStream`, `Writer`
- `Scanner`, `ZipFile`
- `Connection`, `ResultSet`

---

## 16. Lecture avec try-with-resources
```java
Path path = Path.of("data", "notes.csv");

try (BufferedReader reader = Files.newBufferedReader(path)) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println("> " + line);
    }
} catch (IOException e) {
    System.err.println("Erreur I/O : " + e.getMessage());
}
```

### Règle d’or
Préférez toujours le try-with-resources pour les I/O.

---

## 17. Créer ses exceptions
Quand créer une exception personnalisée ?

### Approche générique
- `IllegalArgumentException("note invalide")`
- Ambigu
- Peu ciblable

### Approche métier
- `NoteInvalideException`
- Nom explicite
- Peut contenir des champs dédiés

---

## 18. Exception métier : NoteInvalideException
```java
public class NoteInvalideException extends Exception {
    private final double valeurRefusee;

    public NoteInvalideException(double valeur) {
        super("Note invalide : " + valeur + " (limites: 0-20)");
        this.valeurRefusee = valeur;
    }

    public double getValeurRefusee() {
        return valeurRefusee;
    }
}
```

---

## 19. EtudiantInexistantException
```java
public Etudiant trouverParMatricule(String matricule)
        throws EtudiantInexistantException {
    for (Etudiant e : listeEtudiants) {
        if (e.getMatricule().equals(matricule)) {
            return e;
        }
    }
    throw new EtudiantInexistantException(
        "Aucun étudiant trouvé avec le matricule : " + matricule
    );
}
```

### Piège
Ne pas retourner `null`.

---

## 20. Propagation : throws vs try/catch
À chaque niveau, il faut choisir.

### Gérer localement
- Corriger
- Traduire
- Afficher à l’utilisateur

### Laisser remonter
- Pas de solution ici
- L’appelant décidera
- Service bas niveau

---

## 21. Où gérer l’exception ?
### Couche Service / Métier
- Logger
- Wrapper
- Pas de `System.out`

### Couche UI / Main
- Catch final
- Afficher un message compréhensible
- Ne jamais montrer une stacktrace brute à l’utilisateur

---

## 22. Propagation des exceptions
### UI
- Informe l’utilisateur

### Service
- Traduit les erreurs techniques en erreurs métier

### Couches basses
- Détectent et signalent

---

## 23. Bonnes pratiques : gestion
### À éviter
- `catch(Exception e) {}`
- `e.printStackTrace()`
- `throw new MyEx("Erreur")` sans cause

### Bonnes pratiques
- Logger proprement
- Ajouter du contexte
- Chaîner les exceptions

---

## 24. Wrapping : l’exception Service
```java
public void importerNotes(Path fichier) throws ServiceException {
    try {
        List<String> lignes = Files.readAllLines(fichier);
        for (String ligne : lignes) {
            // parsing...
        }
    } catch (IOException e) {
        System.err.println("Erreur I/O : " + e.getMessage());
        throw new ServiceException("Impossible d'importer les notes", e);
    }
}
```

---

## 25. NIO.2 — L’objet Path
`Path` remplace `java.io.File`.

### Création
- `Path.of("data", "note.csv")`

### Manipulation
- `resolve`
- `normalize`
- `toAbsolutePath`

### Avantage
- Portable entre OS
- UTF-8 par défaut avec `Files`

---

## 26. NIO.2 — Lire texte simplement
```java
Path p = Path.of("data/notes.csv");

String text = Files.readString(p);
List<String> lignes = Files.readAllLines(p);

try (Stream<String> stream = Files.lines(p)) {
    stream.filter(s -> !s.isBlank())
          .filter(s -> s.contains(";20;"))
          .forEach(System.out::println);
} catch (IOException e) {
    System.err.println("Erreur lecture : " + e.getMessage());
}
```

### Piège
Ne pas utiliser `readString` ou `readAllLines` sur de très gros fichiers.

---

## 27. Écrire des fichiers (NIO.2)
```java
public void sauvegarderNote(Path p, String csv) {
    try {
        Files.writeString(p, csv);

        String log = "\n[INFO] Export terminé";
        Files.writeString(p, log,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

        System.out.println("Fichier écrit : " + p);
    } catch (IOException e) {
        System.err.println("Erreur I/O : " + e.getMessage());
    }
}
```

---

## 28. NIO.2 — Erreurs fréquentes
- `NoSuchFileException`
- `AccessDeniedException`
- `MalformedInputException`

### Conseil
Capturez les exceptions spécifiques avant `IOException`.

---

## 29. BufferedReader / Writer
```java
Path src = Path.of("gros_fichier.txt");
Path dst = Path.of("copie_propre.txt");

try (BufferedReader reader = Files.newBufferedReader(src);
     BufferedWriter writer = Files.newBufferedWriter(dst)) {

    String ligne;
    while ((ligne = reader.readLine()) != null) {
        if (!ligne.isBlank()) {
            writer.write(ligne);
            writer.newLine();
        }
    }
    System.out.println("Copie terminée.");
} catch (IOException e) {
    System.err.println("Erreur I/O : " + e.getMessage());
}
```

---

## 30. Tableau comparatif : Files vs BufferedReader
### Files
- Simple
- Tout en mémoire
- Idéal petits fichiers

### BufferedReader / Writer
- Lecture ligne par ligne
- Faible empreinte mémoire
- Idéal gros fichiers

---

## 31. CSV simple : format du fichier
### Règles
- Séparateur `;`
- Date ISO `yyyy-MM-dd`
- UTF-8
- Pas de guillemets

### Champs
1. Matricule
2. Nom
3. Module
4. Note
5. DateSaisie

### Exemple
```text
matricule;nom;module;note;date
2024001;Diop;Java;15.5;2024-03-12
2024002;Fall;Web;12.0;2024-03-12
2024003;Ndiaye;Java;18.0;2024-03-10
```

---

## 32. Parsing CSV : Ligne → Objet
```java
private Note parseLigne(String ligne) throws Exception {
    String[] cols = ligne.split(";");

    if (cols.length != 5) {
        throw new NoteInvalideException("Format incorrect (5 colonnes requises)");
    }

    String mat = cols[0].trim();
    String nom = cols[1].trim();
    String mod = cols[2].trim();

    double val = Double.parseDouble(cols[3].trim());

    Etudiant etd = new Etudiant(mat, nom);
    return new Note(etd, new Module(mod), val);
}
```

---

## 33. Parsing : dates et nombres
```java
String dateIso = "2024-03-21";
LocalDate d1 = LocalDate.parse(dateIso);

String dateFr = "21/03/2024";
DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate d2 = LocalDate.parse(dateFr, fmt);

String noteStr = "15.5";
try {
    double note = Double.parseDouble(noteStr);
} catch (NumberFormatException e) {
    noteStr = noteStr.replace(',', '.');
    double note = Double.parseDouble(noteStr);
}
```

---

## 34. CSV — Stratégie : ignorer lignes invalides
Traitement batch “best effort” :
- Boucler sur les lignes
- Capturer l’erreur de parsing
- Logger
- Continuer

### Bilan obligatoire
- Nombre de succès
- Nombre d’échecs
- Liste des erreurs

---

## 35. Stratégie CSV : arrêt à la 1re erreur
Approche **fail-fast** :
- Interrompre au premier problème
- Garantit l’intégrité
- Débogage simple
- Mais UX plus frustrante

---

## 36. Encapsulation : service d’import
### Model
- `Etudiant`, `Note`, `Module`
- Données et validation simple

### Service
- Lecture du fichier
- Parsing CSV
- Gestion des exceptions
- Retourne un rapport

---

## 37. EtudiantService : bilan d’import
```java
public record Bilan(int ok, int ko, List<String> logs) {}

public Bilan importer(Path path) throws IOException {
    List<String> logs = new ArrayList<>();
    int ok = 0;
    List<String> lignes = Files.readAllLines(path);

    for (int i = 1; i < lignes.size(); i++) {
        try {
            Etudiant e = parse(lignes.get(i));
            repo.save(e);
            ok++;
        } catch (NoteInvalideException | DateTimeParseException e) {
            logs.add("Ligne " + (i + 1) + ": " + e.getMessage());
        }
    }

    return new Bilan(ok, logs.size(), logs);
}
```

---

## 38. Checklist : erreurs fréquentes
### Gestion des exceptions
- Pas de catch vide
- Éviter `e.printStackTrace()`
- Préférer exceptions métier

### Ressources & fichiers
- Éviter fermeture manuelle
- Utiliser try-with-resources
- Utiliser `Path` et `Files`

### Parsing CSV
- Vérifier `parts.length`
- Attention aux locales
- Utiliser `trim()`

### Architecture
- Pas de logique I/O dans le `main`
- Le service wrappe `IOException`
- Code court et clair

