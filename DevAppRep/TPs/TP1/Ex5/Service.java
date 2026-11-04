package exercice5;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Service implements Runnable{
	Socket s; private static int dernierNumero = 0;                                          // partagé

	 public static ArrayList<FicheAnimal> registre = new ArrayList<FicheAnimal>();
public Service (Socket sck) {this.s=sck;}



// Section critique : un seul thread à la fois (verrou = la classe ServiceFiche)
private static synchronized int enregistrer(FicheAnimal f) {
    
		registre.add(f);
	// ajout au registre partagé
    dernierNumero++;      // nouveau numéro d'ordre
    return dernierNumero;
}

@Override
public void run() {
	
	// Flux d'objets : on crée d'abord le flux de sortie, puis celui d'entrée
    OutputStream os;
	try {
		os = s.getOutputStream();
		 ObjectOutputStream oos = new ObjectOutputStream(os);
		    oos.flush();
		    InputStream is = s.getInputStream();
		    ObjectInputStream ois = new ObjectInputStream(is);

		    System.out.println("J’attends une fiche");
		    FicheAnimal fiche = (FicheAnimal) ois.readObject();

		    AccuseReception ar;
		    if (fiche.estFicheValide()) {
		    	int numero = enregistrer(fiche); 
		        registre.add(fiche);
		        ar = new AccuseReception("OK", "Fiche enregistrée", numero);
		    } else {
		    	String erreur = "";
                boolean especeOk = "CHAT".equals(fiche.getEspece()) || "CHIEN".equals(fiche.getEspece());
                boolean zoneOk = "COUR".equals(fiche.getZone()) || "JARDIN".equals(fiche.getZone())
                        || "PARKING".equals(fiche.getZone());
                if (!especeOk) erreur += "Espèce non reconnue (" + fiche.getEspece() + "). ";
                if (!zoneOk)   erreur += "Zone non reconnue (" + fiche.getZone() + ").";
		        ar = new AccuseReception("REJETE",
		                erreur, 0);
		    }

		    System.out.println("J’envoie l’accusé de réception");
		    oos.writeObject(ar);
		    oos.flush();

		    s.close();
		
	} catch (IOException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   

}
}
