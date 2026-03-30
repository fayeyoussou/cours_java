package sn.youdev.model;

import sn.youdev.utils.GeometrieUtils;

public class Carre extends Rectangle {

    public Carre(Point a, Point b, Point c, Point d) {
        super(a, b, c, d);

        double ab = GeometrieUtils.distance(a, b);
        double bc = GeometrieUtils.distance(b, c);
        double cd = GeometrieUtils.distance(c, d);
        double da = GeometrieUtils.distance(d, a);

        boolean tousLesCotesEgaux =
                GeometrieUtils.presqueEgal(ab, bc) &&
                GeometrieUtils.presqueEgal(bc, cd) &&
                GeometrieUtils.presqueEgal(cd, da);

        if (!tousLesCotesEgaux) {
            throw new IllegalArgumentException("Les points fournis ne forment pas un carré valide.");
        }
    }

    @Override
    public String toString() {
        return "Carre{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", cote=" + longueur() +
                ", surface=" + surface() +
                ", perimetre=" + perimetre() +
                '}';
    }
}