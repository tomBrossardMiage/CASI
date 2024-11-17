package org.example.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.UtilitaireScanner;
import org.example.models.Logement;

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

    public Logement ajouterLogement(){
        Logement nvLogement = null;
        System.out.println("== Ajout d'un nouveau logement ==");
        System.out.println("Entrer la localisation du logement :");
        String localisation = scanner.nextLine();
        System.out.println("Entrer le prix du logement à la nuit:");
        int prix = Integer.parseInt(scanner.nextLine());
        System.out.println("Entrer le type du logement (maison, appartement):");
        String type = scanner.nextLine();
        System.out.println("Entrer votre pseudo :");
        String pseudo = scanner.nextLine();
        System.out.println("Veuillez entrer votre pseudo : ");
        pseudo = scanner.nextLine();
        nvLogement = new Logement(localisation, prix, type, pseudo);
        List<Logement> logements = lireLogements();
        logements.add(nvLogement);

        try (FileWriter writer = new FileWriter(LOGEMENT_FILE)) {
            gson.toJson(logements, writer);
            System.out.println("Nouveau logement ajouté avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout de l'utilisateur.");
        }
        return nvLogement;
    }


}
