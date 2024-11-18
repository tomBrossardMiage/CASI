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
import java.util.Scanner;

public class LogementService {

    private static final String LOGEMENT_FILE = "src/main/resources/logement.json";
    private static Scanner scanner = UtilitaireScanner.getScanner();


    private Gson gson = new Gson();
    public List<Logement> lireLogements() {
        try (FileReader reader = new FileReader(LOGEMENT_FILE)) {
            Type LogementListType = new TypeToken<List<Logement>>(){}.getType();
            return gson.fromJson(reader, LogementListType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of(); // Retourne une liste vide en cas d'erreur
    }

    public Logement ajouterLogement(Utilisateur user){
        Logement nvLogement = null;
        System.out.println("== Ajout d'un nouveau logement ==");
        System.out.println("Entrer la localisation du logement :");
        String localisation = scanner.nextLine();
        System.out.println("Entrer le prix du logement à la nuit:");
        int prix = Integer.parseInt(scanner.nextLine());
        System.out.println("Entrer le type du logement (maison, appartement):");
        String type = scanner.nextLine();
        int id = recupererDernierId() + 1;
        nvLogement = new Logement(id, localisation, prix, type, user.getPseudo());
        List<Logement> logements = lireLogements();
        logements.add(nvLogement);

        try (FileWriter writer = new FileWriter(LOGEMENT_FILE)) {
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
        logements.remove(logement);
        try (FileWriter writer = new FileWriter(LOGEMENT_FILE)) {
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
        try (FileWriter writer = new FileWriter(LOGEMENT_FILE)) {
            gson.toJson(logements, writer);
            System.out.println("Logement supprimé avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression du logement.");
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
            if(logement.getLoueur().equals(user.getPseudo())) {
                System.out.println("Logement n°" + logement.getId() + " - Localisation : " + logement.getLocalisation() + " - Prix : " + logement.getPrix() + "\n");
            }
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

    public void AfficherDetailsLogement(Utilisateur user, Logement logement){
        System.out.println("== Logement numéro "+logement.getId()+ " ==");
        System.out.println("Logement n°"+logement.getId()+" - Localisation : "+ logement.getLocalisation() +" - Prix : "+ logement.getPrix()+" - Type de logement : "+ logement.getType() +" - Propriétaire : "+ logement.getProprietaire() +" - Loueur : "+ logement.getLoueur() +"\n\n");

        System.out.println("== Actions possibles vis-à-vis du logement ==");
        if(logement.getLoueur().isEmpty() && !logement.getProprietaire().equals(user.getPseudo())){
            System.out.println("- Entrer 'loue' pour reserver le logement");
        }
        if(logement.getProprietaire().equals(user.getPseudo())){
            System.out.println("- Entrer 'supprimer' pour supprimer le logement");
        }
        String choix = scanner.nextLine();
        if(choix.equals("loue")&&logement.getLoueur().isEmpty() && !logement.getProprietaire().equals(user.getPseudo())){
            louerLogement(user, logement);

        }
        if(choix.equals("supprimer")&&logement.getProprietaire().equals(user.getPseudo())){
            supprimerLogement(logement);
        }
    }

    public int recupererDernierId(){
        List<Logement> logements = lireLogements();
        return logements.getLast().getId();
    }





}
