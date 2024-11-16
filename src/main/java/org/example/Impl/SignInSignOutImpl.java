package org.example.Impl;

import org.example.UtilitaireScanner;
import org.example.signInsignOut;

import java.util.*;

public class SignInSignOutImpl implements signInsignOut {
    private static HashMap<String, String> utilisateur;
    private static String utilisateurConnecte = null; // Utilisateur actuellement connecté
    private static String dernierInscrit = null;     // Dernier utilisateur inscrit
    private Scanner scanner = UtilitaireScanner.getScanner();

    public SignInSignOutImpl() {
        utilisateur = new HashMap<>();
    }

    @Override
    public boolean creerCompte() {
        String pseudo;
        do {
            System.out.println("Veuillez entrer votre pseudo : ");
            pseudo = scanner.nextLine();
            if (utilisateur.containsKey(pseudo)) {
                System.out.println("Pseudo déjà existant, veuillez rentrer un nouveau pseudo");
            }
        } while (utilisateur.containsKey(pseudo));

        System.out.println("Veuillez entrer votre mot de passe : ");
        String mdp = scanner.nextLine();
        utilisateur.put(pseudo, mdp);
        dernierInscrit = pseudo; // Enregistre le dernier inscrit
        System.out.println("Inscription validée, bienvenue sur l'application");
        Menu.afficherChoixDeNavigation();
        return true;
    }

    @Override
    public boolean seConnecter() {
        String pseudo;
        String mdp;
        do {
            System.out.println("Veuillez entrer votre pseudo : ");
            pseudo = scanner.nextLine();
            if (!utilisateur.containsKey(pseudo)) {
                System.out.println("Pseudo inconnu, veuillez rentrer un nouveau pseudo");
            }
        } while (!utilisateur.containsKey(pseudo));

        do {
            System.out.println("Veuillez entrer votre mot de passe : ");
            mdp = scanner.nextLine();
            if (!Objects.equals(utilisateur.get(pseudo), mdp)) {
                System.out.println("Mot de passe incorrect, veuillez entrer le bon mot de passe");
            }
        } while (!Objects.equals(utilisateur.get(pseudo), mdp));

        utilisateurConnecte = pseudo; // Enregistre l'utilisateur connecté
        System.out.println("Connexion réussie, bienvenue sur l'application");
        Menu.afficherChoixDeNavigation();
        return true;
    }

    public static boolean seDeconnecter() {
        if (utilisateurConnecte != null) {
            System.out.println("Déconnexion réussie, au revoir " + utilisateurConnecte + " !");
            utilisateurConnecte = null; // Réinitialiser l'utilisateur connecté
            return true;
        } else if (dernierInscrit != null) {
            System.out.println("Déconnexion réussie, au revoir " + dernierInscrit + " !");
            dernierInscrit = null; // Réinitialiser le dernier inscrit pour éviter les doublons
            return true;
        } else {
            System.out.println("Aucun utilisateur n'est actuellement connecté.");
            return false;
        }
    }
}
