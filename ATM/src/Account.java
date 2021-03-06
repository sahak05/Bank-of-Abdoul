import java.util.ArrayList;

public class Account {

    /**
     * The Account's Name
     */
    private String name;


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

    /**
     *
     * @param name
     * @param holder
     * @param theBank
     */
     public Account(String name, User holder, Bank theBank){
         //set the account name and holder
         this.name = name;
         this.holder = holder;
         //get new account uuid
         this.uuid = theBank.getNewAccountUUID();

         //init list of transactions
         this.transactions = new ArrayList<Transaction>();

     }

    /**
     * get the account ID
     * @return the uuid
     */
    public String getUUID() {
         return this.uuid;
    }

    /**
     * get summary line for the account
     * @return
     */
    public String getSummaryLine() {

        //get the balance
        double balance = this.getBalance();

        //format the summary line, depending on the wether the balance is
        if(balance>=0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        }
        else{
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    /**
     * get the balance of this account by adding the amounts of the transactions
     * @return {double} balance
     */
    public double getBalance() {
        double balance = 0;
        for(Transaction t: this.transactions){
            balance+=t.getAmount();
        }
        return balance;
    }

    /**
     * print a transaction list for the account
     */
    public void printTransHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for(int x=this.transactions.size()-1; x>=0; x--){
            System.out.println(this.transactions.get(x).summaryLine());
        }
        System.out.println();
    }

    /**
     * add a new transaction
     * @param v
     * @param memo
     */
    public void addTransaction(double v, String memo) {
        //create a new transaction object and add it to our list
        Transaction newTrans = new Transaction(v,memo,this);
        this.transactions.add(newTrans);
    }
}
