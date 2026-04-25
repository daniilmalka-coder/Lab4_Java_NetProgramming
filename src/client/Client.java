package client;

import models.MetroCard;
import models.User;
import network.MetroRequest;
import network.OpType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectInputStream is;
    private ObjectOutputStream os;

    public Client(String server, int port) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port), 1000);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("Connection error: " + e);
        }
    }

    public void sendRequest(MetroRequest req) {
        try {
            os.writeObject(req);
            os.flush();
            System.out.println("Server: " + is.readObject());
        } catch (Exception ex) {
            System.out.println("Request error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Client ---");
        Client cl = new Client("localhost", 7891);

        // 1. Додаємо картку
        MetroCard newCard = new MetroCard();
        newCard.setSerNum("001");
        newCard.setColledge("KPI"); // Трохи патріотизму для лаби :)
        newCard.setUsr(new User("Ivan", "Ivanov", "Male", "10.05.2003"));

        cl.sendRequest(new MetroRequest(OpType.ADD_CARD, newCard));

        // 2. Поповнюємо рахунок
        cl.sendRequest(new MetroRequest(OpType.ADD_MONEY, "001", 50.5));

        // 3. Дивимось баланс
        cl.sendRequest(new MetroRequest(OpType.SHOW_BALANCE, "001", 0));

        // 4. Завершуємо роботу
        cl.sendRequest(new MetroRequest(OpType.STOP));
    }
}
