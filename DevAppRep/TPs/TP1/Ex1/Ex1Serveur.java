package Ex1;

import java.io.*;
import java.net.*;

/** Exercice 1 - Serveur : reçoit un poids (int) et renvoie la ration en grammes. */
public class Ex1Serveur {

  public static void main(String[] args) {

    try {
      ServerSocket serveur = new ServerSocket(9753);
      System.out.println("Serveur en attente sur le port " + 9753 + "...");

      Socket client = serveur.accept();
      InputStream in = client.getInputStream();
      OutputStream out = client.getOutputStream();

      System.out.println("Client connecté : " + client.getInetAddress());

      int poids = in.read();
      System.out.println("Poids reçu : " + poids + " kg");

      // 4. Calcul + garde-fou
      int ration;
      if (poids <= 0 || poids > 90) {
        ration = 0;
      } else {
        ration = poids * 25;
      }

      out.write(ration);
      out.flush();

    } catch (IOException e) {
      System.out.println("Erreur avec ce client : " + e.getMessage());
    }
    /*
    !!!APRES TESTER OutputStream ET InputStream on va utiliser DataInputStream et DataOutputStream POUR ECHANGER DES DONNEES SUR PLUS QU'UN OCTET!!!
     OutputStream out = socket.getOutputStream(); -->  DataInputStream in = new DataInputStream(client.getInputStream());
       InputStream in = socket.getInputStream(); --> DataOutputStream out = new DataOutputStream(client.getOutputStream());

    */

  }
}

