import com.google.gson.Gson;
import org.example.UtilitaireScanner;
import org.example.models.Utilisateur;
import org.example.models.Voyage;
import org.example.services.VoyageService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class VoyageServiceTest {
    private VoyageService voyageService;
    private Gson gson;
    private static Scanner scanner = UtilitaireScanner.getScanner();
    private static final String VOYAGE_FILE_TEST = "src/main/resources/voyageTest.json";


    @Before
    public void setUp() {
        gson = new Gson();

        //Avoir un fichier json avec déjà un voyage rempli à chaque début de test
        JSONArray donneeSetUp = new JSONArray();
        JSONObject voyage1 = new JSONObject();
        voyage1.put("id", 1);
        voyage1.put("localisation", "Paris");
        voyage1.put("locataire", "admin");
        voyage1.put("prix", 100);
        donneeSetUp.put(voyage1);

        try (FileWriter fileWriter = new FileWriter(VOYAGE_FILE_TEST)) {
            // Écriture des données initiales dans le fichier JSON
            fileWriter.write(donneeSetUp.toString(4));
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON : " + e.getMessage());
        }
        voyageService = new VoyageService(VOYAGE_FILE_TEST);
    }

    @Test
    public void testReserverVoyage() {
        //creer un locataire, un voyage à partir du voyage déjà disponible et le faire reserver par le loueur
        Utilisateur loueur = new Utilisateur("loueurTest", "passwordTest");
        List<Voyage> firstVoyages = voyageService.lireVoyage();
        Voyage voyage = firstVoyages.getFirst();
        voyageService.reserverVoyage(loueur, voyage);

        //re-récupérer la liste des voyages modifiés par reserverVoyage
        List<Voyage> postVoyages = voyageService.lireVoyage();


        // Vérifie que le voyage a bien le bon loueur
        assertEquals("loueurTest", postVoyages.getFirst().getLocataire());
    }

    @Test
    public void testLireVoyages() {

        List<Voyage> voyages = voyageService.lireVoyage();
        assertEquals(1, voyages.size()); // Vérifie que la liste contient bien le seul voyage dispo
        assertEquals("Paris", voyages.getFirst().getLocalisation()); // Vérifie que la localisation est correcte
    }

}
