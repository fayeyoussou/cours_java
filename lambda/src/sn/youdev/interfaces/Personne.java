package sn.youdev.interfaces;

public class Personne {
    int id;

    public Personne(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Personne{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
