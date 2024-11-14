package org.example;

public interface gestionLogement {

    /*Ajouter un logement*/
    public void ajouterLogement(String localisation, String disponibilite, int prix, String typeDeLogement, String image);

    /*Modifier le logement*/
    public void modifierLogement(int prix);

    /*Supprimer le logement*/
    public void supprimerLogement();

}
