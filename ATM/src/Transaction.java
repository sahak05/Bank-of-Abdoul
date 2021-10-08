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

    /**
     * Create aa new transaction
     * @param amount
     * @param inAccount
     */
    public Transaction(double amount, Account inAccount){
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo ="";
    }

    public Transaction(double amount, String memo, Account inAccount){
        //call the two-args constructor first
        this(amount, inAccount);
       //set the memo
        this.memo = memo;

    }

    /**
     * get the amount of the transaction
     * @return
     */
    public double getAmount() {
        return this.amount;
    }
}
