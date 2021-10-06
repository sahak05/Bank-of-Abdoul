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

         //add to holder and bank list
         holder.addAccount(this);
         theBank.addAccount(this);
     }

}
