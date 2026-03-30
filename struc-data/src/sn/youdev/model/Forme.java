package sn.youdev.model;

public interface Forme  extends Comparable<Forme> {
    Double surface();
    Double perimetre();

    @Override
    default int compareTo(Forme autre) {
        int comparaisonSurface = Double.compare(this.surface(), autre.surface());
        if (comparaisonSurface != 0) {
            return comparaisonSurface;
        }

        int comparaisonPerimetre = Double.compare(this.perimetre(), autre.perimetre());
        if (comparaisonPerimetre != 0) {
            return comparaisonPerimetre;
        }

        return this.getClass().getSimpleName().compareTo(autre.getClass().getSimpleName());
    }
}