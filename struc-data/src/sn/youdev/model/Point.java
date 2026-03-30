package sn.youdev.model;

import java.util.Objects;

public class Point implements Comparable<Point>{
    private final Double x;
    private final Double y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }
    @Override
    public int compareTo(Point autre) {
        int comparaisonX = Double.compare(this.x, autre.x);
        if (comparaisonX != 0) {
            return comparaisonX;
        }
        return Double.compare(this.y, autre.y);
    }
    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "( " + x +
                "," + y +
                " )";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point point)) return false;
        return Objects.equals(x, point.x) && Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
