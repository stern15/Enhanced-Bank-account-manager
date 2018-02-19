package bank;

import java.util.ArrayList;

public class Branch {
    private String branchName;
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private Customer customerObj;
    private double fundForCustomer;

    public Branch(String branchName) {
        this.branchName = branchName;

    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }

    private ArrayList<Customer> getCustomers() {
        return customers;
    }


    public void addNewCustomer(String name, double initialFunds) {
        if (searchCustomer(name) < 0) {
            customers.add(new Customer(name, initialFunds));
            System.out.println(name + " successfully added!");
            return;
        }
        System.out.println("Customer's name already exist");
    }

    public void removeCustomer(String name) {
        int position = searchCustomer(name);
        if (position >= 0) {
            Customer currentCustomer = searchCustomer(position);
            customers.remove(currentCustomer);
            System.out.println(name + " successfully deleted!");
            return;
        }
        System.out.println(name + " not on the customer list");
    }


    public boolean updateCustomer(String oldName, String newName, double funds) {
        int position = searchCustomer(oldName);
        if (position >= 0) {

            customers.set(position, new Customer(newName, funds));
            return true;
        }
        System.out.println(oldName + " not on the customer list");
        return false;
    }


    public Customer queryCustomer(String name) {
        int position = searchCustomer(name);
        if (searchCustomer(name) >= 0) {
            return searchCustomer(position);
        }
        System.out.println(name + " not on the customer list");
        return null;
    }

    public void printCustomer() {
        System.out.println("you have " + customers.size() + " customer(s) in " + branchName + " branch\n");
        for (int i = 0; i < customers.size(); i++) {
            System.out.print((i + 1) + ". Name: " + customers.get(i).getName()
                    + " --> Balance: " + customers.get(i).getFunds().get(customers.get(i).getFunds().size() - 1).toString().replace("[", "").replace("]", "")
                    + " Ksh");
            System.out.println();

        }

    }


    private Customer searchCustomer(int position) {
        return customers.get(position);
    }

    private int searchCustomer(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if (getCustomers().get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }


    public boolean depositFunds(String name, double amount) {
        int position = searchCustomer(name);
        if (position >= 0) {
            customers.get(position).depositFunds(amount, position);
            return true;
        }
        return false;
    }

    public boolean withdrawFunds(String name, double amount) {
        int position = searchCustomer(name);
        if (position >= 0) {
            customers.get(position).withdrawFunds(amount, position);
            return true;
        }
        return false;
    }

    public double getFundForCustomer(String name) {

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(name)) {
                fundForCustomer = customers.get(i).getFunds().get(customers.get(i).getFunds().size() - 1).doubleValue();

            }
        }
        return fundForCustomer;
    }

}
