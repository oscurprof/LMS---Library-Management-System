package borrowings;
import book.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowManagement {
    private List<Borrowings> borrows;
    private static  BorrowManagement instance;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    public static BorrowManagement getInstance(){
        if (instance == null) {
            instance = new BorrowManagement();
        }
        return instance;
    }
    private BorrowManagement() {
        borrows = new ArrayList<>();
    }
    public void addBorrowings(Borrowings Borrowing){
        borrows.add(Borrowing); //adds the Borrowings sent as argument in the Borrowings list
        saveBorrowings();
    }
    public void saveBorrowings() {
        try {
            fos = new FileOutputStream("data/borrows.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(borrows);
            //countBorrows();
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

    }
    public void loadBorrows(){
        try{
            fis = new FileInputStream("data/borrows.txt");
            ois = new ObjectInputStream(fis);
            borrows.clear();
            //noinspection unchecked
            borrows = (List<Borrowings>) ois.readObject();
            System.out.println("borrows Loaded Successfully!");

        } catch (IOException | ClassNotFoundException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
    public boolean duplicateID(String borrowID){
        for (Borrowings borrow:borrows)
            if (borrow.getBorrowID().equals(borrowID))
                return true;
        return false;
    }
    public int getIndex(String bid){
        int i =-1;
        for (Borrowings Borrowing:borrows){
            i=i+1;
            if (Borrowing.getBorrowID().equals(bid)){
                break; //I tried putting here, but it gave an exception I don't know why, so I stored index and used alternative method
            }
        }
        return i;
    }

    /*
    public void deleteBorrowings(String membership){
        int i =-1;
        for (Borrowings Borrowing:borrows){
            i=i+1;
            if (Borrowing.getMembershipID().equals(membership)){
                break; //I tried putting here, but it gave an exception I don't know why, so I stored index and used alternative method
            }
        }
        //System.out.println(bookISBN);
        borrows.remove(i);
        saveBorrowings();
        //load borrows();
        //books.remove(book)
    }*/
    public void bookUpdated(String isbn, Book book){
        int i=-1;
        for (Borrowings borrowing:borrows){
            i=i+1;
            //System.out.println(isbn);
            //System.out.println(borrowing.getsBook().getIsbn());
            //System.out.println("I am Changing!!!");
            if (borrowing.getsBook().getIsbn().equals(isbn)){
                borrows.get(i).setBook(book);
            }
        }
        saveBorrowings();
    }
    public void patronDeleted(String memID){
        List<Borrowings> deleter = new ArrayList<>();
        for (Borrowings borrowing:borrows){
            if (borrowing.getsPatron().getMembershipID().equals(memID)){
                deleter.add(borrowing);
            }
        }
        borrows.removeAll(deleter);
        saveBorrowings();
    }
    public void bookDeleted(String isbn){
        List<Borrowings> deleter = new ArrayList<>();
        for (Borrowings Borrowing:borrows){
            if (Borrowing.getsBook().getIsbn().equals(isbn)){
                deleter.add(Borrowing);
            }
        }
        borrows.removeAll(deleter);
        saveBorrowings();
    }
public int countBorrows(){
        return borrows.size();
    }

    public List<Borrowings> getBorrows() {
        return borrows;
    }
}
