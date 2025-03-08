package borrowings;

import attributes.Attributes;
import attributes.MyText;
import book.BookManagement;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Returns extends BorrowingFields{
    int index;
    JFormattedTextField returnedOn;
    JCheckBox isPaid;
    boolean returned;
    MyText fine;
    public Returns(){
        super();
        Attributes.headingLabel(super.win,"Returns Management",10,40);
        createOnReturnDate(390);
        this.returnedOn.setValue(new Date()); //set default date as today
        fine=new MyText("Fine",10,420);
        createPaidCheck(450,420);
        isPaid.setSelected(false); //not paid by default
        loadData(0); //load the data into fields
        disableFields();
        save.addActionListener(e -> saveFun());
        fine.addFrame(super.win);
        //super.win.setVisible(true);
    }
    private void createOnReturnDate(int y){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.returnedOn=new JFormattedTextField(simpleDateFormat);
        Attributes.CustomBorders(this.returnedOn);
        Attributes.createCustomLabel(win,"Return On:",10,y);
        this.returnedOn.setBounds(165,y,250,20);
        super.win.add(returnedOn);
    }
    private void calculateFine(){
        Date date1 = (Date) returnDate.getValue();
        date1.setHours(0);
        date1.setMinutes(0);
        date1.setSeconds(0);
        Date date2 = new Date();
        long millis = date2.getTime()-date1.getTime(); //get difference between dates in milliseconds
        int fineDays = (int) millis/(24*60*60*1000); //convert millie into days
        if (fineDays>0){
            fine.setText(""+(BorrowManagement.getInstance().getBorrows().get(index).getsBook().getFine()*fineDays));
            isPaid.setVisible(true);
        }
        else{
            fine.setText("0");
            isPaid.setSelected(true);
            //isPaid.setEnabled(false);
            //isPaid.setVisible(false);
        }
    }
    private void createPaidCheck(int x,int y){
        isPaid = new JCheckBox();
        Attributes.createCustomLabel(win,"Is Paid?",x,y);
        isPaid.setBounds((x+100),y,20,20);
        win.add(isPaid);
    }
    private void disableFields(){
        disableField(isbn);
        disableField(memID);
        disableField(borrowID);
        disableField(issueDate);
        disableField(returnDate);
        disableField(returnedOn);
        fine.setEnabled(false);
        fine.setDisabledTextColor(Color.RED);
        srcBook.setVisible(false);
        srcPatron.setVisible(false);
    }
    private void disableField(JTextField txt){
        txt.setEnabled(false);
        txt.setDisabledTextColor(Color.darkGray);
    }
    protected void loadData(int index){
        this.index=index;
        borrowID.setText(BorrowManagement.getInstance().getBorrows().get(index).borrowID);
        isbn.setText(BorrowManagement.getInstance().getBorrows().get(index).book.getIsbn());
        bookTitle.setText(BorrowManagement.getInstance().getBorrows().get(index).book.getTitle());
        bookStatus.setText("Borrowed");
        memID.setText(BorrowManagement.getInstance().getBorrows().get(index).patron.getMembershipID());
        patronName.setText(BorrowManagement.getInstance().getBorrows().get(index).patron.getName());
        patronStatus.setText(BorrowManagement.getInstance().getBorrows().get(index).patron.getExpiryStatus());
        issueDate.setValue(BorrowManagement.getInstance().getBorrows().get(index).borrowDate);
        returnDate.setValue(BorrowManagement.getInstance().getBorrows().get(index).returnDate);
        returned=BorrowManagement.getInstance().getBorrows().get(index).isReturned; //if book is already returned
        calculateFine();
        if (fine.getText().equals("0")){
            isPaid.setEnabled(false);
            isPaid.setSelected(true);
        }
        else{
            isPaid.setSelected(false);
            isPaid.setEnabled(true);
        }
    }
    private void saveFun(){
        if (!isPaid.isSelected()){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Fine Not Paid!\nKeep You Eyes Wide Open Soldier!","",JOptionPane.ERROR_MESSAGE);
        } else if (returned) {
            Toolkit.getDefaultToolkit().beep();
            String booka = "Books Available: "+BookManagement.getInstance().getBooks().getFirst().getCopies();
            JOptionPane.showMessageDialog(null,"Book Already Returned!\nTake Rest Soldier, Enough for Today!",booka,JOptionPane.ERROR_MESSAGE);
        } else {
            BorrowManagement.getInstance().getBorrows().get(index).returnedOn=new Date();
            BorrowManagement.getInstance().getBorrows().get(index).isReturned=true;
            //Increase Book Available 🙂
            BookManagement.getInstance().increaseBook(isbn.getText());
            BorrowManagement.getInstance().saveBorrowings(); //save borrowings once returned
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Book Returned Successfully!");
            returned=true;
        }
        //JOptionPane.showMessageDialog(null,"Return Date Changed Successfully!");
    }

    @Override
    protected boolean proceedSave() {

        return false;
    }
}
