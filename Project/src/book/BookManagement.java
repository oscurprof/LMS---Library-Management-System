package book;

import borrowings.BorrowManagement;
import borrowings.Borrowings;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookManagement {
    private List<Book> books;
    private static BookManagement instance;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    public static BookManagement getInstance(){
        if (instance == null) {
            instance = new BookManagement();
        }
        return instance;
    }
    private BookManagement() {
        books = new ArrayList<>();
    }
    public void addBook(Book book){
        books.add(book); //adds the book sent as argument in the book list
        saveBook();
    }
    public boolean duplicateBook(String bookISBN){
        for (Book book:books)
            if (book.getIsbn().equals(bookISBN))
                return true;
        return false;
    }
    public void saveBook() {
        try {
            fos = new FileOutputStream("data/books.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.close();
            fos.close();
        }
        catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

    }
    public void reduceBook(String isbn){
        for (Book book : books)
            if (book.getIsbn().equals(isbn)){
                book.setCopies(book.getCopies()-1);
            }
        saveBook(); //save to file after quantity is reduced
    }
    public void increaseBook(String isbn){
        for (Book book : books)
            if (book.getIsbn().equals(isbn)){
                book.setCopies(book.getCopies()+1);
            }
        saveBook(); //save to file after quantity is reduced
    }
    public void loadBooks(){
        try{
            fis = new FileInputStream("data/books.txt");
            ois = new ObjectInputStream(fis);
            books.clear();
            //noinspection unchecked
            books = (List<Book>) ois.readObject();
            System.out.println("Books Loaded Successfully!");

        } catch (IOException | ClassNotFoundException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
    public int getIndex(String bookISBN){
        int i =-1;

        for (Book book:books){
            i=i+1;
            if (book.getIsbn().equals(bookISBN)){
                break; //I tried putting here, but it gave an exception I don't know why, so I stored index and used alternative method
            }
        }
        return i;
    }
    public void deleteBook(String bookISBN){
        int i =-1;

        for (Book book:books){
            i=i+1;
            if (book.getIsbn().equals(bookISBN)){
                break; //I tried putting here, but it gave an exception I don't know why, so I stored index and used alternative method
            }
        }
        //System.out.println(bookISBN);
        //System.out.println(books.get(i).is);
        List<Borrowings> borrows = BorrowManagement.getInstance().getBorrows();
        for (Borrowings borrowings:borrows)
            if (borrowings.getsBook().getIsbn().equals(bookISBN))
                if (!borrowings.isReturned()){
                    JOptionPane.showMessageDialog(null,"Why so reckless dude! Book is already borrowed");
                    return;
                }

        books.remove(i);
        saveBook();
        //loadBooks();
    }
    public int countBooks(){
        return books.size();
    }

    public List<Book> getBooks() {
        return books;
    }
}
