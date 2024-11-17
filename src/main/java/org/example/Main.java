package org.example;

import org.example.Impl.Menu;
import org.example.models.Utilisateur;
import org.example.services.UtilisateurService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateurConnecte;
        Scanner scanner = UtilitaireScanner.getScanner();

        while (true) {
            System.out.println("=== Bienvenue sur l'application ===");
            System.out.println("1. Se connecter");
            System.out.println("2. S'inscrire");
            System.out.println("Tapez 'exit' pour quitter l'application");

            System.out.print("Veuillez entrer votre choix (1, 2 ou 'exit'): ");
            String choix = scanner.nextLine();

            if (choix.equals("1")) {
                utilisateurConnecte = utilisateurService.seConnecter();
                Menu.afficherChoixDeNavigation(utilisateurConnecte);
            } else if (choix.equals("2")) {
                utilisateurConnecte = utilisateurService.creerCompte();
                Menu.afficherChoixDeNavigation(utilisateurConnecte);
            } else if (choix.equals("exit")) {
                System.out.println("Merci d'avoir utilisé l'application. Au revoir!");
                break;
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
                break;
            }
        }

        scanner.close();
    }
}
