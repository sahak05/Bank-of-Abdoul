import java.util.ArrayList;

public class Bank {

    private String Name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    public String getNewUserUUID(){}

    public String getNewAccountUUID() {
    }

    /**
     * add an account for the user
     * @param acc
     */
    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }
}
