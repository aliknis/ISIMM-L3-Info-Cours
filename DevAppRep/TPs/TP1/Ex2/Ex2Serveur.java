package Ex2;

import java.net.*;
import java.io.*;

public class Ex2Serveur {

    static final String AU_REVOIR = "Merci pour votre engagement, à bientôt !";

    // Règles de réponse (recherche insensible à la casse)
    static String repondre(String motCle) {
        switch (motCle.trim().toUpperCase()) {
            case "COUR":
                return "Cour de l’institut : point de nourrissage principal, prévoir de l’eau fraîche.";
            case "JARDIN":
                return "Jardin : abri installé sous les buissons, rester discret.";
            case "PARKING":
                return "Parking : présence récente de chatons, ne pas s’approcher de la mère.";
            case "VACCIN":
                return "Le prochain passage du vétérinaire bénévole est prévu vendredi.";
            case "FIN":
           
                return AU_REVOIR;
            default:
                return "Information non reconnue, contactez le responsable du club.";
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1379);
            System.out.println("J’attends une connexion");
            Socket s = ss.accept();

            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            boolean fin = false;
            while (!fin) {
                System.out.println("J’attends un mot-clé");
                String str = br.readLine();
                if (str == null) {          // le client a fermé la connexion
                    break;
                }
                String rep = repondre(str);
                System.out.println("Reçu : " + str + "  J’envoie la réponse");
                pw.println(rep);
                if (rep.equals(AU_REVOIR)) {
                    fin = true;
                }
            }
            s.close();
            ss.close();
            System.out.println("Connexion fermée");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
