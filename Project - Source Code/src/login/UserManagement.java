package login;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class UserManagement //Singleton Class Ensuring Only 1 instance of Class throughout System
{
    private static UserManagement instance;
    private List<User> users;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;

    public static UserManagement getInstance(){
        if (instance == null) {
            instance = new UserManagement();
        }
        return instance;
    }
    private UserManagement() {
        // Initialize the list of users
        users = new ArrayList<>();
    }
    public void adduser(User user){
        users.add(user);
        this.saveusertofile();
    }
    public void saveusertofile() {
        try {
            fos = new FileOutputStream("data/users.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loaduserfromfile(){
        try{
            fis = new FileInputStream("data/users.txt");
            ois = new ObjectInputStream(fis);
            users.clear();
            //noinspection unchecked
            users = (ArrayList <User>) ois.readObject();
            System.out.println("Users Loaded Successfully!");

        } catch (EOFException e)
        {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                ois.close();
                fis.close();
            }
            catch (IOException io){
                io.printStackTrace();
            }
        }
    }
    public String validateLogin(String uname, String password){
        //String validation = "pwd";
        boolean userFound = false;
        for(User user:users) {
            if (user.getUname().equals(uname)) {
                if (user.getPassword().equals(password))
                    return "success";
                else
                    userFound=true;
                    //return "pwd";
            }
        }
        if (userFound)
            return "pwd";
        else
            return "usr";

    }
    public boolean duplicateUser(String uname){
        for(User user:users){
            if (user.getUname().equals(uname))
                //user.printAll();
                return true;
            }
        return false;
    }
}
