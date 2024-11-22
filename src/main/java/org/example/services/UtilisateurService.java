package org.example.services;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.UtilitaireScanner;
import org.example.models.Utilisateur;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UtilisateurService {

    private String userFile; //"src/main/resources/utilisateur.json"
    private static Scanner scanner = UtilitaireScanner.getScanner();

    private Gson gson = new Gson();

    public UtilisateurService(String file){
        this.userFile = file;
    }
    public Utilisateur seConnecter(String pseudo, String mdp) {
        List<Utilisateur> utilisateurs = lireUtilisateurs();
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getPseudo().equals(pseudo) && utilisateur.getPassword().equals(mdp)) {
                System.out.println("Connexion réussie !");
                return utilisateur;
            }
        }
        System.out.println("Échec de la connexion : nom d'utilisateur ou mot de passe incorrect.");
        return null;
    }

    public Utilisateur creerCompte(String pseudo, String mdp){
        Utilisateur nvUtilisateur = null;
        List<Utilisateur> utilisateurs = lireUtilisateurs();
        if(!utilisateurs.isEmpty()) {
            for (Utilisateur utilisateur : utilisateurs) {
                if (utilisateur.getPseudo().equals(pseudo)) {
                    System.out.println("Pseudo déjà existant, veuillez re essayer");
                    return null;
                }
            }
        }
        if(Objects.equals(pseudo, "") || Objects.equals(mdp, "")){
            System.out.println("Pseudo vide ou mdp vide, veuillez re essayer");
            return null;
        }
        nvUtilisateur = new Utilisateur(pseudo, mdp);
        ajouterUtilisateur(nvUtilisateur);
        return nvUtilisateur;
    }

    // Lecture du fichier JSON pour obtenir la liste des utilisateurs
    public List<Utilisateur> lireUtilisateurs() {
        try (FileReader reader = new FileReader(userFile)) {
            Type utilisateurListType = new TypeToken<List<Utilisateur>>(){}.getType();
            return gson.fromJson(reader, utilisateurListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of(); // Retourne une liste vide en cas d'erreur
    }

    public void ajouterUtilisateur(Utilisateur nouvelUtilisateur) {
        List<Utilisateur> utilisateurs = lireUtilisateurs();

        // Ajouter le nouvel utilisateur à la liste
        utilisateurs.add(nouvelUtilisateur);

        // Sauvegarder la liste mise à jour dans le fichier JSON
        try (FileWriter writer = new FileWriter(userFile)) {
            gson.toJson(utilisateurs, writer);
            System.out.println("Nouvel utilisateur ajouté avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout de l'utilisateur.");
        }
    }
}

