package org.example.Impl;

import org.example.UtilitaireScanner;
import org.example.models.Utilisateur;
import org.example.services.LogementService;
import org.example.services.VoyageService;

import java.util.Objects;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = UtilitaireScanner.getScanner();
    private static LogementService logementService = new LogementService("src/main/resources/logement.json");
    private static VoyageService voyageService = new VoyageService();

    public static void afficherChoixDeNavigation(Utilisateur user) {
        System.out.println("== MENU ==");
        System.out.println("1 - Logements disponibles");
        System.out.println("2 - Voyages disponibles");
        System.out.println("3 - Mes reservations");
        System.out.println("4 - Ajouter un logement");
        System.out.println("5 - Deconnexion");
        String choix = scanner.nextLine();
        while(true){
            if (choix.equals("1")) {
                logementService.afficherLogement(user);
            } else if (choix.equals("2")) {
                voyageService.afficherVoyage(user);
            } else if (choix.equals("3")) {
                System.out.println("== Mes reservations ==");
                System.out.println("Choisir entre 1 ou 2");
                System.out.println("1 - Mes logements réservés");
                System.out.println("2 - Mes voyages réservés");
                String choix2 = scanner.nextLine();
                if(choix2.equals("1")){
                    System.out.println("Entre ton pseudo");
                    logementService.afficherLogementLoue(user);
                } else if (choix2.equals("2")) {
                    System.out.println("Entre ton pseudo");
                    voyageService.afficherVoyageReserve(user);
                }
                //afficherChoixDeNavigation(user);
                break;
            } else if (choix.equals("4")) {
                System.out.println("== Ajout d'un nouveau logement ==");
                System.out.println("Entrer la localisation du logement :");
                String localisation = scanner.nextLine();
                System.out.println("Entrer le prix du logement à la nuit:");
                int prix = Integer.parseInt(scanner.nextLine());
                System.out.println("Entrer le type du logement (maison, appartement):");
                String type = scanner.nextLine();
                System.out.println("Taper 'retour' pour annuler l'ajout et revenir au menu ou 'ok' pour valider l'ajout");
                String confirmation = scanner.nextLine();
                if(Objects.equals(confirmation, "ok")){
                    logementService.ajouterLogement(user, localisation, prix, type);
                }
                afficherChoixDeNavigation(user);
            } else if (choix.equals("5")) {
                System.out.println("Merci d'avoir utilisé l'application. Au revoir!");
                break;
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
    }
}
