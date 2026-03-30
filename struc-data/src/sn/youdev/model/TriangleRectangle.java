package sn.youdev.model;

import sn.youdev.utils.GeometrieUtils;

public class TriangleRectangle extends Triangle {

    public TriangleRectangle(Point a, Point b, Point c) {
        super(a, b, c);

        boolean angleDroitEnA = GeometrieUtils.estAngleDroit(b, a, c);
        boolean angleDroitEnB = GeometrieUtils.estAngleDroit(a, b, c);
        boolean angleDroitEnC = GeometrieUtils.estAngleDroit(a, c, b);

        if (!(angleDroitEnA || angleDroitEnB || angleDroitEnC)) {
            throw new IllegalArgumentException("Les points fournis ne forment pas un triangle rectangle.");
        }
    }

    @Override
    public String toString() {
        return "TriangleRectangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", surface=" + surface() +
                ", perimetre=" + perimetre() +
                '}';
    }
}