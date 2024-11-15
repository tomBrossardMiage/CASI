package org.example;

public interface logement {

    /*
    * Afficher les logements d'une personne
    */
    public void afficherMesLogements();

    /*
    * Afficher les logements pour lequels l'utilisateur est le loueur actuel
    */
    public void afficherMesLocations(String pseudo);
}
