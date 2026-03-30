# Cours 2 - POO Java - 120 questions/reponses

1. Q: Qu'est-ce qu'une classe en Java ?
   R: Une classe est un modele qui definit des attributs et des methodes.
2. Q: Qu'est-ce qu'un objet ?
   R: Un objet est une instance concrete creee a partir d'une classe.
3. Q: Peut-on creer plusieurs objets d'une meme classe ?
   R: Oui, autant que necessaire.
4. Q: Quelle instruction cree un objet ?
   R: L'operateur `new`.
5. Q: A quoi servent les attributs ?
   R: A stocker l'etat d'un objet.
6. Q: A quoi servent les methodes ?
   R: A definir les comportements d'un objet.
7. Q: Comment s'appelle le constructeur ?
   R: Il porte exactement le meme nom que la classe.
8. Q: Un constructeur a-t-il un type de retour ?
   R: Non.
9. Q: Peut-on avoir plusieurs constructeurs ?
   R: Oui, via la surcharge.
10. Q: Pourquoi surcharger un constructeur ?
    R: Pour offrir plusieurs facons de creer un objet.
11. Q: Que fait `this` ?
    R: `this` designe l'objet courant.
12. Q: Pourquoi ecrire `this.nom = nom` ?
    R: Pour distinguer l'attribut de classe et le parametre.
13. Q: Que fait `this(...)` dans un constructeur ?
    R: Il appelle un autre constructeur de la meme classe.
14. Q: Ou doit se trouver `this(...)` ?
    R: En premiere instruction du constructeur.
15. Q: Que signifie `private` ?
    R: Accessible uniquement dans la classe elle-meme.
16. Q: Que signifie l'acces par defaut (sans mot-cle) ?
    R: Accessible seulement dans le meme package.
17. Q: Que signifie `protected` ?
    R: Accessible dans le package et dans les sous-classes.
18. Q: Que signifie `public` ?
    R: Accessible partout.
19. Q: Pourquoi mettre les attributs en `private` ?
    R: Pour proteger l'etat et controler les modifications.
20. Q: A quoi sert un getter ?
    R: A lire un attribut prive.
21. Q: A quoi sert un setter ?
    R: A modifier un attribut prive avec controle.
22. Q: Que permet la validation dans un setter ?
    R: D'imposer les regles metier.
23. Q: Quelle exception lever pour une valeur invalide ?
    R: Souvent `IllegalArgumentException`.
24. Q: Qu'est-ce que l'encapsulation ?
    R: Cacher l'etat interne et exposer une API controlee.
25. Q: Pourquoi l'encapsulation reduit les bugs ?
    R: Parce qu'elle empeche les modifications incoherentes.
26. Q: Qu'est-ce qu'un membre d'instance ?
    R: Un membre propre a chaque objet.
27. Q: Qu'est-ce qu'un membre `static` ?
    R: Un membre partage par toute la classe.
28. Q: Comment appeler une methode `static` ?
    R: Avec le nom de la classe, par exemple `Etudiant.getCompteur()`.
29. Q: Une methode `static` peut-elle acceder directement aux attributs d'instance ?
    R: Non.
30. Q: Une methode d'instance peut-elle acceder a `static` ?
    R: Oui.
31. Q: Quand utiliser `static` ?
    R: Pour des donnees ou comportements communs a toutes les instances.
32. Q: Exemples de `static` en cours 2 ?
    R: Un compteur d'etudiants.
33. Q: Que fait le mot-cle `final` sur un attribut ?
    R: L'attribut ne peut plus etre reaffecte apres initialisation.
34. Q: Pourquoi rendre `matricule` final ?
    R: Pour eviter les changements d'identite.
35. Q: Une classe Java peut-elle avoir des attributs publics ?
    R: Oui, mais ce n'est pas recommande pour le modele metier.
36. Q: Quelle est la bonne pratique pour les attributs metier ?
    R: `private` + getters/setters.
37. Q: Quel est le role de `super(...)` ?
    R: Appeler le constructeur de la classe parente.
38. Q: `super(...)` doit-il etre en premiere instruction ?
    R: Oui.
39. Q: Peut-on mettre `this(...)` et `super(...)` dans le meme constructeur ?
    R: Non, un seul appel possible en premiere ligne.
40. Q: Que se passe-t-il si la classe parent n'a pas de constructeur vide ?
    R: Le fils doit appeler explicitement un constructeur parent valide.
41. Q: Difference principale classe/objet ?
    R: Classe = definition, objet = instance.
