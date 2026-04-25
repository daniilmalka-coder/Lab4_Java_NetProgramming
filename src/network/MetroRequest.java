package network;

import models.MetroCard;
import java.io.Serializable;

public class MetroRequest implements Serializable {
    private OpType type;
    private MetroCard card; // додавання картки
    private String serNum;  // пошук/баланс
    private double money;   // поповнення

    // конструктор для додавання картки
    public MetroRequest(OpType type, MetroCard card) {
        this.type = type;
        this.card = card;
    }

    // конструктор для грошей та балансу
    public MetroRequest(OpType type, String serNum, double money) {
        this.type = type;
        this.serNum = serNum;
        this.money = money;
    }

    // конструктор для простих команд
    public MetroRequest(OpType type) {
        this.type = type;
    }

    public OpType getType() { return type; }
    public MetroCard getCard() { return card; }
    public String getSerNum() { return serNum; }
    public double getMoney() { return money; }
}