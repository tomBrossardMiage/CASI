package org.example.Impl;

import org.example.signInsignOut;

import java.util.*;

public class SignInSignOutImpl implements signInsignOut {
    private static HashMap<String, String> utilisateur;

    public SignInSignOutImpl(){
        utilisateur = new HashMap<String, String>();
    }

    @Override
    public boolean creerCompte() {
        Scanner scanner = new Scanner(System.in);
        String pseudo;
        do{ //récupérer un pseudo unique
            System.out.println("Veuillez entrer votre pseudo : ");
            pseudo = scanner.nextLine();
            if(utilisateur.containsKey(pseudo)){
                System.out.println("pseudo déjà existant, veuillez rentrer un nouveau pseudo");
            }
        }while(utilisateur.containsKey(pseudo));

        System.out.println("Veuillez entrer votre mot de passe : ");
        String mdp = scanner.nextLine();
        utilisateur.put(pseudo, mdp);
        System.out.println("Inscription validé, bienvenue sur l'application");

        return true;
    }

    @Override
    public boolean seConnecter() {
        Scanner scanner = new Scanner(System.in);
        String pseudo;
        String mdp;
        do{ //récupérer un pseudo unique
            System.out.println("Veuillez entrer votre pseudo : ");
            pseudo = scanner.nextLine();
            if(!utilisateur.containsKey(pseudo)){
                System.out.println("pseudo inconnue, veuillez rentrer un nouveau pseudo");
            }
        }while(!utilisateur.containsKey(pseudo));

        do{
            System.out.println("Veuillez entrer votre mot de passe : ");
            mdp = scanner.nextLine();
            if(!(Objects.equals(utilisateur.get(pseudo), mdp))){
                System.out.println("mot de passe incorrect, veuillez entrer le bon mot de passe");
            }
        }while(!(Objects.equals(utilisateur.get(pseudo), mdp)));
        System.out.println("Connexion réussi, bienvenue sur l'application");

        return true;
    }
}
