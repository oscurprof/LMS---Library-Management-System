package login;
import java.io.Serializable;

public class User implements Serializable {
    private String uname, password,email;
    public User(String uname, String password,String email){
        this.uname = uname;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public String getUname() {
        return uname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void printAll(){
        System.out.println("\nUsername: "+this.uname+"\nPassword: "+this.password+"\nEmail: "+this.email);
    }
}