42. Q: Dans le modele, que contient `Etudiant` ?
    R: Un matricule, un nom, un prenom, eventuellement un email.
43. Q: Dans le modele, que contient `Module` ?
    R: Un code, un libelle, un coefficient.
44. Q: Dans le modele, que contient `Note` ?
    R: Un etudiant, un module, une valeur de note.
45. Q: Pourquoi `Note` reference `Etudiant` et `Module` ?
    R: Pour lier la note a l'etudiant et au module concernes.
46. Q: Regle metier pour `valeur` d'une note ?
    R: Entre 0 et 20.
47. Q: Regle metier pour `coefficient` ?
    R: Strictement superieur a 0.
48. Q: Comment calculer les points ?
    R: `valeur * coefficient`.
49. Q: Pourquoi valider l'email dans `Etudiant` ?
    R: Pour eviter les formats incoherents.
50. Q: Pourquoi normaliser `code` module en majuscules ?
    R: Pour garder un format uniforme.
51. Q: Quelle relation cardinalite entre `Etudiant` et `Note` ?
    R: Un etudiant peut avoir plusieurs notes.
52. Q: Quelle relation cardinalite entre `Module` et `Note` ?
    R: Un module peut apparaitre dans plusieurs notes.
53. Q: Peut-on modifier la note apres creation ?
    R: Oui, via un setter valide.
54. Q: Pourquoi lancer une exception si note = 25 ?
    R: Parce que la valeur sort de la plage autorisee.
55. Q: Que montre un `try/catch` dans ce cours ?
    R: La gestion d'erreurs de validation en execution.
56. Q: `isBlank()` et `isEmpty()` sont-ils identiques ?
    R: Non, `isBlank()` considere aussi les espaces.
57. Q: Pourquoi utiliser `trim()` sur les entrees texte ?
    R: Pour supprimer les espaces inutiles.
58. Q: Une methode peut-elle etre surchargee ?
    R: Oui, si la liste de parametres change.
59. Q: Changer seulement le type de retour suffit pour surcharger ?
    R: Non.
60. Q: Que definit la signature d'une methode ?
    R: Nom + types/ordre des parametres.
61. Q: Une classe Java publique doit-elle etre dans un fichier du meme nom ?
    R: Oui.
62. Q: Peut-on avoir plusieurs classes publiques dans un meme fichier ?
    R: Non.
63. Q: A quoi sert le package ?
    R: A organiser le code et eviter les conflits de noms.
64. Q: Convention de nommage de classe ?
    R: UpperCamelCase.
65. Q: Convention de nommage de methode et attribut ?
    R: lowerCamelCase.
66. Q: Pourquoi `Main` contient `main(String[] args)` ?
    R: C'est le point d'entree du programme.
67. Q: En Java, la memoire est-elle liberee manuellement comme en C++ classique ?
    R: Non, le garbage collector gere la liberation.
68. Q: Existe-t-il un destructeur comme en C++ ?
    R: Non, pas au meme usage.
69. Q: Java supporte-t-il l'heritage multiple de classes ?
    R: Non.
70. Q: Java supporte-t-il plusieurs interfaces ?
    R: Oui.
71. Q: L'encapsulation Java ressemble-t-elle a C++ ?
    R: Oui, meme principe general.
72. Q: Java utilise-t-il des fichiers header `.h` comme C++ ?
    R: Non.
73. Q: Peut-on acceder a un attribut `private` depuis `Main` ?
    R: Non.
74. Q: Peut-on acceder a un attribut `public` depuis `Main` ?
    R: Oui.
75. Q: Pourquoi eviter les attributs `public` dans un modele metier ?
    R: Ils cassent le controle des regles.
76. Q: Que se passe-t-il si on oublie de valider un setter ?
    R: L'objet peut entrer dans un etat invalide.
77. Q: Quand creer un constructeur vide ?
    R: Quand on veut autoriser une creation avec valeurs par defaut.
78. Q: Quand eviter un constructeur vide ?
    R: Quand certains champs sont obligatoires.
79. Q: Quel type utiliser pour une note decimale ?
    R: `double`.
80. Q: Pourquoi une reference `Etudiant` dans `Note` peut etre `final` ?
    R: Pour ne pas changer l'etudiant associe apres creation.
81. Q: `Etudiant.getCompteur()` est-il correct ?
    R: Oui, si `getCompteur` est static.
82. Q: Appeler `e1.getCompteur()` est-il possible ?
    R: Oui techniquement, mais moins clair.
83. Q: Vrai ou faux: `static` appartient a l'objet.
    R: Faux.
