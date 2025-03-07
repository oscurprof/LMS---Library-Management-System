package patron;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatronManagement {
    private List<Patron> patrons;
    private static  PatronManagement instance;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    public static PatronManagement getInstance(){
        if (instance == null) {
            instance = new PatronManagement();
        }
        return instance;
    }
    private PatronManagement() {
        patrons = new ArrayList<>();
    }
    public void addPatron(Patron patron){
        patrons.add(patron); //adds the Patron sent as argument in the Patron list
        savePatron();
    }
    public boolean duplicateID(String membership){
        for (Patron patron:patrons)
            if (patron.getMembershipID().equals(membership))
                return true;
        return false;
    }
    public void savePatron() {
        try {
            fos = new FileOutputStream("data/patrons.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(patrons);
            //countPatrons();
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

    }
    public void loadPatrons(){
        try{
            fis = new FileInputStream("data/patrons.txt");
            ois = new ObjectInputStream(fis);
            patrons.clear();
            //noinspection unchecked
            patrons = (List<Patron>) ois.readObject();
//            Object obj;
//            while ((obj=ois.readObject())!=null){
//                patrons.add((Patron) ois.readObject());
//                }
            System.out.println("Patrons Loaded Successfully!");

        } catch (IOException | ClassNotFoundException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
    public int getIndex(String membership){
        int i =-1;

        for (Patron patron:patrons){
            i=i+1;
            if (patron.getMembershipID().equals(membership)){
                break; //I tried putting here, but it gave an exception I don't know why, so I stored index and used alternative method
            }
        }
        return i;
    }
    public void deletePatron(String membership){
        int i =-1;

        for (Patron patron:patrons){
            i=i+1;
            if (patron.getMembershipID().equals(membership)){
                break; //I tried putting here, but it gave an exception I don't know why, so I stored index and used alternative method
            }
        }
        //System.out.println(bookISBN);
        patrons.remove(i);
        savePatron();
        //loadPatrons();
        //books.remove(book)
    }
    public int countPatrons(){
        return patrons.size();
    }

    public List<Patron> getPatrons() {
        return patrons;
    }
}
