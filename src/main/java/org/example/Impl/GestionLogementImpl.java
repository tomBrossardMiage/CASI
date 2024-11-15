package org.example.Impl;

import org.example.GestionLogement;
import org.example.UtilitaireScanner;

import java.util.Scanner;

public class GestionLogementImpl implements GestionLogement {
    static Scanner scanner = UtilitaireScanner.getScanner();
    public void ajouterLogement() {
        System.out.println("== Ajout d'un nouveau logement ==");
        System.out.println("Entrer la localisation du logement :");
        String localisation = scanner.nextLine();
        System.out.println("Entrer le prix du logement à la nuit:");
        String prix = scanner.nextLine();
        System.out.println("Entrer le type du logement (maison, appartement):");
        String type = scanner.nextLine();
        System.out.println("Entrer votre pseudo :");
        String pseudo = scanner.nextLine();
        new LogementImpl(localisation, prix, type, pseudo);
        System.out.println("Votre logement à été ajouté ! Bravo");
    }

    public void modifierLogement(int prix) {
        //utilise dans details d'un logement
    }
    public void supprimerLogement() {
        //utilise dans details d'un logement
    }
}
