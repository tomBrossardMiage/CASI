package org.example.Impl;

import org.example.UtilitaireScanner;
import org.example.logement;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LogementImpl implements logement {
    private static int compteur = 1;
    private static HashMap<Integer, LogementImpl> listeLogement = new HashMap<>();
    private static Scanner scanner = UtilitaireScanner.getScanner();

    private String localisation;
    private String prix;
    private String type;
    private String proprietaire;
    private String loueur;

    public LogementImpl(String localisation, String prix, String type, String proprietaire){
        this.localisation = localisation;
        this.prix = prix;
        this.type = type;
        this.proprietaire = proprietaire;
        this.loueur = "empty";
        listeLogement.put(compteur, this);
        compteur +=1;
    }

    public LogementImpl(){

    }

    public HashMap<Integer, LogementImpl> getListeLogement(){
        return listeLogement;
    }
    public String getLocalisation() {
        return localisation;
    }

    public String getPrix() {
        return prix;
    }

    public String getType() {
        return type;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public String getLoueur() {
        return loueur;
    }

    public void afficherLogements() {
        System.out.println("== Liste des logements ==\n");

        for (Map.Entry<Integer, LogementImpl> entry : listeLogement.entrySet()){
            System.out.println("Logement n°"+entry.getKey()+" - Localisation : "+entry.getValue().getLocalisation()+" - Prix : "+entry.getValue().getPrix()+" - Type de logement : "+entry.getValue().getType()+" - Propriétaire : "+entry.getValue().getProprietaire()+" - Loueur : "+entry.getValue().getLoueur()+"\n");
        }
        System.out.println("Pour afficher les détails d'un logement, entrer le numéro de ce dernier.\nTaper 'retour' pour revenir au menu");
        while (true) {
            String choix = scanner.nextLine();
            if (choix.equals("retour")) {
                Menu.afficherChoixDeNavigation();
                break;
            } else {
                try {
                    int numeroLogement = Integer.parseInt(choix);

                    if (listeLogement.containsKey(numeroLogement)) {
                        System.out.println("Détails du logement n°" + numeroLogement + " : " + listeLogement.get(numeroLogement));
                    } else {
                        System.out.println("Numéro de logement non trouvé.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Entrez un numéro de logement ou 'retour' pour revenir au menu.");
                }
            }
        }
    }
    public void afficherMesLogements() {

    }
    @Override
    public void afficherMesLocations(String pseudo){
        for (Map.Entry<Integer, LogementImpl> entry : listeLogement.entrySet()){
            if(entry.getValue().getLoueur().equals(pseudo)){
                System.out.println("Logement n°"+entry.getKey()+" - Localisation : "+entry.getValue().getLocalisation()+" - Prix : "+entry.getValue().getPrix()+" - Type de logement : "+entry.getValue().getType()+" - Propriétaire : "+entry.getValue().getProprietaire()+" - Loueur : "+entry.getValue().getLoueur()+"\n");
            }
        }
    }
}
