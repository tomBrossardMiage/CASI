package org.example;

public interface GestionLogement {
    /*
    * Ajouter un nouveau logement
    */
    public void ajouterLogement();

    /*
    * Modifier un logement existant
    */
    public void modifierLogement(int prix);

    /*
    * Supprimer un logement existant
    */
    public void supprimerLogement();
}
