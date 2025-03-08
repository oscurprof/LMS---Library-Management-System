package borrowings;

import java.awt.*;

//Related to books list when clicked on borrow there
public class BorrowingsInterface1 extends NewBorrowing {
    public BorrowingsInterface1(){
        super();
        //loadBook("Crimson");
        super.srcBook.setVisible(false);
        super.isbn.setEnabled(false);
        super.isbn.setDisabledTextColor(Color.BLACK);
        //super.getFrame().setVisible(true);
    }
    public void loadBook(String txt){
        super.isbn.setText(txt);
        super.srcBook.doClick();
    }
}