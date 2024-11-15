package org.example.Impl;

import org.example.UtilitaireScanner;

import java.util.Scanner;

public class Menu {
    static Scanner scanner = UtilitaireScanner.getScanner();

    public static void afficherChoixDeNavigation() {
        System.out.println("== MENU ==");
        System.out.println("1 - Logements disponibles");
        System.out.println("2 - Voyages disponibles");
        System.out.println("3 - Mes reservations");
        System.out.println("4 - Ajouter un logement");
        System.out.println("5 - Deconnexion");
        String choix = scanner.nextLine();
        while(true){
            if (choix.equals("1")) {
                LogementImpl.afficherLogements();
            } else if (choix.equals("2")) {
                VoyageImpl.afficherVoyages();
            } else if (choix.equals("3")) {
                break;
            } else if (choix.equals("4")) {
                break;
            } else if (choix.equals("5")) {
                break;
            } else if (choix.equals("exit")) {
                System.out.println("Merci d'avoir utilisé l'application. Au revoir!");
                break;
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }

}
