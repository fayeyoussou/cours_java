package sn.youdev.model;

import sn.youdev.utils.GeometrieUtils;

public class TriangleIsocele extends Triangle {

    public TriangleIsocele(Point a, Point b, Point c) {
        super(a, b, c);

        double ab = coteAB();
        double bc = coteBC();
        double ca = coteCA();

        boolean isocele =
                GeometrieUtils.presqueEgal(ab, bc) ||
                GeometrieUtils.presqueEgal(bc, ca) ||
                GeometrieUtils.presqueEgal(ca, ab);

        if (!isocele) {
            throw new IllegalArgumentException("Les points fournis ne forment pas un triangle isocèle.");
        }
    }

    @Override
    public String toString() {
        return "TriangleIsocele{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", surface=" + surface() +
                ", perimetre=" + perimetre() +
                '}';
    }
}