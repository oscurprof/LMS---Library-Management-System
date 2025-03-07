package patron;

import book.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Patron implements Serializable {
    private String membershipID,name,email,membershipType;
    Date expiryDate;
    private double fineBalance;
    private ArrayList<Book> currentBooks,borrowingHistory;

    public String getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(String membershipID) {
        this.membershipID = membershipID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryStatus(){
        if (expiryDate.before(new Date()))
            return "Expired";
        else return "Active";
    }

    public double getFineBalance() {
        return fineBalance;
    }

    public void setFineBalance(double fineBalance) {
        this.fineBalance = fineBalance;
    }

    public ArrayList<Book> getCurrentBooks() {
        return currentBooks;
    }

    public void setCurrentBooks(ArrayList<Book> currentBooks) {
        this.currentBooks = currentBooks;
    }

    public ArrayList<Book> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void setBorrowingHistory(ArrayList<Book> borrowingHistory) {
        this.borrowingHistory = borrowingHistory;
    }
}
