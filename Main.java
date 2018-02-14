package bank;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String bankName;
    private static Bank bankObj;
    private static Customer customerObj;

    public static void main(String[] args) {
        boolean flag = true;
        System.out.println("Enter the name of the bank:");
        bankName = scanner.nextLine().toLowerCase();
        bankObj = new Bank(bankName);
        printBranchInstruction();
        while (flag) {
            System.out.println("\nEnter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printBranchInstruction();
                    break;
                case 1:
                    addNewBranch();
                    break;
                case 2:
                    removeBranch();
                    break;
                case 3:
                    printBranches();
                    break;
                case 4:
                    addNewCustomer();
                    break;
                case 5:
                    removeCustomer();
                    break;
                case 6:
                    updateCustomer();
                    break;
                case 7:
                    queryCustomer();
                    break;
                case 8:
                    printCustomers();
                    break;
                //case 9;
                //case 10;
                //case 11;
                case 12:
                    flag = false;
                    break;
                default:
                    System.out.println("Please select between 0-12");
                    break;
            }

        }

    }

    private static void printBranchInstruction() {
        System.out.println("\n\nWelcome to " + bankName + " bank" +

                "\n\n    0. print the instructions" +
                "\n\n    *** Branch service ***\n" +
                "\n    1. Add a new branch" +
                "\n    2. Remove a branch" +
                "\n    3. Print available branches" +
                "\n\n    *** Customer service ***\n" +
                "\n    4. Add a customer" +
                "\n    5. Remove a customer" +
                "\n    6. Update a customer" +
                "\n    7. Query a customer" +
                "\n    8. Print customers" +
                "\n\n    *** Funds service ***\n" +
                "\n    9. Deposit" +
                "\n    10. Withdraw" +
                "\n    11. Print balance" +
                "\n\n    *** Quit ***" +
                "\n    12. Quit application");
    }

    private static void addNewBranch() {
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();
        bankObj.addNewBranch(branchName);

    }

    private static void removeBranch() {
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();
        bankObj.removeBranch(branchName);
    }

    private static void printBranches() {
        bankObj.printBranches();
    }

    private static void addNewCustomer() {

        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();

        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String customerName = scanner.nextLine().toLowerCase();
            System.out.println("Enter the initial funds:");
            double initialFunds = scanner.nextDouble();
            bankObj.searchInBranches(branchName).addNewCustomer(customerName, initialFunds);
            return;
        }
        System.out.println(branchName + " not in the branch list of customers!");
    }

    private static void removeCustomer() {

        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();

        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String customerName = scanner.nextLine().toLowerCase();
            bankObj.searchInBranches(branchName).removeCustomer(customerName);
            return;
        }
        System.out.println(branchName + " branch does not exist!");
    }

    private static void updateCustomer() {
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();

        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String oldCustomerName = scanner.nextLine().toLowerCase();
            System.out.println("Enter the new customer's name:");
            String newCustomerName = scanner.nextLine().toLowerCase();
            System.out.println("Do you want to update the new customer's funds[Y/N]:");
            char toBeUpdate = Character.toLowerCase(scanner.next().charAt(0));

            if (toBeUpdate == 'y') {
                System.out.println("Enter the new funds:");
                double newFunds = scanner.nextDouble();
                boolean isUpDated = bankObj.searchInBranches(branchName).updateCustomer(oldCustomerName, newCustomerName, newFunds);
                if(isUpDated) {
                    System.out.println("Funds successfully updated to " + newFunds + " Ksh");
                    return;
                }

            } else if (toBeUpdate == 'n') {
                bankObj.searchInBranches(branchName).updateCustomer(oldCustomerName, newCustomerName,bankObj.searchInBranches(branchName).getFundForCustomer(oldCustomerName));
                System.out.println(oldCustomerName+" successfully updated to "+newCustomerName);
            } else {
                System.out.println("Please enter \"Y\" or \"N\"");
            }
            return;
        }
        System.out.println(branchName + " branch does not exist!");
    }
    private static void queryCustomer(){
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();

        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String customerName = scanner.nextLine().toLowerCase();
            Customer customer = bankObj.searchInBranches(branchName).queryCustomer(customerName);
            System.out.println("Name : "+customer.getName()+" --> Balance : "+customer.getFunds().toString().replace("[", "").replace("]", "") +" Ksh");
            return;
        }
        System.out.println(branchName + " branch does not exist!");
    }

    private static void printCustomers() {
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();
        if (bankObj.searchInBranches(branchName) != null) {
            bankObj.searchInBranches(branchName).printCustomer();
            return;
        }
        System.out.println(branchName + " not in the branch list of customers!");
    }
}
