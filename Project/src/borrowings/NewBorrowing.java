package borrowings;
import attributes.Attributes;
import book.BookManagement;

import javax.swing.*;

public class NewBorrowing extends BorrowingFields{
    public NewBorrowing(){
        super();
        Attributes.headingLabel(super.win,"Register a New Borrowing",10,40);
        save.addActionListener(e -> saveFun());
        super.defaultIssueDate();
        super.defaultReturnDate();
        //isReturned.setVisible(false);
        //super.win.setVisible(true);
    }
    private void saveFun(){
        if (!proceedSave())
            return;
        if (BorrowManagement.getInstance().duplicateID(borrowID.getText())){
            borrowID.setPromptLabel("Duplicate Borrow ID");
            return;
        }
        //Reduce Book Available 🙂
        BookManagement.getInstance().reduceBook(isbn.getText());
        //validate data and store borrowing 🙂
        BorrowManagement.getInstance().addBorrowings(super.storeData());
        JOptionPane.showMessageDialog(null,"Book("+bookTitle.getText()+") borrowed successfully by Patron("+patronName.getText()+")");
        clrAll();
    }
    private void clrAll(){
        super.borrowID.setText("");
        super.isbn.setText("");
        super.bookStatus.setText("");
        super.bookTitle.setText("");
        super.patron=null;
        super.book=null;
        super.memID.setText("");
        super.patronName.setText("");
        super.patronStatus.setText("");
        super.defaultIssueDate();
        super.defaultReturnDate();
        //super.isReturned.setSelected(false);
    }
}
