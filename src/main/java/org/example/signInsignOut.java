package org.example;

public interface signInsignOut {

    /*
    * créer un compte dans l'application*/
    public void creerCompte(String pseudo, String mdp);

    /*
    * se connecter à l'application*/
    public void seConnecter(String pseudo, String mdp);
}
