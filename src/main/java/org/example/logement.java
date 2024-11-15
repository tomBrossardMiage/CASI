package org.example;

public interface logement {

    /*
    * Afficher l'ensemble des logements disponible
    */
    public void afficherLogements();


    /*
    * Afficher les logements d'une personne
    */
    public void afficherMesLogements(utilisateur u);
}
