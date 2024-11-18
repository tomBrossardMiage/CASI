package org.example.models;

public class Utilisateur {
    private static int id = 1;
    private String pseudo;
    private String password;

    public Utilisateur(String pseudo, String password) {
        id = getId();
        this.pseudo = pseudo;
        this.password = password;
        id++;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        Utilisateur.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                '}';
    }
}

