import java.util.ArrayList;

public class Account {

    /**
     * The Account's Name
     */
    private String name;


    /**
     * The balance
     */private double balance;

    /**
     * The account 10 number
     */
     private String uuid;

    /**
     * The User who owns this account
     */
     private User holder;

    /**
     * historic of transactions
     */
     private ArrayList<Transaction> transactions;


}
