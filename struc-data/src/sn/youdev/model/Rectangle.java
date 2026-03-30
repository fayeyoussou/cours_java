package sn.youdev.model;

import sn.youdev.utils.GeometrieUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Rectangle implements Forme {
    protected final Point a;
    protected final Point b;
    protected final Point c;
    protected final Point d;

    public Rectangle(Point a, Point b, Point c, Point d) {
        verifierPointsNonNull(a, b, c, d);
        verifierPointsDistincts(a, b, c, d);
        verifierRectangle(a, b, c, d);

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    private void verifierPointsNonNull(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null) {
            throw new IllegalArgumentException("Les points du rectangle ne peuvent pas être null.");
        }
    }

    private void verifierPointsDistincts(Point a, Point b, Point c, Point d) {
        Set<Point> points = new HashSet<>();
        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);

        if (points.size() != 4) {
            throw new IllegalArgumentException("Un rectangle doit avoir 4 points distincts.");
        }
    }

    private void verifierRectangle(Point a, Point b, Point c, Point d) {
        double ab = GeometrieUtils.distance(a, b);
        double bc = GeometrieUtils.distance(b, c);
        double cd = GeometrieUtils.distance(c, d);
        double da = GeometrieUtils.distance(d, a);

        double ac = GeometrieUtils.distance(a, c);
        double bd = GeometrieUtils.distance(b, d);

        boolean cotesOpposesEgaux =
                GeometrieUtils.presqueEgal(ab, cd) &&
                GeometrieUtils.presqueEgal(bc, da);

        boolean diagonalesEgales = GeometrieUtils.presqueEgal(ac, bd);

        boolean angleDroit = GeometrieUtils.estAngleDroit(a, b, c);

        if (!(cotesOpposesEgaux && diagonalesEgales && angleDroit)) {
            throw new IllegalArgumentException("Les points fournis ne forment pas un rectangle valide.");
        }
    }

    protected double longueur() {
        return GeometrieUtils.distance(a, b);
    }

    protected double largeur() {
        return GeometrieUtils.distance(b, c);
    }

    @Override
    public Double surface() {
        return longueur() * largeur();
    }

    @Override
    public Double perimetre() {
        return 2 * (longueur() + largeur());
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rectangle rectangle)) return false;
        return Objects.equals(a, rectangle.a)
                && Objects.equals(b, rectangle.b)
                && Objects.equals(c, rectangle.c)
                && Objects.equals(d, rectangle.d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }
    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", longueur=" + longueur() +
                ", largeur=" + largeur() +
                ", surface=" + surface() +
                ", perimetre=" + perimetre() +
                '}';
    }
}