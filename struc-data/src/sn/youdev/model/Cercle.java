package sn.youdev.model;

import java.util.Objects;

public class Cercle implements Forme {
    private final Point centre;
    private final Double rayon;

    public Cercle(Point centre, Double rayon) {
        if (centre == null) {
            throw new IllegalArgumentException("Le centre ne peut pas être null.");
        }
        if (rayon == null || rayon <= 0) {
            throw new IllegalArgumentException("Le rayon doit être strictement positif.");
        }

        this.centre = centre;
        this.rayon = rayon;
    }

    @Override
    public Double surface() {
        return Math.PI * rayon * rayon;
    }

    @Override
    public Double perimetre() {
        return 2 * Math.PI * rayon;
    }

    @Override
    public String toString() {
        return "Cercle{" +
                "centre=" + centre +
                ", rayon=" + rayon +
                ", surface=" + surface() +
                ", perimetre=" + perimetre() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cercle cercle)) return false;
        return Objects.equals(centre, cercle.centre)
                && Objects.equals(rayon, cercle.rayon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centre, rayon);
    }
}