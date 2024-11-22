package org.example;

import org.example.Impl.Menu;
import org.example.models.Utilisateur;
import org.example.services.UtilisateurService;

import java.util.Scanner;

public class Main {
    private static final String USERS_FILE = "src/main/resources/utilisateur.json";
    private static final String USERS_FILE_TEST = "src/main/resources/utilisateurTest.json";

    private static final String LOGEMENT_FILE_TEST = "src/main/resources/logementTest.json";
    private static final String VOYAGE_FILE = "src/main/resources/voyage.json";
    private static final String VOYAGE_FILE_TEST = "src/main/resources/voyageTest.json";

    public static void main(String[] args) {

        UtilisateurService utilisateurService = new UtilisateurService(USERS_FILE);
        Utilisateur utilisateurConnecte = null;
        Scanner scanner = UtilitaireScanner.getScanner();
        String pseudo;
        String mdp;
        do{
            System.out.println("=== Bienvenue sur l'application ===");
            System.out.println("1. Se connecter");
            System.out.println("2. S'inscrire");
            System.out.println("Tapez 'exit' pour quitter l'application");

            System.out.print("Veuillez entrer votre choix (1, 2 ou 'exit'): ");
            String choix = scanner.nextLine();

            if (choix.equals("1")) {
                System.out.println("Veuillez entrer votre pseudo : ");
                pseudo = scanner.nextLine();
                System.out.println("Veuillez entrer votre mot de passe : ");
                mdp = scanner.nextLine();
                utilisateurConnecte = utilisateurService.seConnecter(pseudo, mdp);
                Menu.afficherChoixDeNavigation(utilisateurConnecte);
                utilisateurConnecte = null;
            } else if (choix.equals("2")) {
                System.out.println("Veuillez entrer votre pseudo : ");
                pseudo = scanner.nextLine();
                System.out.println("Veuillez entrer votre mot de passe : ");
                mdp = scanner.nextLine();
                utilisateurConnecte = utilisateurService.creerCompte(pseudo, mdp);
                Menu.afficherChoixDeNavigation(utilisateurConnecte);
                utilisateurConnecte = null;
            } else if (choix.equals("exit")) {
                System.out.println("Merci d'avoir utilisé l'application. Au revoir!\n\n\n");
                break;
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }while(utilisateurConnecte == null);

        scanner.close();
    }
}
