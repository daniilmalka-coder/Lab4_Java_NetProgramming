package server;

import models.MetroCardBank;
import network.MetroRequest;
import network.OpType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private boolean work = true;
    private MetroCardBank bnk;
    private Socket s;

    public ClientHandler(MetroCardBank bnk, Socket s) {
        this.bnk = bnk;
        this.s = s;
        try {
            this.os = new ObjectOutputStream(s.getOutputStream());
            this.is = new ObjectInputStream(s.getInputStream());
        } catch (IOException e) {
            System.out.println("Error init streams: " + e);
        }
    }

    @Override
    public void run() {
        System.out.println("Client connected: " + s);
        try {
            while (work) {
                Object obj = is.readObject();
                if (obj instanceof MetroRequest) {
                    MetroRequest req = (MetroRequest) obj;
                    processRequest(req);
                }
            }
        } catch (Exception e) {
            System.out.println("Connection dropped.");
        } finally {
            try { s.close(); } catch (IOException ex) { ex.printStackTrace(); }
        }
    }

    private void processRequest(MetroRequest req) throws IOException {
        switch (req.getType()) {
            case ADD_CARD:
                bnk.addCard(req.getCard());
                sendResponse("Card added successfully");
                break;
            case ADD_MONEY:
                if (bnk.addMoney(req.getSerNum(), req.getMoney())) {
                    sendResponse("Money added!");
                } else {
                    sendResponse("Error: Card not found");
                }
                break;
            case SHOW_BALANCE:
                int idx = bnk.findMetroCard(req.getSerNum());
                if (idx >= 0) {
                    sendResponse("Balance for " + req.getSerNum() + " is " + bnk.getStore().get(idx).getBalance());
                } else {
                    sendResponse("Card not found");
                }
                break;
            case STOP:
                work = false;
                sendResponse("Bye!");
                break;
            default:
                sendResponse("Unknown command");
        }
    }

    private void sendResponse(String msg) throws IOException {
        os.writeObject(msg);
        os.flush();
    }
}