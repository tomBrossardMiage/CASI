import com.google.gson.Gson;
import org.example.UtilitaireScanner;
import org.example.models.Utilisateur;
import org.example.services.UtilisateurService;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.Assert.*;

public class UtilisateurServiceTest {

    private UtilisateurService utilisateurService;
    private Gson gson;
    private List<Utilisateur> mockUtilisateurs;
    private static Scanner scanner = UtilitaireScanner.getScanner();
    private static final String USERS_FILE_TEST = "src/main/resources/utilisateurTest.json";


    @Before
    public void setUp() {
        gson = new Gson();

        //Avoir un fichier json avec déjà admin rempli à chaque début de test
        JSONArray donneeSetUp = new JSONArray();
        JSONObject admin = new JSONObject();
        admin.put("pseudo", "admin");
        admin.put("password", "admin");
        donneeSetUp.put(admin);

        try (FileWriter fileWriter = new FileWriter(USERS_FILE_TEST)) {
            // Écriture des données initiales dans le fichier JSON
            fileWriter.write(donneeSetUp.toString(4));
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier JSON : " + e.getMessage());
        }
        utilisateurService = new UtilisateurService(USERS_FILE_TEST);
    }

    @Test
    public void testSeConnecterUtilisateurExistant() {
        //utilisateur de base dans le json
        String pseudo = "admin";
        String mdp = "admin";

        Utilisateur utilisateur = utilisateurService.seConnecter(pseudo, mdp);

        assertNotNull(utilisateur);
        assertEquals("admin", utilisateur.getPseudo());
        assertEquals("admin", utilisateur.getPassword());
    }

    @Test
    public void testSeConnecterUtilisateurInexistant() {
        String pseudo = "inexistant";
        String mdp = "password1";

        Utilisateur utilisateur = utilisateurService.seConnecter(pseudo, mdp);

        assertNull(utilisateur);
    }

    @Test
    public void testCreerCompteAvecPseudoExistant() {
        String pseudo = "admin";
        String mdp = "mdp";
        Utilisateur newUtilisateur = utilisateurService.creerCompte(pseudo, mdp);
        assertNull(newUtilisateur);
    }
}
