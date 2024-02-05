package Server;

import java.net.*;
import java.io.*;
import bdd.*;

/**
 * ClassClient
 */
public class ClassClient implements Runnable{
    Socket client;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    Sgbd bdd = new Sgbd();
    public ClassClient(Socket moi){
        client = moi;
    }

    public void run(){  
        try {  
        ois = new ObjectInputStream(client.getInputStream());
        oos = new ObjectOutputStream(client.getOutputStream());
        System.out.println("Nouvelle client : " + client.getInetAddress().getHostAddress() + " connecter");
        String welcome = " =========================================\n||                                       ||\n||   =======    ====     ===     ==      ||\n||      ||    ||       ||   ||   ||      ||\n||      ||      ===    ||   ||   ||      ||\n||      ||         ||  ||  \\'|   ||      ||\n||   ===       ====      ===\\'    ====   ||\n||                                       ||\n ========================================= \n";
        oos.writeObject(welcome);
        while (true) {
            String request = (String) ois.readObject();
            String response = bdd.connecter(request);
            oos.writeObject(response);
        }
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
}