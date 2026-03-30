package sn.youdev.utils;

import sn.youdev.model.Point;

/**
 * Classe utilitaire contenant des méthodes statiques
 * pour effectuer des calculs géométriques de base dans le plan.
 *
 * <p>Cette classe ne doit pas être instanciée car elle ne contient
 * que des méthodes utilitaires.</p>
 */
public final class GeometrieUtils {

    /**
     * Tolérance utilisée pour comparer deux nombres réels (double).
     *
     * <p>En géométrie, les calculs sur les nombres décimaux peuvent produire
     * de très petites erreurs. Au lieu de tester a == b, on vérifie donc
     * que la différence entre a et b est très faible.</p>
     */
    private static final double EPSILON = 1e-9;

    /**
     * Constructeur privé pour empêcher l'instanciation
     * de cette classe utilitaire.
     */
    private GeometrieUtils() {
    }

    /**
     * Calcule la distance entre deux points du plan.
     *
     * <p>Formule utilisée :</p>
     * <pre>
     * distance = √((x2 - x1)² + (y2 - y1)²)
     * </pre>
     * distance(A, B) = √((xB - xA)² + (yB - yA)²)
     * @param a le premier point
     * @param b le second point
     * @return la distance entre les points a et b
     */
    public static double distance(Point a, Point b) {
        double dx = a.getX() - b.getX(); // différence sur l'axe des x
        double dy = a.getY() - b.getY(); // différence sur l'axe des y
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calcule le produit scalaire des vecteurs BA et BC.
     *
     * <p>On considère ici :</p>
     * <ul>
     *     <li>BA = A - B</li>
     *     <li>BC = C - B</li>
     * </ul>
     *
     * <p>Formule :</p>
     * <pre>
     * BA . BC = (xA - xB)(xC - xB) + (yA - yB)(yC - yB)
     * </pre>
     *
     * <p>Le produit scalaire est très utile pour savoir si deux segments
     * forment un angle droit :</p>
     * <ul>
     *     <li>si le produit scalaire vaut 0, alors les vecteurs sont perpendiculaires</li>
     * </ul>
     *
     * @param a point A
     * @param b point B, sommet commun des deux vecteurs
     * @param c point C
     * @return le produit scalaire des vecteurs BA et BC
     */
    public static double produitScalaire(Point a, Point b, Point c) {
        // Coordonnées du vecteur BA
        double bax = a.getX() - b.getX();
        double bay = a.getY() - b.getY();

        // Coordonnées du vecteur BC
        double bcx = c.getX() - b.getX();
        double bcy = c.getY() - b.getY();

        // Calcul du produit scalaire BA . BC
        return bax * bcx + bay * bcy;
    }

    /**
     * Vérifie si l'angle ABC est un angle droit.
     *
     * <p>L'angle est droit si les vecteurs BA et BC sont perpendiculaires,
     * donc si leur produit scalaire est nul.</p>
     *
     * <p>Comme on travaille avec des nombres réels, on n'utilise pas
     * une égalité stricte à 0, mais une comparaison avec une tolérance.</p>
     *
     * @param a point A
     * @param b sommet de l'angle
     * @param c point C
     * @return true si l'angle ABC est droit, false sinon
     */
    public static boolean estAngleDroit(Point a, Point b, Point c) {
        return presqueEgal(produitScalaire(a, b, c), 0.0);
    }

    /**
     * Compare deux nombres réels avec une petite tolérance.
     *
     * <p>Cette méthode est utile pour éviter les erreurs liées
     * aux imprécisions des calculs sur les doubles.</p>
     *
     * <p>Exemple :</p>
     * <pre>
     * 0.1 + 0.2 != 0.3 exactement en informatique
     * </pre>
     *
     * <p>Donc au lieu de faire :</p>
     * <pre>
     * a == b
     * </pre>
     *
     * <p>on fait :</p>
     * <pre>
     * |a - b| < EPSILON
     * </pre>
     *
     * @param a premier nombre
     * @param b second nombre
     * @return true si les deux valeurs sont presque égales, false sinon
     */
    public static boolean presqueEgal(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    /**
     * Calcule l'aire d'un triangle défini par trois points.
     *
     * <p>Cette méthode utilise la formule du déterminant :</p>
     * <pre>
     * Aire = | xA(yB - yC) + xB(yC - yA) + xC(yA - yB) | / 2
     * </pre>
     *
     * <p>La valeur absolue est utilisée pour garantir une aire positive,
     * quel que soit l'ordre des points.</p>
     *
     * @param a premier point du triangle
     * @param b deuxième point du triangle
     * @param c troisième point du triangle
     * @return l'aire du triangle
     */
    public static double aireTriangle(Point a, Point b, Point c) {
        return Math.abs(
                a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())
        ) / 2.0;
    }

    /**
     * Vérifie si trois points sont alignés.
     *
     * <p>Trois points sont alignés si l'aire du triangle qu'ils forment est nulle.</p>
     *
     * <p>Comme pour les autres calculs sur les doubles, on utilise
     * une comparaison avec tolérance.</p>
     *
     * @param a premier point
     * @param b deuxième point
     * @param c troisième point
     * @return true si les trois points sont alignés, false sinon
     */
    public static boolean sontAlignes(Point a, Point b, Point c) {
        return presqueEgal(aireTriangle(a, b, c), 0.0);
    }
}