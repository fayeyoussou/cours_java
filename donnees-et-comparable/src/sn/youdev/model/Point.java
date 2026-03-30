package sn.youdev.model;

import java.util.Objects;

// Cette classe represente un point dans un plan.
public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // L'ordre naturel commence par x puis par y.
    @Override
    public int compareTo(Point autrePoint) {
        int resultatX = Integer.compare(this.x, autrePoint.x);
        if (resultatX != 0) {
            return resultatX;
        }
        return Integer.compare(this.y, autrePoint.y);
    }

    // Deux points sont egaux si x et y sont les memes.
    @Override
    public boolean equals(Object objet) {
        if (this == objet) {
            return true;
        }
        if (objet == null || getClass() != objet.getClass()) {
            return false;
        }
        Point point = (Point) objet;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
