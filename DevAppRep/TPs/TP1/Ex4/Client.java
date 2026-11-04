package Ex4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    try {
      Socket s = new Socket("localhost", 9977);
      InputStream is = s.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      OutputStream os = s.getOutputStream();
      PrintWriter pw = new PrintWriter(os, true);

      Scanner clavier = new Scanner(System.in);
      System.out.println(" Votre prénom :");
      String prenom = clavier.nextLine();
      System.out.println(" Votre zone (COUR , JARDIN ou PARKING ) :");
      String zone = clavier.nextLine();

      pw.println(prenom + ";" + zone); // une seule ligne : prenom ; zone
      String rep = br.readLine();
      System.out.println(rep);
      s.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
