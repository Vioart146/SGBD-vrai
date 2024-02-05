package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Client
 */
public class Client {
    public static void main(String[] args)throws Exception{
        System.out.print("entrer le IP du serveur : ");
        Scanner sc = new Scanner(System.in);
        String ip = sc.nextLine();
        Socket autre = new Socket(ip,1968);
        Socket moi = new Socket("127.0.0.1",1968);
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        oos = new ObjectOutputStream(autre.getOutputStream());
        ois = new ObjectInputStream(autre.getInputStream());
        String welcome = (String)ois.readObject();
        System.out.println(welcome);
        Scanner scan;
        while (true){
            System.out.print("JSQL => : ");
            scan = new Scanner(System.in);
            String m = (String)sc.nextLine();
            oos.writeObject(""+m);
            String message = (String)ois.readObject();
            System.out.println(message);
            if (message.compareToIgnoreCase("bye bye ^_^")==0) {
                break;
            }
        }
        
    }
}