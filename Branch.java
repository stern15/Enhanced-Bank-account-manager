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


    public boolean addNewCustomer(String name, double initialFunds) {

        if (searchCustomer(name) < 0) {
                customers.add(new Customer(name, initialFunds));
            return true;
        }
        System.out.println("the customer's name already exist");
        return false;
    }

    public void removeCustomer(String name) {
        int position = searchCustomer(name);
            Customer currentCustomer = searchCustomer(position);
            customers.remove(currentCustomer);
            System.out.println(name + " successfully deleted!");

        System.out.println(name + " not on the customer list");
    }


    public void updateCustomer(String oldName, String newName, double funds) {
        int position = searchCustomer(oldName);

            customers.set(position, new Customer(newName, funds));

    }


    public Customer queryCustomer(String name) {
        int position = searchCustomer(name);
        if (searchCustomer(name) >= 0) {
            return searchCustomer(position);
        }
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


    public boolean /*void*/ depositFunds(String name, double amount) {
        int position = searchCustomer(name);
        if (position >= 0) {
            customers.get(position).depositFunds(amount, position);
            return true;
        }
        return false;
    }

    public /*boolean*/void withdrawFunds(String name, double amount) {
        int position = searchCustomer(name);
      //  if (position >= 0) {
            customers.get(position).withdrawFunds(amount, position);
//            return true;
//        }
//        return false;
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
