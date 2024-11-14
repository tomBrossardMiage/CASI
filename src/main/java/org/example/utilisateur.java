package org.example;

public interface utilisateur {
    /*Ajouter un logement*/
    public void ajouterLogement(String localisation, String disponibilite, int prix, String typeDeLogement, String image);

    /*Reserver un logement*/
    public void reserverLogement(logement logement);

    /*ReserverUnVoyage*/
    public void reserverVoyage(voyage voyage);

    /**/
}
