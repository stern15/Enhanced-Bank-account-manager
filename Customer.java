package bank;

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> funds= new ArrayList<Double>();


    public Customer(String name, double funds) {
        this.name = name;
        this.funds.add(Double.valueOf(funds)) ;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getFunds() {
        return funds;
    }

}
