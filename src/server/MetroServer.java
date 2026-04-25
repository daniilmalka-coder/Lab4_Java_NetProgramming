package server;

import models.MetroCardBank;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MetroServer extends Thread {
    private MetroCardBank bnk;
    private ServerSocket servSock;
    private int serverPort;

    public MetroServer(int port) {
        this.bnk = new MetroCardBank();
        this.serverPort = port;
    }

    @Override
    public void run() {
        try {
            this.servSock = new ServerSocket(serverPort);
            System.out.println("Metro Server started");
            while (true) {
                System.out.println("New Client Waiting...");
                Socket sock = servSock.accept();
                System.out.println("New client: " + sock);
                ClientHandler ch = new ClientHandler(bnk, sock);
                ch.start();
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e);
        }
    }

    public static void main(String[] args) {
        MetroServer srv = new MetroServer(7891);
        srv.start();
    }
}
