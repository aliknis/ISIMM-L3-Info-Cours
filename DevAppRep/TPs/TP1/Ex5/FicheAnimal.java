package exercice5;

import java.io.*;
import java.time.LocalDate;

public class FicheAnimal implements Serializable {
    private String espece;      // "CHAT" ou "CHIEN"
    private String zone;        // "COUR", "JARDIN" ou "PARKING"
    private String etatSante;   // ex : "Blessé", "En bonne santé", "À vacciner"
    private LocalDate dateSignalement;
    private static int compteurFiches = 0;   // static : jamais sérialisé

    public FicheAnimal(String espece, String zone, String etatSante) {
        this.espece = espece;
        this.zone = zone;
        this.etatSante = etatSante;
        this.dateSignalement = LocalDate.now();
        compteurFiches++;
    }

    public boolean estFicheValide() {
        boolean especeValide = espece.equals("CHAT") || espece.equals("CHIEN");
        boolean zoneValide = zone.equals("COUR") || zone.equals("JARDIN")
                          || zone.equals("PARKING");
        return especeValide && zoneValide;
    }

    public String getEspece() { return espece; }
    public String getZone() { return zone; }
    public String getEtatSante() { return etatSante; }
    public LocalDate getDateSignalement() { return dateSignalement; }
    public static int getCompteurFiches() { return compteurFiches; }
}
