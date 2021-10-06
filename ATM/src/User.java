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
     * add an account
     * @param acc
     */
    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }
}
