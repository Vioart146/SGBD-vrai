package Server;

import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(1968);
            server.setReuseAddress(true);
            while (true) {
                Socket client = server.accept();
                ClassClient clientSock = new ClassClient(client);
                new Thread(clientSock).start();
            }

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
