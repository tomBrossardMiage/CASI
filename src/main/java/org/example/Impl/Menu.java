package org.example.Impl;

import org.example.UtilitaireScanner;
import org.example.models.Utilisateur;
import org.example.services.LogementService;

import java.util.Scanner;

public class Menu {
    static Scanner scanner = UtilitaireScanner.getScanner();
    private static LogementImpl logement = new LogementImpl();
    private static VoyageImpl voyage = new VoyageImpl();
    private static LogementService logementService = new LogementService();

    private static GestionReservationLogementImpl gestionResLogement = new GestionReservationLogementImpl();

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
                logement.afficherLogements();
            } else if (choix.equals("2")) {
                voyage.afficherVoyages();
                break;
            } else if (choix.equals("3")) {
                System.out.println("== Mes reservations ==");
                System.out.println("Choisir entre 1 ou 2");
                System.out.println("1 - Mes logements réservés");
                System.out.println("2 - Mes voyages réservés");
                String choix2 = scanner.nextLine();
                if(choix2.equals("1")){
                    System.out.println("Entre ton pseudo");
                    //afficher logements que l'utilisateur à reservé
                } else if (choix2.equals("2")) {
                    System.out.println("Entre ton pseudo");
                    //afficher les voyage pour lequels le pseudo est identique à la personne qui à voyagé
                }
                afficherChoixDeNavigation(user);
                break;
            } else if (choix.equals("4")) {
                logementService.ajouterLogement();
                afficherChoixDeNavigation(user);
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
