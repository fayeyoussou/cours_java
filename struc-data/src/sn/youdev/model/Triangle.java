package sn.youdev.model;

import sn.youdev.utils.GeometrieUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Triangle implements Forme {
    protected final Point a;
    protected final Point b;
    protected final Point c;

    public Triangle(Point a, Point b, Point c) {
        verifierPoints(a, b, c);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private void verifierPoints(Point a, Point b, Point c) {
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException("Les points du triangle ne peuvent pas être null.");
        }

        Set<Point> points = new HashSet<>();
        points.add(a);
        points.add(b);
        points.add(c);

        if (points.size() != 3) {
            throw new IllegalArgumentException("Un triangle doit avoir 3 points distincts.");
        }

        if (GeometrieUtils.sontAlignes(a, b, c)) {
            throw new IllegalArgumentException("Les trois points sont alignés, ce n'est pas un triangle.");
        }
    }

    protected double coteAB() {
        return GeometrieUtils.distance(a, b);
    }

    protected double coteBC() {
        return GeometrieUtils.distance(b, c);
    }

    protected double coteCA() {
        return GeometrieUtils.distance(c, a);
    }

    @Override
    public Double surface() {
        return GeometrieUtils.aireTriangle(a, b, c);
    }

    @Override
    public Double perimetre() {
        return coteAB() + coteBC() + coteCA();
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", surface=" + surface() +
                ", perimetre=" + perimetre() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Triangle triangle)) return false;
        return Objects.equals(a, triangle.a)
                && Objects.equals(b, triangle.b)
                && Objects.equals(c, triangle.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}