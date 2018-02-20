package bank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String bankName;
    private static Bank bankObj;
    private static Customer customerObj;


    public static void main(String[] args) {
        int choice=0;
        boolean flag = true;
        System.out.println("Enter the name of the bank:");

        bankName = scanner.nextLine().toLowerCase();

        bankObj = new Bank(bankName);
        printBranchInstruction();
        while (flag) {
            System.out.println("\nEnter your choice:");
            try {
                choice = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Please select between 0-12");
            }
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
                    updateBranch();
                    break;
                case 4:
                    printBranches();
                    break;
                case 5:
                    addNewCustomer();
                    break;
                case 6:
                    removeCustomer();
                    break;
                case 7:
                    updateCustomer();
                    break;
                case 8:
                    queryCustomer();
                    break;
                case 9:
                    printCustomers();
                    break;
                case 10:
                    depositFunds();
                    break;
                case 11:
                    withdrawFunds();
                    break;
                case 12:
                    printFunds();
                    break;
                case 13:
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
                "\n    3. Update a branch name" +
                "\n    4. Print available branches" +
                "\n\n    *** Customer service ***\n" +
                "\n    5. Add a customer" +
                "\n    6. Remove a customer" +
                "\n    7. Update a customer" +
                "\n    8. Query a customer" +
                "\n    9. Print customers" +
                "\n\n    *** Funds service ***\n" +
                "\n    10. Deposit" +
                "\n    11. Withdraw" +
                "\n    12. Print balance" +
                "\n\n    *** Quit ***" +
                "\n    13. Quit application");
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

    private static void updateBranch() {
        System.out.println("Enter the old branch's name:");
        String oldBranchName = scanner.nextLine().toLowerCase();


        System.out.println("Enter the new branch's name:");
        String newBranchName = scanner.nextLine().toLowerCase();


        bankObj.updateBranch(oldBranchName, newBranchName);
    }


    private static void printBranches() {
        bankObj.printBranches();
    }


    private static void addNewCustomer() {
        double initialFunds=0.0;

        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();


        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");


            String customerName = scanner.nextLine().toLowerCase();

            Customer customer = bankObj.searchInBranches(branchName).queryCustomer(customerName);

            if (customer == null) {


                System.out.println("Enter the initial funds:");
                try {
                    initialFunds = scanner.nextDouble();
                } catch (InputMismatchException e) {
                }
                scanner.nextLine();
                if (initialFunds > 0 && !(Double.isNaN(initialFunds))) {
                    boolean isAdded = bankObj.searchInBranches(branchName).addNewCustomer(customerName, initialFunds);
                    if (isAdded) {
                        System.out.println(customerName + " successfully added!");

                    } else {

                        System.out.println(customerName + "not added!");

                    }
                } else {
                    System.out.println("the initial amount must be a decimal number and greater than 0");
                }
                return;
            }
            System.out.println(customerName + " already exist!");


            return;
        }
        System.out.println(branchName + " not on the list of branches");

    }


    private static void removeCustomer() {

        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();


        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String customerName = scanner.nextLine().toLowerCase();

            Customer customer = bankObj.searchInBranches(branchName).queryCustomer(customerName);

            if (customer != null) {
                bankObj.searchInBranches(branchName).removeCustomer(customerName);
                return;
            }
            System.out.println(customerName+" not in the contact list");


            return;
        }
        System.out.println(branchName + " branch does not exist!");
    }

    private static void updateCustomer() {
        double newFunds=0.0;
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();


        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String oldCustomerName = scanner.nextLine().toLowerCase();

            Customer customer = bankObj.searchInBranches(branchName).queryCustomer(oldCustomerName);

            if (customer != null) {

                System.out.println("Enter the new customer's name:");
                String newCustomerName = scanner.nextLine().toLowerCase();


                System.out.println("Do you want to update the new customer's funds[Y/N]:");
                char toBeUpdate = Character.toLowerCase(scanner.next().charAt(0));

                if (toBeUpdate == 'y') {
                    System.out.println("Enter the new funds:");

                    try {
                        newFunds = scanner.nextDouble();

                    } catch (InputMismatchException e) {
                        System.out.println("Funds should be a decimal number ");

                    }
                    bankObj.searchInBranches(branchName).updateCustomer(oldCustomerName, newCustomerName, newFunds);
                        System.out.println("Funds successfully updated to " + newFunds + " Ksh");


                } else if (toBeUpdate == 'n') {
                    bankObj.searchInBranches(branchName).updateCustomer(oldCustomerName, newCustomerName, bankObj.searchInBranches(branchName).getFundForCustomer(oldCustomerName));
                    System.out.println(oldCustomerName + " successfully updated to " + newCustomerName);
                } else {
                    System.out.println("Please enter \"Y\" or \"N\"");
                }


                return;
            }
            System.out.println(oldCustomerName+" not in the contact list");

            return;
        }
        System.out.println(branchName + " branch does not exist!");
    }

    private static void queryCustomer() {
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();


        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String customerName = scanner.nextLine().toLowerCase();

            Customer customer = bankObj.searchInBranches(branchName).queryCustomer(customerName);

            if (customer != null) {
                System.out.println("Name : " + customer.getName() + " --> Balance : " + customer.getFunds().toString().replace("[", "").replace("]", "") + " Ksh");
                return;
            }
            System.out.println(customerName+" not in the contact list");

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
        System.out.println(branchName + " not on the list of branches");
    }

    private static void depositFunds() {
        double depositAmount=0.0;
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();
        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String customerName = scanner.nextLine().toLowerCase();
            Customer customer = bankObj.searchInBranches(branchName).queryCustomer(customerName);

            if (customer != null) {


                System.out.println("Enter the amount to be deposit:");

                try {
                    depositAmount = scanner.nextDouble();

                } catch (InputMismatchException e) {
                }
                scanner.nextLine();


                if (depositAmount > 0 && !(Double.isNaN(depositAmount))) {
                    boolean isDeposit = bankObj.searchInBranches(branchName).depositFunds(customerName, depositAmount);
                    if (isDeposit) {
                        System.out.println(depositAmount + " Ksh successfully deposit into " + customerName + "'s account");

                    } else {
                        System.out.println(depositAmount + " Ksh not deposit!");
                    }
                } else {
                    System.out.println("the initial amount must be a decimal number and greater than 0");
                }

                return;
            }
            System.out.println(customerName + " not on the customer list");
            return;
        }
        System.out.println(branchName + " not on the list of branches");
    }

    private static void withdrawFunds() {
        double withdrawAmount=0.0;
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();

        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String customerName = scanner.nextLine().toLowerCase();
            Customer customer = bankObj.searchInBranches(branchName).queryCustomer(customerName);
            if (customer != null) {
                System.out.println("Enter the amount to be withdrawn:");
                try {
                    withdrawAmount = scanner.nextDouble();

                } catch (InputMismatchException e) {
                }
                scanner.nextLine();

                if (withdrawAmount > 0 && !(Double.isNaN(withdrawAmount))) {
                    if(bankObj.searchInBranches(branchName).getFundForCustomer(customerName) > withdrawAmount){
                        /*boolean isWithdrawn = */bankObj.searchInBranches(branchName).withdrawFunds(customerName, withdrawAmount);
                        //  if (isWithdrawn) {
                        System.out.println(withdrawAmount + " Ksh successfully withdraw from " + customerName + "'s account");
                        //
                        //                    } else {
                        //                        System.out.println(withdrawAmount + " Ksh not withdrawn!");
                        //                    }
                    }else {
                        System.out.println("Insufficient balance in " + customerName + "'s account to make this transaction");
                    }

                } else {
                    System.out.println("the withdraw amount must be a decimal number and greater than the current customer's balance");
                }
                return;
            }
            System.out.println(customerName + " not on the customer list");
            return;
        }
        System.out.println(branchName + " not on the list  of branches");
    }

    private static void printFunds() {
        double withdrawAmount;
        System.out.println("Enter the branch's name:");
        String branchName = scanner.nextLine().toLowerCase();
        if (bankObj.searchInBranches(branchName) != null) {
            System.out.println("Enter the customer's name:");
            String customerName = scanner.nextLine().toLowerCase();
            Customer customer = bankObj.searchInBranches(branchName).queryCustomer(customerName);
            if (customer != null) {
                double currentFunds = bankObj.searchInBranches(branchName).getFundForCustomer(customerName);
                System.out.println("The balance of " + customerName + " is " + currentFunds + " Ksh");
                return;
            }
            System.out.println(customerName + " not on the customer list");
            return;
        }
        System.out.println(branchName + " not on the list  of branches");
    }
}
