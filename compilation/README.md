# Guide de Compilation Java (`zsh` + Windows PowerShell)

## 1) Commandes correctes de compilation/exécution pour ce projet

Classe principale actuelle :
- package : `sn.youdev.app`
- classe : `Main`
- cible d'exécution : `sn.youdev.app.Main`

### macOS / Linux (`zsh`)

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out sn.youdev.app.Main
```

Note importante (`zsh`) :
- Éviter `FILES=$(find ...)` puis `javac ... $FILES` si plusieurs fichiers sont trouvés.
- En `zsh`, cette variable peut être transmise comme une seule valeur et provoquer `file not found`.

### Windows PowerShell

```powershell
mkdir out -Force
$files = Get-ChildItem -Path .\src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d .\out $files
java -cp .\out sn.youdev.app.Main
```

Note importante (PowerShell) :
- La même erreur peut arriver si `$files` est converti en une seule chaîne.
- Exemples à éviter : `javac ... "$files"` ou `$files = (... | Out-String)`.
- Version sûre :

```powershell
$files = @(Get-ChildItem -Path .\src -Recurse -Filter *.java | Select-Object -ExpandProperty FullName)
javac -encoding UTF-8 -d .\out @files
```

## 2) Erreurs de compilation/exécution à éviter

### `cannot find symbol: IO`
Cause :
- `IO.println(...)` a été utilisé, mais il n'y a pas de classe `IO` dans le code.

Correction :
- Utiliser `System.out.println(...)`.

### `Could not find or load main class out.Main`
Cause :
- `java out/Main` a été utilisé. `java` attend un nom de classe, pas un chemin de fichier.

Correction :
- Utiliser le classpath + le nom de classe complet :
- `java -cp out sn.youdev.app.Main`

### `zsh: no matches found: *java`
Cause :
- Mauvais motif glob (`*java`) et/ou aucun fichier `.java` dans le dossier courant.

Correction :
- Utiliser `*.java` si les fichiers sont dans le dossier courant.
- Pour ce projet, compiler récursivement depuis `src` avec `find`.

### `error: file not found: src/...Main.java src/...Personne.java`
Cause :
- En `zsh`, `FILES=$(find src -name "*.java")` peut devenir une seule chaîne multi-lignes.

Correction :
- Utiliser directement : `javac -encoding UTF-8 -d out $(find src -name "*.java")`.

### `error: file not found: ...` (PowerShell)
Cause :
- `$files` est passé comme une seule chaîne (par exemple avec `"$files"` ou `Out-String`).

Correction :
- Conserver `$files` en tableau et le passer tel quel :
- `$files = @(Get-ChildItem -Path .\src -Recurse -Filter *.java | Select-Object -ExpandProperty FullName)`
- `javac -encoding UTF-8 -d .\out @files`

### `error: file not found: srcsniagepoomodel*.java`
Cause :
- Des antislashs style Windows ont été utilisés dans `zsh`, où `\` sert d'échappement.

Correction :
- Dans `zsh`, utiliser des chemins avec `/` (exemple : `src/sn/youdev/app/Main.java`).

### `ClassNotFoundException: sn.iage.poo.app.Main`
Cause :
- La classe lancée ne correspond pas au package/nom de classe réel.

Correction :
- Lancer `sn.youdev.app.Main`.

### `zsh: parse error near '}'` avec une commande PowerShell
Cause :
- Une syntaxe PowerShell a été exécutée dans `zsh`.

Correction :
- Utiliser les commandes PowerShell uniquement dans PowerShell.
- Utiliser les commandes `zsh` dans un shell `zsh`/`bash`.

## 3) Checklist rapide avant exécution

- `javac` et `java` sont installés et disponibles dans le `PATH`.
- Tous les fichiers source sous `src` sont compilés.
- L'exécution se fait avec `-cp out`.
- La classe principale complète est correcte : `sn.youdev.app.Main`.

## 4) Conclusion finale: meilleure méthode (toutes les étapes)

### macOS / Linux (`zsh`) - procédure recommandée

1. Ouvrir le terminal dans le dossier du projet.
2. Vérifier Java :
   - `javac -version`
   - `java -version`
3. Créer le dossier de sortie :
   - `mkdir -p out`
4. Compiler tous les `.java` sous `src` :
   - `javac -encoding UTF-8 -d out $(find src -name "*.java")`
5. Exécuter l'application :
   - `java -cp out sn.youdev.app.Main`

Commande rapide :

```bash
mkdir -p out && javac -encoding UTF-8 -d out $(find src -name "*.java") && java -cp out sn.youdev.app.Main
```

### Windows (PowerShell) - procédure recommandée

1. Ouvrir PowerShell dans le dossier du projet.
2. Vérifier Java :
   - `javac -version`
   - `java -version`
3. Créer le dossier de sortie :
   - `mkdir out -Force`
4. Récupérer les sources Java en tableau :
   - `$files = @(Get-ChildItem -Path .\src -Recurse -Filter *.java | Select-Object -ExpandProperty FullName)`
5. Compiler :
   - `javac -encoding UTF-8 -d .\out @files`
6. Exécuter l'application :
   - `java -cp .\out sn.youdev.app.Main`

Commande rapide :

```powershell
mkdir out -Force | Out-Null
$files = @(Get-ChildItem -Path .\src -Recurse -Filter *.java | Select-Object -ExpandProperty FullName)
javac -encoding UTF-8 -d .\out @files
java -cp .\out sn.youdev.app.Main
```
