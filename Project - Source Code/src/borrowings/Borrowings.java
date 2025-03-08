package borrowings;
import book.Book;
import patron.Patron;

import java.io.Serializable;
import java.util.Date;

public class Borrowings implements Serializable {
    Date borrowDate, returnDate,returnedOn;
    boolean isReturned;
    String borrowID;
    Patron patron; Book book;
    Borrowings() {
        patron=new Patron();
        book=new Book();
        isReturned=false;
    }
    public String getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(String borrowID) {
        this.borrowID = borrowID;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }

    public Date getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(Date returnedOn) {
        this.returnedOn = returnedOn;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public Patron getsPatron() {
        return patron;
    }
    public Book getsBook() {
        return book;
    }
    public void addFine(){
        if (returnDate.before(new Date()))
            patron.setFineBalance(patron.getFineBalance()+ book.getFine());
    }
    public void returned(){
        patron.setFineBalance(patron.getFineBalance()- book.getFine());
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
