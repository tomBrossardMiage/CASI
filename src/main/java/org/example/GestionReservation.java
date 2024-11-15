package org.example;

public interface GestionReservation {

    /*
     * Afficher mes reservation
     */
    public void mesReservations(String pseudo);

    /*Reserver un logement*/
    public void reserverLogement();

    /*Reserver un voyage*/
    public void reserverVoyage();
}
