import java.net.*;
import java.util.Scanner;

public class ClientUDP {
  public static void main(String argv[]) {
    int port = 5124, port1 = 2342;
    String host = "";
    String msg1;
    Scanner keyb = new Scanner(System.in);
    try {
      // on récupère les paramètres : nom de la machine serveur
      System.out.println("Adress du serveur : ");
      host = keyb.next();
      InetAddress adr;
      DatagramPacket packet;
      DatagramSocket socket;
      // adr contient l’@IP de la partie serveur
      adr = InetAddress.getByName(host);
      // données àenvoyer : chaîne de caractères
      byte[] data = (new String("Hello Word")).getBytes();
      // création du paquet avec les données et en précisant l’adresse du serveur (@IP et port sur
      // lequel il écoute)
      packet = new DatagramPacket(data, data.length, adr, port1);
      // création d’une socket, sans la lier àun port particulier
      socket = new DatagramSocket();
      // envoi du paquet via la socket
      socket.send(packet);
      DatagramSocket socketRecu = new DatagramSocket(port);
      // création d’un tableau vide pour la réception
      byte[] reponse = new byte[15];
      DatagramPacket paquetRecu = new DatagramPacket(reponse, reponse.length);
      // attente paquet envoyé sur la socket du client
      socketRecu.receive(paquetRecu);
      // récupération et affichage de la donnée contenue dans le paquet
      msg1 = new String(paquetRecu.getData(), 0, paquetRecu.getLength());
      System.out.println(" reçu du serveur : " + msg1);
    } catch (Exception e) {
      System.err.println("Erreur : " + e);
    }
  }
}
