package org.example.Impl;

import org.example.UtilitaireScanner;
import org.example.Voyage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VoyageImpl implements Voyage {
    private static int compteur = 1;
    private static HashMap<Integer, ArrayList<String>> listeVoyage = new HashMap<>();
    private static Scanner scanner = UtilitaireScanner.getScanner();
    private ArrayList<String> Voyage = new ArrayList<>();

    public VoyageImpl(String nom, String localisation, String pseudo){
        Voyage.add(nom);
        Voyage.add(localisation);
        Voyage.add(pseudo);
        listeVoyage.put(compteur, Voyage);
        compteur +=1;
    }

    public static void afficherLogements() {
        System.out.println("== Liste des logements ==\n");

        for (Map.Entry<Integer, ArrayList<String>> entry : listeVoyage.entrySet()){
            System.out.println("Logement n°"+entry.getKey()+" - Nom : "+entry.getValue().get(1));
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

                    if (listeVoyage.containsKey(numeroLogement)) {
                        System.out.println("Détails du logement n°" + numeroLogement + " : " + listeVoyage.get(numeroLogement));
                    } else {
                        System.out.println("Numéro de logement non trouvé.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Entrez un numéro de logement ou 'retour' pour revenir au menu.");
                }
            }
        }
    }

    public void afficherMesVoyages() {

    }
}
