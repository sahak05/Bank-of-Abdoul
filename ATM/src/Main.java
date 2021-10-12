import java.util.*;

public class Main {

    public static void main(String[] args) {
	    // initialize the scanner
        Scanner scan = new Scanner(System.in);

        //init the bank
        Bank theBank = new Bank("Bank of Abdoul");
        //I got a bank now


        //for testing to not create a user everytime (Jane Doe of blind spot)
        User user = theBank.addUser("Jane","Doe", "1234");

        //add a checking account for our user
        Account newAccount = new Account("Checking", user, theBank);
        user.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while(true){

            //stay in the login prompt until successful login
            curUser = Main.mainMenuPrompt(theBank, scan);

            //stay in the main menu until user quits
            Main.printUserMenu(curUser, scan);
        }
    }

    /**
     * Want to keep the login space open until we have a real user of the Bank
     * @param {Bank} theBank
     * @param {Scanner}scan
     * @return User
     */

    public static User mainMenuPrompt(Bank theBank, Scanner scan) {

        //inits
        String userID, pin;
        User authUser;

        //prompt the user for user ID/pin combo until a correct one is reached
        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID=scan.nextLine();
            System.out.print("Enter pin: ");
            pin = scan.nextLine();
            //try to get the user correspond to the ID/pin combo
            authUser = theBank.userLogin(userID, pin);
            if(authUser == null)
                System.out.println("Incorrect user ID/pin combination. Please try again");
        }
        while(authUser==null); //continue looping untill successful login
        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc){
        //print a summary of the user's accounts
        theUser.printAccountsSummary();

        //init
        int choice;

        //user menu
        do {
            System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
            System.out.println("1) Show account transaction history");
            System.out.println("2) Withdrawl");
            System.out.println("3) Deposit");
            System.out.println("4) Transfer");
            System.out.println("5) Quit");
            System.out.println();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if(choice <1 || choice>5)
                System.out.println("Invalid choice. Please choose 1-5");
        }while(choice<1 || choice>5);

        //process the choice
        switch (choice) {
            case 1 -> Main.showTransHistory(theUser, sc);
            case 2 -> Main.withdrawlFunds(theUser, sc);
            case 3 -> Main.depositFunds(theUser, sc);
            case 4 -> Main.transferFunds(theUser, sc);
            case 5 -> sc.nextLine();
        }

        //redisplay the menu unless the user wants to quit
        if(choice!=5) {
            Main.printUserMenu(theUser, sc);
        }
    }

    /**
     * proceed a fund deposit to an account
     * @param theUser
     * @param sc
     */
    private static void depositFunds(User theUser, Scanner sc) {

        //inits
        int toAcct;
        String memo;
        double amount;
        double acctBal;

        //get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n"+"to deposit in: ",theUser.numAccounts());
            toAcct = sc.nextInt()-1;

            if(toAcct <0|| toAcct> theUser.numAccounts())
                System.out.println("Invalid account. Pleas try again.");
        }while(toAcct <0|| toAcct> theUser.numAccounts());
        acctBal = theUser.getAcctBalance(toAcct);

        //the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $ ", acctBal);
            amount = sc.nextDouble();

            if(amount <0)
                System.out.println("Amount must be greater than zero.");
        }while(amount <0);

        //gobble up the rest of the previous input
        sc.nextLine();

        //get a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        //do the withdrawal
        theUser.addAcctTransaction(toAcct, amount, memo);
    }

    /**
     * process a fund withdrawal from an account
     * @param theUser the logged-in user object
     * @param sc th scanner
     */
    private static void withdrawlFunds(User theUser, Scanner sc) {

        //inits
        int fromAcct;
        String memo;
        double amount;
        double acctBal;

        //get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n"+"to withdraw from: ",theUser.numAccounts());
            fromAcct = sc.nextInt()-1;

            if(fromAcct <0|| fromAcct> theUser.numAccounts())
                System.out.println("Invalid account. Pleas try again.");
        }while(fromAcct <0|| fromAcct> theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);

        //the amount to transfer
        do {
            System.out.printf("Enter the amount to withdraw (max $%.02f): $ ", acctBal);
            amount = sc.nextDouble();

            if(amount <0)
                System.out.println("Amount must be greater than zero.");
            else if(amount>acctBal)
                System.out.printf("Amount mustn't be greater than balance of $%.02f.\n", acctBal);
        }while(amount <0|| amount> acctBal);

        //gobble up the rest of the previous input
        sc.nextLine();

        //get a memo
        System.out.print("Enter a memo: ");
        memo = sc.nextLine();

        //do the withdrawal
        theUser.addAcctTransaction(fromAcct, -1*amount, memo);
    }

    public static void transferFunds(User theUser, Scanner sc) {

        //inits
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        //get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n"+"to transfer from: ",theUser.numAccounts());
            fromAcct = sc.nextInt()-1;

            if(fromAcct <0|| fromAcct> theUser.numAccounts())
                System.out.println("Invalid account. Pleas try again.");
        }while(fromAcct <0|| fromAcct> theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);

        //account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account\n"+"to transfer to: ",theUser.numAccounts());
            toAcct = sc.nextInt()-1;

            if(toAcct <0|| toAcct> theUser.numAccounts())
                System.out.println("Invalid account. Pleas try again.");
        }while(toAcct <0|| toAcct> theUser.numAccounts());

        //the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $ ", acctBal);
            amount = sc.nextDouble();

            if(amount <0)
                System.out.println("Amount must be greater than zero.");
            else if(amount>acctBal)
                System.out.printf("Amount mustn't be greater than balance of $%.02f.\n", acctBal);
        }while(amount <0|| amount> acctBal);

        //finally, do the transfer
        theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account %s",
                theUser.getAcctUUID(toAcct)));

        theUser.addAcctTransaction(toAcct, amount, String.format("Transfer from account %s",
                theUser.getAcctUUID(fromAcct)));
    }

    /**
     * Show the transaction history for an account
     * @param {User} theUser
     * @param {Scanner} sc
     */
    public static void showTransHistory(User theUser, Scanner sc) {

        int theAcct;
        //get the account whose transaction history to look at
        do {
           System.out.printf("Enter the number (1-%d) of the account\n whose transactions you want to see:",
                   theUser.numAccounts());
           theAcct = sc.nextInt()-1;
           if(theAcct<0|| theAcct >theUser.numAccounts())
               System.out.println("Invalid account. Pleas try again.");
        }while(theAcct<0|| theAcct >theUser.numAccounts());

        //print the transaction history
        theUser.printAccTransHistory(theAcct);
    }


}
