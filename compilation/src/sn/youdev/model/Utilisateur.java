package sn.youdev.model;

public class Utilisateur {
    private String login;
    private String role;

    public Utilisateur(String login) {
        this(login, "ETUDIANT");
    }

    public Utilisateur(String login, String role) {
        setLogin(login);
        setRole(role);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login invalide");
        }
        this.login = login.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role invalide");
        }
        this.role = role.trim().toUpperCase();
    }

    public boolean estAdmin() {
        return "ADMIN".equals(role);
    }
}
