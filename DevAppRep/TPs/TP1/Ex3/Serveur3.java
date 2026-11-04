package exercice3v1;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

import exercice5.AccuseReception;



public class Serveur3 {
    public static void main(String[] args) {
        try {
            ArrayList<FicheAnimal> registre = new ArrayList<FicheAnimal>();

            ServerSocket ss = new ServerSocket(1379);
            System.out.println("J’attends une connexion");
            Socket s = ss.accept();

            // Flux d'objets : on crée d'abord le flux de sortie, puis celui d'entrée
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.flush();
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            System.out.println("J’attends une fiche");
            FicheAnimal fiche = (FicheAnimal) ois.readObject();

            AccuseReception ar;
            if (fiche.estFicheValide()) {
                registre.add(fiche);
                ar = new AccuseReception("OK", "Fiche enregistrée", registre.size());
            } else {
            	String erreur = "";
                boolean especeOk = "CHAT".equals(fiche.getEspece()) || "CHIEN".equals(fiche.getEspece());
                boolean zoneOk = "COUR".equals(fiche.getZone()) || "JARDIN".equals(fiche.getZone())
                        || "PARKING".equals(fiche.getZone());
                if (!especeOk) erreur += "Espèce non reconnue (" + fiche.getEspece() + "). ";
                if (!zoneOk)   erreur += "Zone non reconnue (" + fiche.getZone() + ").";
		        ar = new AccuseReception("REJETE",
		                erreur, 0);       }

            System.out.println("J’envoie l’accusé de réception");
            oos.writeObject(ar);
            oos.flush();

            s.close();
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
