package models;

import java.util.ArrayList;
import java.util.List;

public class MetroCardBank {
    private List<MetroCard> store;

    public MetroCardBank() {
        this.store = new ArrayList<>();
    }

    public List<MetroCard> getStore() {
        return store;
    }

    //синхронізуємо методи для безпечної роботи
    public synchronized int findMetroCard(String serNum) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getSerNum().equals(serNum)) return i;
        }
        return -1;
    }

    public synchronized void addCard(MetroCard newCard) {
        store.add(newCard);
    }

    public synchronized boolean removeCard(String serNum) {
        int idx = findMetroCard(serNum);
        if (idx != -1) {
            store.remove(idx);
            return true;
        }
        return false;
    }

    public synchronized boolean addMoney(String serNum, double money) {
        int idx = findMetroCard(serNum);
        if (idx != -1) {
            store.get(idx).setBalance(store.get(idx).getBalance() + money);
            return true;
        }
        return false;
    }

    public synchronized boolean getMoney(String serNum, double money) {
        int idx = findMetroCard(serNum);
        if (idx != -1) {
            double current = store.get(idx).getBalance();
            if (current >= money) {
                store.get(idx).setBalance(current - money);
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized String toString() {
        StringBuilder buf = new StringBuilder("List of MetroCards:");
        for (MetroCard c : store) {
            buf.append("\n\n").append(c);
        }
        return buf.toString();
    }
}