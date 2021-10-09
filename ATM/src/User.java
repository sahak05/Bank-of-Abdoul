import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {
    /**
     * The first Name
     */
    private String firstName;

    /**
     * The last Name
     */
    private String lastName;

    /**
     * The Id Number of the User
     */
    private String uuid;

    /**
     * The MDS hash of the user's pin number
     */
    private byte pinHash[];

    /**
     * The list of accounts for this user
     */
    private ArrayList<Account> accounts;

    /**
     * Create a New User
     * @param firstName
     * @param lastName
     * @param pin
     * @param theBank
     */
    public User(String firstName, String lastName, String pin, Bank theBank){

        //set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        //store the pin's MDS hash, rather than the original value,
        //for security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        //get a new, unique ID for User
        //by generate a method in our bank class to creates them
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts
        this.accounts = new ArrayList<Account>();

        //print a log message
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);


    }

    /**
     *Getter
     * @return the uuid
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * first Name of the user
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * last name of the user
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * add an account
     * @param acc
     */
    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }

    /**
     * Check if the pin matches wit the true User pin
     * @param aPin the pin to check
     * @return wheter the pin is value or not
     */
    public boolean validatePin(String aPin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()),this.pinHash );
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    /**
     * Print summaries for the account of this user
     */
    public void printAccountsSummary() {
        System.out.printf("\n\n%s's accounts summary", this.getFirstName());

        for (int a=0; a<this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a+1,this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * number of accounts of the user
     * @return int
     */
    public int numAccounts(){
        return this.accounts.size();
    }

    /**
     * Print transaction history for a particular account
     * @param {int} acctidx
     */
    public void printAccTransHistory(int acctidx){
        this.accounts.get(acctidx).printTransHistory();
    }

    /**
     * Get the balance for a particular account
     * @param {int} fromAcct
     * @return double
     */
    public double getAcctBalance(int fromAcct) {
        return this.accounts.get(fromAcct).getBalance();
    }
}
