package org.example.Impl;

import org.example.GestionReservation;

import java.util.Map;

public class GestionReservationLogementImpl implements GestionReservation {
    private LogementImpl logement = new LogementImpl();
    @Override
    public void mesReservations(String pseudo) {
        for (Map.Entry<Integer, LogementImpl> entry : logement.getListeLogement().entrySet()){
            if(entry.getValue().getLoueur().equals(pseudo)){
                System.out.println("Logement n°"+entry.getKey()+" - Localisation : "+entry.getValue().getLocalisation()+" - Prix : "+entry.getValue().getPrix()+" - Type de logement : "+entry.getValue().getType()+" - Propriétaire : "+entry.getValue().getProprietaire()+" - Loueur : "+entry.getValue().getLoueur()+"\n");
            }
        }
    }

    @Override
    public void reserverLogement() {

    }

    @Override
    public void reserverVoyage() {

    }
}
