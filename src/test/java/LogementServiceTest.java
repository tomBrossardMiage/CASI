import com.google.gson.Gson;
import org.example.models.Logement;
import org.example.models.Utilisateur;
import org.example.services.LogementService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class LogementServiceTest {
    private LogementService logementService;
    private Gson gson;
    private static final String LOGEMENT_FILE_TEST = "src/main/resources/logementTest.json";

    @Before
    public void setUp() {
        gson = new Gson();
        //Avoir un fichier json avec déjà 1 logement à chaque début de test
        JSONArray donneeSetUp = new JSONArray();
        JSONObject logement = new JSONObject();
        logement.put("id", 0);
        logement.put("localisation", "localisationtest");
        logement.put("prix", 1000);
        logement.put("type", "maisontest");
        logement.put("proprietaire", "usertest");
        donneeSetUp.put(logement);

        try (FileWriter fileWriter = new FileWriter(LOGEMENT_FILE_TEST)) {
            // Écriture des données initiales dans le fichier JSON
            fileWriter.write(donneeSetUp.toString(4));
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON : " + e.getMessage());
        }
        logementService = new LogementService(LOGEMENT_FILE_TEST);
    }

    @Test
    public void testAjouterLogement() {
        Utilisateur user = new Utilisateur("usertest", "passwordTest");
        Logement logement = logementService.ajouterLogement(user, "Paris", 1000, "Appartement");

        List<Logement> logements = logementService.lireLogements();
        assertEquals(2, logements.size()); // Vérifie que la liste contient bien deux logements après l'ajout
        assertEquals("Paris", logements.get(1).getLocalisation());// Vérifie que la localisation du deuxième logement est bien Paris (celui que l'on a ajouté)
    }

    @Test
    public void testSupprimerLogement() {
        Utilisateur user = new Utilisateur("usertest", "passwordTest");
        Logement logement = logementService.ajouterLogement(user, "Paris", 1000, "Appartement");

        logementService.supprimerLogement(logement);
        List<Logement> logements = logementService.lireLogements();
        assertFalse(logements.contains(logement)); // Vérifie que le logement a été supprimé
        assertEquals(1, logements.size()); // Vérifie que la liste est égale à 1 après la suppression (logement de base)
    }

    @Test
    public void testLouerLogement() {
        Utilisateur proprietaire = new Utilisateur("usertestProprietaire", "passwordTest");
        Utilisateur loueur = new Utilisateur("loueurTest", "passwordTest");
        Logement logement = logementService.ajouterLogement(proprietaire, "Lyon", 1200, "Maison");

        logementService.louerLogement(loueur, logement);
        List<Logement> logements = logementService.lireLogements();

        // Vérifie que le logement a bien le bon loueur
        assertEquals("loueurTest", logements.get(1).getLoueur());
    }

    @Test
    public void testLireLogements() {
        Utilisateur user = new Utilisateur("usertest", "passwordTest");
        logementService.ajouterLogement(user, "Marseille", 900, "Studio");

        List<Logement> logements = logementService.lireLogements();
        assertEquals(2, logements.size()); // Vérifie que la liste contient bien le logement ajouté
        assertEquals("Marseille", logements.get(1).getLocalisation()); // Vérifie que la localisation est correcte
    }
}
