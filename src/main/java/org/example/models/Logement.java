package org.example.models;

public class Logement {
    private int id;
    private String localisation;
    private int prix;
    private String type;
    private String proprietaire;
    private String loueur;



    public Logement(int id, String localisation, int prix, String type, String proprietaire){
        this.id = id + 1;
        this.localisation = localisation;
        this.prix = prix;
        this.type = type;
        this.proprietaire = proprietaire;
        this.loueur = null;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getId() {
        return id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getLoueur() {
        return loueur;
    }

    public void setLoueur(String loueur) {
        this.loueur = loueur;
    }

}
