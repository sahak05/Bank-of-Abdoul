import java.util.ArrayList;

public class User {
    /**
     * The first Name
     */
    private String FirstName;

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
}
