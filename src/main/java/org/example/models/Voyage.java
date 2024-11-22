package org.example.models;

public class Voyage {
    private int id;
    private String localisation;
    private String locataire;
    private int prix;

    public Voyage (String localisation, int prix){
        this.localisation = localisation;
        this.prix = prix;
        this.locataire = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getLocataire() {
        return locataire;
    }

    public void setLocataire(String locataire) {
        this.locataire = locataire;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