84. Q: Vrai ou faux: un constructeur peut avoir un nom different de la classe.
    R: Faux.
85. Q: Vrai ou faux: un attribut `private` est visible partout dans le projet.
    R: Faux.
86. Q: Vrai ou faux: `public` est le niveau d'acces le plus ouvert.
    R: Vrai.
87. Q: Vrai ou faux: une methode d'instance a besoin d'un objet.
    R: Vrai.
88. Q: Vrai ou faux: une methode `static` peut etre appelee sans objet.
    R: Vrai.
89. Q: Que produit `new Etudiant("2026-001")` avec un constructeur surcharge ?
    R: Un etudiant avec des valeurs par defaut completes par le constructeur.
90. Q: Pourquoi enchainer des constructeurs avec `this(...)` ?
    R: Pour eviter la duplication de code.
91. Q: Pourquoi une validation doit se trouver dans setter et constructeur ?
    R: Pour garantir la coherence a toute creation/modification.
92. Q: Que signifie `NullPointerException` ?
    R: Utilisation d'une reference `null` comme un objet.
93. Q: Comment prevenir des `null` invalides dans le modele ?
    R: Tester les parametres et lever une exception.
94. Q: `==` et `.equals()` sont-ils identiques pour `String` ?
    R: Non, `.equals()` compare le contenu.
95. Q: Peut-on redefinir une methode heritee ?
    R: Oui, avec l'override.
96. Q: Pourquoi redefinir `afficher()` dans `Etudiant` ?
    R: Pour un affichage specifique a l'etudiant.
97. Q: Qu'est-ce qu'une API de classe ?
    R: L'ensemble des methodes publiques exposees.
98. Q: Quelle est la responsabilite d'une classe propre ?
    R: Une responsabilite claire et limitee.
99. Q: Pourquoi eviter la classe fourre-tout ?
    R: Elle devient difficile a maintenir et tester.
100. Q: Une classe `Note` doit-elle connaitre la saisie clavier ?
     R: Non, elle doit rester metier.
101. Q: Pourquoi separer package `model` et package `app` ?
     R: Pour separer logique metier et execution.
102. Q: Qu'est-ce qu'un objet coherent ?
     R: Un objet dont les attributs respectent toutes les regles metier.
103. Q: Pourquoi une exception est preferable a une correction silencieuse ?
     R: Elle signale clairement l'erreur au developpeur.
104. Q: Peut-on encapsuler sans setter ?
     R: Oui, avec des attributs en lecture seule.
105. Q: Quelle est la difference entre validation syntaxique et metier ?
     R: Syntaxique verifie le format, metier verifie la regle du domaine.
106. Q: Exemple de validation syntaxique dans ce cours ?
     R: Format simple de l'email.
107. Q: Exemple de validation metier dans ce cours ?
     R: Note entre 0 et 20.
108. Q: Peut-on instancier une classe sans `new` ?
     R: Pas dans le cas general d'une classe classique.
109. Q: Une methode `static` peut-elle etre surchargee ?
     R: Oui.
110. Q: Une methode `static` peut-elle etre redefinie polymorphiquement ?
     R: Non, elle est masquee, pas polymorphe comme une methode d'instance.
111. Q: Pourquoi afficher `Etudiant.getCompteur()` dans `main` ?
     R: Pour illustrer la donnee partagee de classe.
112. Q: Pourquoi montrer un cas invalide en TP ?
     R: Pour prouver que les validations protegent le modele.
113. Q: Quel est le benefice de `private final` sur une reference ?
     R: Stabilite de l'association apres construction.
114. Q: Peut-on changer l'objet interne d'une reference final ?
     R: Non, la reference ne change pas.
115. Q: Peut-on modifier l'etat interne de l'objet reference par un final ?
     R: Oui, si ses methodes l'autorisent.
116. Q: Que signifie "POO version Java" pour un etudiant venant de C++ ?
     R: Meme logique objet, syntaxe et regles de langage differents.
117. Q: Pourquoi Java impose plus souvent getters/setters explicites ?
     R: Pour formaliser l'API et le controle d'acces.
118. Q: Quel est l'objectif principal du mini-modele Etudiant-Module-Note ?
     R: Appliquer classe, objet, encapsulation, constructeur et static dans un cas reel.
119. Q: Quelle notion du cours 2 prepare directement le cours 3 ?
     R: La structure de classes et la visibilite pour l'heritage.
120. Q: Quelle est la meilleure regle pratique de ce cours ?
     R: Commencer par un modele simple, encapsule et valide.
