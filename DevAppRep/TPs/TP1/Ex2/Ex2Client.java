package exercice2;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Ex2Client {

	public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 1379);
 
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
 
            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
 
            Scanner clavier = new Scanner(System.in);
 
            boolean fin = false;
            while (!fin) {
                System.out.println("Donner un mot-clé (COUR, JARDIN, PARKING, VACCIN, FIN) :");
                String str = clavier.nextLine();   
                pw.println(str);
 
                String res = br.readLine();
                  
                    System.out.println("Res = " + res);
                
                  if (str.toUpperCase().equals("FIN")||str.toUpperCase().equals("Quitter")) {                 
                      fin = true;
                  }
            }
            s.close();
            clavier.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
