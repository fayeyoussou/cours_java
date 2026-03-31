package sn.youdev.java.avance.model.record;

/**
 * QUAND UTILISER UN RECORD
 * On se dit qu’un record est mieux qu’une classe normale quand l’objet sert surtout à transporter des données et pas à gérer une logique métier complexe.
 *
 * La question à se poser est presque celle-ci :
 *
 * Est-ce que ma classe décrit principalement des valeurs, ou un vrai objet avec beaucoup de comportement ?
 */
public record Point(int x, int y) {}
/**
 * Cette seule ligne remplace une grande partie du code précédent.
 *
 * Java génère automatiquement :
 * 	•	le constructeur
 * 	•	les accesseurs
 * 	•	equals()
 * 	•	hashCode()
 * 	•	toString()
 */
