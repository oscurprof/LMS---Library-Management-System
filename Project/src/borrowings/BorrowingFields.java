package borrowings;

import attributes.Attributes;
import attributes.MyText;
import book.Book;
import book.BookManagement;
import patron.Patron;
import patron.PatronManagement;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowingFields {
    MyText borrowID;
    MyText isbn,bookTitle,bookStatus;
    MyText memID,patronName,patronStatus;
    JFormattedTextField issueDate,returnDate;
    JFrame win; JButton save,backBtn,srcBook,srcPatron;
    Patron patron;
    Book book;
    public BorrowingFields(){
        createFrame();
        borrowID = new MyText("Borrow ID",10,120);
        borrowID.isRequired();
        isbn = new MyText("Book",10,160);
        isbn.isRequired();
        bookTitle = new MyText("Book Title",10,200);
        bookStatus = new MyText("Book Status",330,200);
        memID = new MyText("Patron",10,250);
        memID.isRequired();
        patronName = new MyText("Patron Name",10,290);
        patronStatus = new MyText("Patron Status",330,290);
        customizeSubfields();
        nonEditableFields();
        //code for src book//
        srcBook = new JButton();
        createSearch(srcBook,160);
        srcBook.addActionListener(e -> searchBooks());
        srcPatron = new JButton();
        srcPatron.addActionListener(e -> searchPatrons());
        createSearch(srcPatron,250);
        /////////////////////
        createIssueDate(330);
        createReturnDate(360);
        //createCheckBox(390);
        createSave();
        addFields();
        //win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //win.setVisible(true);

    }
    private void customizeSubfields(){
        customizeSearchField(isbn,"Enter BookISBN to Search");
        customizeSearchField(memID,"Enter MembershipID to Search");
        customizeField(bookStatus);
        customizeField(bookTitle);
        customizeField(patronName);
        customizeField(patronStatus);

        //memID.setBounds((memID.getX()-80),memID.getY(),memID.getWidth(),memID.getHeight());
    }
    private void customizeField(MyText myText){
        Font font = new Font("Arial",Font.ITALIC,12);
        Border bf = BorderFactory.createMatteBorder(0,0,1,0,Color.gray);
        myText.getCustomLabel().setForeground(Color.gray);
        myText.getCustomLabel().setFont(font);
        myText.setFont(font);
        myText.setBorder(bf);
        myText.setBounds((myText.getX()-70),myText.getY(),200,myText.getHeight());
    }
    private void customizeSearchField(MyText myText,String txt){
        Font font = new Font("Arial",Font.ITALIC,12);
        Border bf = BorderFactory.createMatteBorder(0,0,1,0,Color.gray);
        myText.setBounds((myText.getX()-80),myText.getY(),myText.getWidth(),myText.getHeight());
        myText.getPromptLabel().setBounds(myText.getX(),(myText.getY()+18),myText.getWidth(),myText.getHeight());
        myText.setText(txt);
        myText.setForeground(Color.gray);
        myText.setFont(font);
        myText.setBorder(bf);
        myText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (myText.getText().equals(txt)){
                    myText.setText("");
                    myText.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (myText.getText().isBlank()){
                    myText.setText(txt);
                    myText.setForeground(Color.gray);
                    myText.setBorder(bf);
                }
            }
        });
    }
    private void createFrame()
    {
        this.win=new JFrame();
        win.setSize(800,800);
        win.setLayout(null);
        win.setLocationRelativeTo(null);
    }
    private void createIssueDate(int y){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.issueDate=new JFormattedTextField(simpleDateFormat);
        Attributes.CustomBorders(this.issueDate);
        Attributes.createCustomLabel(win,"Issue Date:",10,y);
        this.issueDate.setBounds(165,y,250,20);
    }
    protected void defaultIssueDate(){
        issueDate.setValue(new Date());
    }
    private void createReturnDate(int y){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.returnDate=new JFormattedTextField(simpleDateFormat);
        Attributes.CustomBorders(this.returnDate);
        Attributes.createCustomLabel(win,"Return Date:",10,y);
        this.returnDate.setBounds(165,y,250,20);
    }
    protected void defaultReturnDate(){
        Date date = new Date();
        date.setDate(date.getDate()+14);
        returnDate.setValue(date);
    }
    protected void createSave(){
        save=new JButton();
        backBtn = new JButton();
        ImageIcon ic2 = new ImageIcon(new ImageIcon("resources/backBtn.png").getImage().getScaledInstance(100,30,Image.SCALE_SMOOTH));
        backBtn.setIcon(ic2);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder());
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setBounds(320,500,100,35);
        //win.add(backBtn);
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/save.png").getImage().getScaledInstance(100,30,Image.SCALE_SMOOTH));
        save.setIcon(ic);
        //save.setText("Save   ");
        save.setBorder(BorderFactory.createEmptyBorder());
        save.setContentAreaFilled(false);
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setBounds(200,500,100,35);
        save.setOpaque(false);
    }
    protected boolean proceedSave(){ //check all the fields but for duplicate ID
        if (!borrowID.prompt() || book==null || patron==null){
            if (book==null)
                isbn.setPromptLabel("No Book Selected!");
            if (patron==null)
                memID.setPromptLabel("No Patron Selected!");
            return false;
        }
            if (issueDate.getText().isBlank()||returnDate.getText().isBlank()){
                Toolkit.getDefaultToolkit().beep();
                if (issueDate.getText().isBlank())
                    issueDate.setBorder(Attributes.rborder);
                if (returnDate.getText().isBlank())
                    returnDate.setBorder(Attributes.rborder);
                //JOptionPane.showMessageDialog(null,"Must Enter Return & Issue Date!");
                return false;
        }
            if (bookStatus.getText().equals("Unavailable(0)")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null,"Book Unavailable!","",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (patronStatus.getText().equals("Expired")){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null,"Patron Subscription Expired","",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;
    }
    protected Borrowings storeData(){
        Borrowings borrowings = new Borrowings();
        borrowings.setBorrowID(borrowID.getText());
        //storing dates is not fun 😭
        borrowings.setBorrowDate((Date) issueDate.getValue());
        borrowings.setReturnDate((Date) returnDate.getValue());
        /*try {
            Date date = new SimpleDateFormat("MM/dd/yyyy").parse(issueDate.getText());
            borrowings.setBorrowDate(date);
            date = new SimpleDateFormat("MM/dd/yyyy").parse(returnDate.getText());
            borrowings.setReturnDate(date);
        }
        catch (ParseException pe)
        {
            System.out.println("\nMessage: "+pe.getMessage());
        }*/
        borrowings.setBook(book);
        borrowings.setPatron(patron);
        return borrowings;
    }
    private void addFields(){
        borrowID.addFrame(this.win);
        isbn.addFrame(this.win);
        bookTitle.addFrame(this.win);
        bookStatus.addFrame(this.win);
        memID.addFrame(this.win);
        patronName.addFrame(this.win);
        patronStatus.addFrame(this.win);
        win.add(issueDate);
        win.add(returnDate);
        //win.add(isReturned);
        win.add(srcBook);
        win.add(srcPatron);
        win.add(save);
    }
    private void nonEditableFields(){
        disableField(bookTitle);
        disableField(bookStatus);
        disableField(patronName);
        disableField(patronStatus);
    }
    private void disableField(MyText myText){
        myText.setText("");
        myText.setEnabled(false);
        myText.setDisabledTextColor(Color.darkGray);
    }
    private void createSearch(JButton btn,int y){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/Find.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        btn.setIcon(ic);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setContentAreaFilled(false);
        btn.setBounds(350,y,30,30);
        btn.setOpaque(false);
    }
    private void searchBooks(){
        for (Book book : BookManagement.getInstance().getBooks()){
            if (book.getIsbn().equals(isbn.getText())){
                bookTitle.setText(book.getTitle());
                bookStatus.setText(book.getStatus()+"("+book.getCopies()+")");
                //bookExist=true;
                this.book=book;
                return;
            }
        }
        //bookExist=false;
        isbn.setPromptLabel("Book Not Found!");
        bookTitle.setText("");
        bookStatus.setText("");
        this.book=null;
    }
    private void searchPatrons(){
        for (Patron patron : PatronManagement.getInstance().getPatrons()){
            if (patron.getMembershipID().equals(memID.getText())){
                patronName.setText(patron.getName());
                patronStatus.setText(patron.getExpiryStatus());
                this.patron=patron;
                //patronExist=true;
                return;
            }
        }
        //patronExist=false;
        this.patron=null;
        memID.setPromptLabel("Patron Not Found!");
        patronStatus.setText("");
        patronName.setText("");

    }
    public JFrame getFrame(){
        return win;
    }

}
