package exercice3v1;
import java.net.*;
import java.io.*;
import java.util.Scanner;




public class Client3 {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 1379);

            // Même ordre que le serveur : sortie d'abord, puis entrée
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.flush();
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            Scanner clavier = new Scanner(System.in);
            System.out.println("Espèce (CHAT / CHIEN) :");
            String espece = clavier.nextLine().trim().toUpperCase();
            System.out.println("Zone (COUR / JARDIN / PARKING) :");
            String zone = clavier.nextLine().trim().toUpperCase();
            System.out.println("État de santé :");
            String etatSante = clavier.nextLine();

            FicheAnimal fiche = new FicheAnimal(espece, zone, etatSante);
            oos.writeObject(fiche);
            oos.flush();

            AccuseReception ar = (AccuseReception) ois.readObject();
            System.out.println("Statut : " + ar.getStatut());
            System.out.println("Message : " + ar.getMessage());
            if (ar.getStatut().equals("OK")) {
                System.out.println("Fiche n° " + ar.getNumeroOrdre());
            }

            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
