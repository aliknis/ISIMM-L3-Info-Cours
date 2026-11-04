import java.net.*;
import java.util.Scanner;

public class ServeurUDP {
  public static void main(String argv[]) {
    int port = 2342, port1 = 5124;
    String msg1;
    Scanner keyb = new Scanner(System.in);
    try {
      // on récupère le paramètre : port d’écoute
      System.out.println("server en attent");
      DatagramPacket packetRecu;
      // création d’une socket liée au port précisé en paramètre
      DatagramSocket socket = new DatagramSocket(port);
      // tableau de 15 octets qui contiendra les données reçues
      byte[] data = new byte[1024];
      // création d’un paquet en utilisant le tableau d’octets
      packetRecu = new DatagramPacket(data, data.length);
      // attente de la réception d’un paquet. Le paquet reçu est placé
      // dans packet et ses données dans data.
      socket.receive(packetRecu);
      // récupération et affichage des données (une chaîne de caractères)
      msg1 = new String(packetRecu.getData(), 0, packetRecu.getLength());
      System.out.println(" le message recu est: " + msg1);
      System.out.println(
          " ça vient de l’ @: " + packetRecu.getAddress() + "et du port: " + packetRecu.getPort());
      // on met une nouvelle donnée dans le paquet
      // (qui contient donc le couple @IP/port de la socket coté client)
      InetAddress ad = null;
      DatagramSocket socketRecu = new DatagramSocket();
      byte[] reponse = (new String("bien recu")).getBytes();
      ad = InetAddress.getLocalHost();
      DatagramPacket dgp = new DatagramPacket(reponse, reponse.length, ad, port1);
      // on envoie le paquet au client
      socketRecu.send(dgp);
    } catch (Exception e) {
      System.err.println("Erreur : " + e);
    }
  }
}
