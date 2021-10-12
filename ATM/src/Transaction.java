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
     * @return {double}
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Get a string sumarizing the transaction
     * @return {String}
     */
    public String summaryLine() {
        if(this.amount >=0)
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        else{
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), -this.amount, this.memo);

        }
    }
}
