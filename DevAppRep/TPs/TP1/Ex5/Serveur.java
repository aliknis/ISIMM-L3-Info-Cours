package exercice5;


import java.io.*;
import java.net.*;
import java.util.ArrayList;
public class Serveur {
	
	    public static void main(String[] args) {
	        try {
	          
	            ServerSocket ss = new ServerSocket(1379);
	            System.out.println("J’attends une connexion");
	            while(true)
	            {Socket s = ss.accept();
	            Service serv = new Service(s);
	            Thread th = new Thread(serv);
	            th.start();

	            
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}



