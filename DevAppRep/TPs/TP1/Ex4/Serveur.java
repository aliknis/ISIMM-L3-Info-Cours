package Ex4;

import java.io.*;
import java.net.*;

public class Serveur {
  public static void main(String[] args) {
    try {

      ServerSocket ss = new ServerSocket(9977); // socket d'écoute , créé UNE seule fois
      int n = 0; // compteur de béné voles servis

      while (true) { // le serveur ne s'arrête jamais
        System.out.println("J'attends un béné vole ...");
        Socket s = ss.accept(); // bloquant : attend un client

        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);

        String ligne = br.readLine(); // attend " prenom ; zone "
        if (ligne != null && ligne.split(";").length == 2) {
          String[] parties = ligne.split(";"); // parties [0] = prénom , parties [1] = zone
          n++;
          pw.println(
              " Bienvenue "
                  + parties[0]
                  + ", vous êtes le béné vole n°"
                  + n
                  + " de la journ ée. Zone : "
                  + parties[1]
                  + ".");
        } else {
          pw.println(" Format invalide : envoyez prenom ; zone ");
        }
        s.close(); // ferme le socket d'é change de CE client
      } // le socket d'é coute ss reste ouvert
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

