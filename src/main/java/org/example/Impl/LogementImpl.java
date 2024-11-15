package org.example.Impl;

import org.example.logement;
import org.example.utilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LogementImpl implements logement {
    private static int compteur = 1;
    private static HashMap<Integer, ArrayList<String>> listeLogement = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    private ArrayList<String> logement = new ArrayList<>();

    public LogementImpl(String nom, String localisation, String pseudo){
        logement.add(nom);
        logement.add(localisation);
        logement.add(pseudo);
        listeLogement.put(compteur, logement);
        compteur +=1;
    }
    @Override
    public void afficherLogements() {
        System.out.println("== Liste des logements ==\n");

        for (Map.Entry<Integer, ArrayList<String>> entry : listeLogement.entrySet()){
            System.out.println("Logement n°"+entry.getKey()+" - Nom : "+entry.getValue().get(1));
        }
        System.out.println("Pour afficher les détailles d'un logement, entrer le numéro de ce dernier.");
        int choix = scanner.nextInt();
    }

    @Override
    public void afficherMesLogements(utilisateur u) {

    }
}
