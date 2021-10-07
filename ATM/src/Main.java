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

    private static User mainMenuPrompt(Bank theBank, Scanner scan) {

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
}
