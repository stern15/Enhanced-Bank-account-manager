package bank;

import java.util.ArrayList;

public class Bank {
    private String bankName;
    private ArrayList<Branch> branches = new ArrayList<Branch>();
    private Branch branchObj;

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    private String getBankName() {
        return bankName;
    }


    public void addNewBranch(String branchName) {
        if (searchInBranches(branchName) == null) {
            branches.add(new Branch(branchName));
            System.out.println(branchName + " branch successfully added!");
            return;
        }
        System.out.println(branchName + " branch already exist!");
    }
    public void removeBranch(String branchName){
        if (searchInBranches(branchName) != null) {
            branches.remove(searchInBranches(branchName));
            System.out.println(branchName + " branch successfully removed!");
            return;
        }
        System.out.println(branchName + " branch does not exist!");
    }

    public void printBranches() {
        System.out.println("there is " + branches.size() + " branch(es)\n");
        for (int i = 0; i < branches.size(); i++) {
            System.out.println((i + 1) + ". " + branches.get(i).getBranchName());
        }
    }

    public Branch searchInBranches(String branchName) {
        for (Branch branch : branches) {
            if (branch.getBranchName().equals(branchName)) {
                return branch;
            }
        }
        return null;
    }
}
