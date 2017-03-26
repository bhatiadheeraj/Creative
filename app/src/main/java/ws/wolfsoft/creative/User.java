package ws.wolfsoft.creative;

/**
 * Created by bhatia on 23/07/16.
 */
public class User {

    public String username;
    public String email;
    public String pwd;
    public String mob;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email,String pwd,String mobile) {
        this.username = username;
        this.email = email;
        this.pwd = pwd;
        this.mob = mobile;
    }
}
