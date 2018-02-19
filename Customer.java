package bank;

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> funds = new ArrayList<Double>();
    private double currentBalance;


    public Customer(String name, double funds) {
        this.name = name;
        this.funds.add(Double.valueOf(funds));
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getFunds() {
        return funds;
    }

    public ArrayList<Double> depositFunds(double amount, int position) {
        currentBalance = getFunds().get(getFunds().size() - 1);
        currentBalance += amount;
        funds.add(currentBalance);
        return funds;
    }

    public ArrayList<Double> withdrawFunds(double amount, int position) {
        currentBalance = getFunds().get(getFunds().size() - 1);
        currentBalance -= amount;
        funds.add(currentBalance);
        return funds;
    }

}
