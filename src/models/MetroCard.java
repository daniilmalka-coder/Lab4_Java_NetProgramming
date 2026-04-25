package models;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private String serNum;
    private User usr;
    private String colledge;
    private double balance;

    public MetroCard() {
        this.usr = new User();
    }

    public String getSerNum() { return serNum; }
    public void setSerNum(String serNum) { this.serNum = serNum; }
    public User getUsr() { return usr; }
    public void setUsr(User usr) { this.usr = usr; }
    public String getColledge() { return colledge; }
    public void setColledge(String colledge) { this.colledge = colledge; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Card №: " + serNum + "\nUser: " + usr +
                "\nColledge: " + colledge + "\nBalance: " + balance;
    }
}