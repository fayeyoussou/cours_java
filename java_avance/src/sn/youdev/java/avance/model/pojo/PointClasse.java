package sn.youdev.java.avance.model.pojo;

import java.util.Objects;

/**
 *      POJO veut dire Plain Old Java Object.
 *      Quand une classe sert seulement à stocker des données, on doit souvent écrire beaucoup de code répétitif :
 * 	•	les attributs
 * 	•	le constructeur
 * 	•	les getters
 * 	•	parfois equals()
 * 	•	parfois hashCode()
 * 	•	parfois toString()
 */
class PointClasse {
    private final int x;
    private final int y;

    public PointClasse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PointClasse that)) return false;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PointClasse{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}