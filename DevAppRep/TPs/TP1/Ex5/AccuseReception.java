package Ex5;

import java.io.*;

public class AccuseReception implements Serializable   {
    private static final long serialVersionUID = 1L;
	///private static final long serialVersionUID = 1L;
	private String statut;      // "OK" ou "REJETE"
    private String message;
    private int numeroOrdre;    // numéro de la fiche dans le registre (0 si rejetée)

    public AccuseReception(String statut, String message, int numeroOrdre) {
        this.statut = statut;
        this.message = message;
        this.numeroOrdre = numeroOrdre;
    }

    public String getStatut() { return statut; }
    public String getMessage() { return message; }
    public int getNumeroOrdre() { return numeroOrdre; }
}
