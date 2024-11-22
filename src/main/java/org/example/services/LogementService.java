package org.example.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Impl.Menu;
import org.example.UtilitaireScanner;
import org.example.models.Logement;
import org.example.models.Utilisateur;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LogementService {
    private String logementFile;
    private static Scanner scanner = UtilitaireScanner.getScanner();


    private Gson gson = new Gson();

    public LogementService(String file){
        this.logementFile = file;
    }
    public List<Logement> lireLogements() {
        try (FileReader reader = new FileReader(logementFile)) {
            Type LogementListType = new TypeToken<List<Logement>>(){}.getType();
            return gson.fromJson(reader, LogementListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of(); // Retourne une liste vide en cas d'erreur
    }

    public Logement ajouterLogement(Utilisateur user, String localisation, int prix, String type){
        Logement nvLogement = null;
        int id = recupererDernierId() + 1;
        nvLogement = new Logement(id, localisation, prix, type, user.getPseudo());
        List<Logement> logements = lireLogements();
        logements.add(nvLogement);

        try (FileWriter writer = new FileWriter(logementFile)) {
            gson.toJson(logements, writer);
            System.out.println("Nouveau logement ajouté avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du logement.");
        }
        return nvLogement;
    }

    public void supprimerLogement(Logement logement){
        List<Logement> logements = lireLogements();
        logements.removeIf(l -> l.getId() == logement.getId());//on cherche via l'id car ce n'est pas le même objet qui est comparé, le .remove classique ne fonctionne pas.
        try (FileWriter writer = new FileWriter(logementFile)) {
            gson.toJson(logements, writer);
            System.out.println("Logement supprimé avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression du logement.");
        }
    }

    public void louerLogement(Utilisateur user, Logement logementLouer){
        List<Logement> logements = lireLogements();
        for(Logement logement : logements){
            if(logementLouer.getId() == logement.getId()){
                logement.setLoueur(user.getPseudo());
            }
        }
        try (FileWriter writer = new FileWriter(logementFile)) {
            gson.toJson(logements, writer);
            System.out.println("Logement reservé avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la reservation du logement.");
        }
    }

    public void afficherLogement(Utilisateur user){
        System.out.println("== Liste des logements ==\n");
        List<Logement> logements = lireLogements();

        for (Logement logement : logements){
            System.out.println("Logement n°" + logement.getId()+" - Localisation : "+ logement.getLocalisation() +" - Prix : "+ logement.getPrix()+"\n");
        }
        System.out.println("Pour afficher les détails d'un logement, entrer le numéro de ce dernier.\nTaper 'retour' pour revenir au menu");
        while (true) {
            String choix = scanner.nextLine();
            if (choix.equals("retour")) {
                Menu.afficherChoixDeNavigation(user);
                break;
            } else {
                for (Logement logement : logements){
                    if(logement.getId() == Integer.parseInt(choix)){
                       AfficherDetailsLogement(user, logement);
                    }
                }
            }
        }
    }

    public void afficherLogementLoue(Utilisateur user){
        System.out.println("== Liste des logements ==\n");
        List<Logement> logements = lireLogements();

        for (Logement logement : logements){
            if(Objects.equals(logement.getLoueur(), user.getPseudo())) {
                System.out.println("Logement n°" + logement.getId() + " - Localisation : " + logement.getLocalisation() + " - Prix : " + logement.getPrix() + "\n");
            }
        }
        while (true) {
            System.out.println("Pour afficher les détails d'un logement, entrer le numéro de ce dernier.\nTaper 'retour' pour revenir au menu");
            String choix = scanner.nextLine();
            if (choix.equals("retour")) {
                Menu.afficherChoixDeNavigation(user);
                break;
            } else {
                for (Logement logement : logements){
                    if(logement.getId() == Integer.parseInt(choix)){
                        AfficherDetailsLogement(user, logement);
                    }
                }
            }
        }
    }

    public void AfficherDetailsLogement(Utilisateur user, Logement logement) {
        System.out.println("== Logement numéro " + logement.getId() + " ==");
        System.out.println("Logement n°" + logement.getId() + " - Localisation : " + logement.getLocalisation() +
                " - Prix : " + logement.getPrix() + " - Type de logement : " + logement.getType() +
                " - Propriétaire : " + logement.getProprietaire() + " - Loueur : " + logement.getLoueur() + "\n");

        System.out.println("== Actions possibles vis-à-vis du logement ==");
        String choix = "";
        System.out.println("- Entrer 'retour' pour revenir à la liste des voyages");
        if (logement.getLoueur() == null && !Objects.equals(logement.getProprietaire(), user.getPseudo())) {
            System.out.println("- Entrer 'loue' pour réserver le logement");
        }else if(Objects.equals(logement.getProprietaire(), user.getPseudo())){
            System.out.println("- Entrer 'supprimer' pour supprimer le logement");
        }
        while (true) {
            choix = scanner.nextLine();
            if (choix.equals("loue")){
                louerLogement(user, logement);
                break;
            } else if(choix.equals("supprimer")){
                supprimerLogement(logement);
                break;
            } else if(choix.equals("retour")){
                afficherLogement(user);
                break;
            }else{
                System.out.println("Entrée non valide.");
            }
        }
    }

    public int recupererDernierId(){
        List<Logement> logements = lireLogements();
        if(logements.isEmpty()){
            return -1;
        }
        return logements.getLast().getId();
    }





}
