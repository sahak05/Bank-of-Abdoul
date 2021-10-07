import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String Name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    /**
     * generate unique ID for user
     * @return the uuid
     */
    public String getNewUserUUID(){
        String uuid;
        Random rand = new Random();
        int len = 6;
        boolean nonUnique;

        //keep looping until we get a unique ID
        do {
            uuid="";
            for(int i=0; i<len; i++){
                uuid+=((Integer)rand.nextInt(10)).toString();
            }
            //check if is unique
            nonUnique = false;
            for(User u : this.users){
                if(uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        }while (nonUnique);


        return uuid;
    }

    /**
     *Generate a unique uuid for an account
     * @return a new uuid
     */
    public String getNewAccountUUID() {
        String uuid;
        Random rand = new Random();
        int len = 6;
        boolean nonUnique;

        //keep looping until we get a unique ID
        do {
            uuid="";
            for(int i=0; i<len; i++){
                uuid+=((Integer)rand.nextInt(10)).toString();
            }
            //check if is unique
            nonUnique = false;
            for(Account a : this.accounts){
                if(uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        }while (nonUnique);


        return uuid;
    }

    /**
     * add an account for the user
     * @param acc
     */
    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }

    /**
     * Create a new user of the bank and give him a savings accounts
     * @param firstName
     * @param lastName
     * @param pin
     * @return
     */
    public User addUser(String firstName, String lastName, String pin){
        //create a new user object and add it in to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //create a savings account for the user and add tu User and Bank accounts lists
        Account newAccount = new Account("Savings", newUser, this);
        //add to holder and bank list
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);
        return newUser;
    }

    public User userLogin(String userID, String pin){

        //search through list of users
        for(User u: this.users){

            //check user ID is correct
            if(u.getUUID().compareTo(userID) ==0 && u.validatePin(pin))
                return u;
        }
        return null;
    }
}
