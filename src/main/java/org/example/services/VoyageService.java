package org.example.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Impl.Menu;
import org.example.UtilitaireScanner;
import org.example.models.Utilisateur;
import org.example.models.Voyage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class VoyageService {
    private static Scanner scanner = UtilitaireScanner.getScanner();

    private String voyageFile;

    public VoyageService(String voyageFile){
        this.voyageFile = voyageFile;
    }
    private Gson gson = new Gson();
    public List<Voyage> lireVoyage() {
        try (FileReader reader = new FileReader(voyageFile)) {
            Type VoyageListType = new TypeToken<List<Voyage>>(){}.getType();
            return gson.fromJson(reader, VoyageListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of(); // Retourne une liste vide en cas d'erreur
    }

    public void reserverVoyage(Utilisateur user, Voyage voyageLoue){
        List<Voyage> voyages = lireVoyage();
        for(Voyage voyage : voyages){
            if(voyageLoue.getId() == voyage.getId()){
                voyage.setLocataire(user.getPseudo());
            }
        }
        try (FileWriter writer = new FileWriter(voyageFile)) {
            gson.toJson(voyages, writer);
            System.out.println("Paiement pris en compte et Voyage reservé avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la reservation du logement.");
        }
    }

    public void afficherVoyage(Utilisateur user){
        System.out.println("== Liste des logements ==\n");
        List<Voyage> voyages = lireVoyage();

        for (Voyage voyage : voyages){
            System.out.println("Voyage n°" + voyage.getId()+" - Localisation : "+ voyage.getLocalisation() +" - Prix : "+ voyage.getPrix()+"\n");
        }
        System.out.println("Pour afficher les détails d'un voyage, entrer le numéro de ce dernier.\nTaper 'retour' pour revenir au menu");
        while (true) {
            String choix = scanner.nextLine();
            if (choix.equals("retour")) {
                Menu.afficherChoixDeNavigation(user);
                break;
            } else {
                for (Voyage voyage : voyages){
                    if(voyage.getId() == Integer.parseInt(choix)){
                        AfficherDetailsVoyage(user, voyage);
                        afficherVoyage(user);
                    }
                }
            }
        }

    }

    public void afficherVoyageReserve(Utilisateur user){
        System.out.println("== Liste des voyages reservé ==\n");
        List<Voyage> voyages = lireVoyage();

        for (Voyage voyage : voyages){
            if(Objects.equals(voyage.getLocataire(), user.getPseudo())) {
                System.out.println("Logement n°" + voyage.getId() + " - Localisation : " + voyage.getLocalisation() + " - Prix : " + voyage.getPrix() + "\n");
            }
        }
        while (true) {
            System.out.println("Pour afficher les détails d'un logement, entrer le numéro de ce dernier.\nTaper 'retour' pour revenir au menu");
            String choix = scanner.nextLine();
            if (Objects.equals(choix, "retour")) {
                Menu.afficherChoixDeNavigation(user);
                break;
            } else {
                for (Voyage voyage : voyages){
                    if(voyage.getId() == Integer.parseInt(choix)){
                        AfficherDetailsVoyage(user, voyage);
                    }
                }
            }
        }
    }

    public void AfficherDetailsVoyage(Utilisateur user, Voyage voyage) {
        System.out.println("== Voyage numéro " + voyage.getId() + " ==");
        System.out.println("Voyage n°" + voyage.getId() + " - Localisation : " + voyage.getLocalisation() + " - Prix : " + voyage.getPrix() + " - Loueur : " + voyage.getLocataire() + "\n");
        System.out.println("== Actions possibles vis-à-vis du voyage ==");
        String choix = "";
        System.out.println("- Entrer 'retour' pour revenir à la liste des voyages");
        if (voyage.getLocataire() == null) {
            System.out.println("- Entrer 'loue' pour réserver le voyage");
        }
        while (true) {
            choix = scanner.nextLine();
            if (choix.equals("loue")){
                reserverVoyage(user, voyage);
                break;
            } else if(choix.equals("retour")){
                afficherVoyage(user);
                break;
            }else{
                System.out.println("Entrée non valide.");
            }
        }
    }
}
