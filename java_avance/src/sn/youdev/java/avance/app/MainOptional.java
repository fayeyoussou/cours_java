package sn.youdev.java.avance.app;

import sn.youdev.java.avance.enums.MentionEnumAvance;

import java.util.Optional;

public class MainOptional {
    public static void main(String[] args) {
        // Optional évite de manipuler directement une valeur nulle.
        // On peut l'utiliser pour représenter un résultat trouvé ou absent.
        Optional<MentionEnumAvance> mentionTexte = MentionEnumAvance.of("bien");
        Optional<MentionEnumAvance> mentionNote = MentionEnumAvance.fromNote(9);
        Optional<MentionEnumAvance> mentionInconnue = MentionEnumAvance.of("excellent");

        // isPresent vérifie si une valeur existe.
        IO.println("Texte trouvé : " + mentionTexte.isPresent());

        // isEmpty vérifie si l'Optional est vide.
        IO.println("Texte inconnu : " + mentionInconnue.isEmpty());

        // ifPresent exécute une action seulement si la valeur existe.
        mentionTexte.ifPresent(mention -> IO.println("Mention trouvée : " + mention.getValeur()));

        // map transforme la valeur si elle est présente.
        IO.println("Depuis une note : " + mentionNote.map(MentionEnumAvance::getValeur).orElse("Aucune"));

        // orElse prépare la valeur par défaut tout de suite, même si elle ne sert pas.
        IO.println("Avec orElse : " + mentionInconnue.orElse(MentionEnumAvance.NON_ADMIS));

        // orElseGet calcule la valeur par défaut seulement si l'Optional est vide.
        IO.println("Avec orElseGet : " + mentionInconnue.orElseGet(() -> MentionEnumAvance.NON_ADMIS));

        // orElseThrow lève une exception si rien n'est présent.
        MentionEnumAvance recupere = mentionTexte.orElseThrow(() -> new IllegalArgumentException("Mention inconnue"));
        IO.println("Avec orElseThrow : " + recupere.getValeur());
    }
}
