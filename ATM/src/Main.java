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
     * @param scan
     * @return
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
            System.out.printf("Welcome %s, what would you like to do?", theUser.getFirstName());
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
        switch (choice){

            case 1:
                Main.showTransHistory(theUser, sc);
                break;

            case 2:
                Main.withdrawlFunds(theUser, sc);
                break;
            case 3:
                Main.depositFunds(theUser, sc);
                break;
            case 4:
                Main.transferFunds(theUser, sc);
                break;
        }

        //redisplay the menu unless the user wants to quit
        if(choice!=5) {
            Main.printUserMenu(theUser, sc);
        }
    }
}
