package Ex1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/** Exercice 1 - Echange des octets */
public class Ex1Client {

  public static void main(String[] args) {

    Scanner clavier = new Scanner(System.in);
    System.out.print("Poids estimé de l'animal (kg) : ");
    int poids = clavier.nextInt();

    try {
      // 1. Connexion au serveur
      Socket socket = new Socket("localhost", 9753);
      OutputStream out = socket.getOutputStream();
      InputStream in = socket.getInputStream();
      // 2. Envoi de l'entier
      out.write(poids);
      out.flush();

      // 3. Réception de la ration
      int ration = in.read();

      if (ration == 0) {
        System.out.println("Poids invalide, merci de vérifier votre estimation.");
      } else {
        System.out.println("Ration recommandée : " + ration + " g de croquettes.");
      }
      socket.close();
    } catch (IOException e) {
      System.out.println("Erreur de communication : " + e.getMessage());
    }
  }
}
/*
*
*
!!!APRES TESTER OutputStream ET InputStream on va utiliser DataInputStream et DataOutputStream POUR ECHANGER DES DONNEES SUR PLUS QU'UN OCTET!!!
 OutputStream out = socket.getOutputStream(); -->  DataInputStream in = new DataInputStream(client.getInputStream());
   InputStream in = socket.getInputStream(); --> DataOutputStream out = new DataOutputStream(client.getOutputStream());

*/
