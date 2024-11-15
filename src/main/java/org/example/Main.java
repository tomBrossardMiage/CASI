package org.example;

import org.example.Impl.LogementImpl;
import org.example.Impl.SignInSignOutImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SignInSignOutImpl logUser = new SignInSignOutImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Bienvenue sur l'application ===");
            System.out.println("1. Se connecter");
            System.out.println("2. S'inscrire");
            System.out.println("Tapez 'exit' pour quitter l'application");

            System.out.print("Veuillez entrer votre choix (1, 2 ou 'exit'): ");
            String choix = scanner.nextLine();

            if (choix.equals("1")) {
                logUser.seConnecter();
            } else if (choix.equals("2")) {
                logUser.creerCompte();
            } else if (choix.equals("exit")) {
                System.out.println("Merci d'avoir utilisé l'application. Au revoir!");
                break;
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
            }
        }

        scanner.close();
    }
}
