import java.util.Date;

public class Transaction {

    /**
     * The amount of the transaction
     */
    private double amount;

    /**
     * The time and date the transaction was done
     */
    private Date timestamp;

    /**
     * A memo for this transaction
     */
    private String memo;

    /**
     * The account in which transaction was performed
     */
    private Account inAccount;
}
