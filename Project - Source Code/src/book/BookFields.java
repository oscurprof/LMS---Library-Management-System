package book;
import attributes.Attributes;
import attributes.MyText;

import javax.swing.*;
import java.awt.*;

public class BookFields {
    MyText isbn,title,edition,author,genre,description,language,location,copies,fine; JButton save;
    JFrame win;
    //can add a field named cover picture later
    protected BookFields(){
        //BookManagement.getInstance().loadBooks(); //Load the Books into System
        this.isbn = new MyText("Book ISBN",20,120);
        isbn.isRequired();
        this.title = new MyText("Book Title",20,160);
        title.isRequired();
        this.edition = new MyText("Book Edition",20,200);
        this.genre = new MyText("Book Genre",20,240);
        this.description = new MyText("Book Description",20,280);
        this.author = new MyText("Book Author",20,320);
        this.language = new MyText("Book Language",20,360);
        this.copies = new MyText("Copies Available",20,400); //No. of Books Available in Library
        copies.isRequired();
        this.fine = new MyText("Fine Amount",20,440);
        fine.isRequired();
        this.location = new MyText("Location",20,480); //Physical Location in Library
    }
    protected void addToFrame(){
        this.isbn.addFrame(win);
        this.title.addFrame(win);
        this.edition.addFrame(win);
        this.genre.addFrame(win);
        this.description.addFrame(win);
        this.author.addFrame(win);
        this.language.addFrame(win);
        this.copies.addFrame(win);
        this.fine.addFrame(win);
        this.location.addFrame(win);
    }
    protected void createNewInterface(){
        this.win = new JFrame("Book Registration");
        this.win.setSize(800,800);
        this.win.setLayout(null);
    }

    protected void createSaveButton(){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/save.png").getImage().getScaledInstance(100,30,Image.SCALE_SMOOTH));
        save = new JButton();
        save.setIcon(ic);
        save.setBorder(BorderFactory.createEmptyBorder());
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setContentAreaFilled(false);
        save.setBounds(120,550,100,30);
        //save.setCursor(Attributes.handcursor);
        //win.setLayout(null);
        save.addActionListener(e -> saveAction()); //replaced with lambda
        win.add(save);
    }
    private void saveAction(){
        //checks if any of required fields aren't empty!
        boolean proceed = isbn.prompt()&& title.prompt()&& copies.prompt()&& fine.prompt();
        //searches for book with same name
        if (BookManagement.getInstance().duplicateBook(isbn.getText())){
            //if a duplicate book is found, don't proceed further!
            isbn.setPromptLabel("Book Already Exists!");
            return;
        }
        //if everything is valid (proceed is true), then add book function will be called
        //authenticatedData() returns User object having all good data (in form that can be stored)
        if (proceed){
            BookManagement.getInstance().addBook(authenticatedData());
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Book has been registered Successfully!");
            clearAll();
        }
    }

    protected Book authenticatedData(){
        //Manages the text, and stores in book object and returns it!
        Book book = new Book();
        book.setIsbn(isbn.getText());
        book.setTitle(title.getText());

        if (edition.getText().equals("Enter Book Edition"))
            book.setEdition("");
        else
            book.setEdition(edition.getText());

        if (genre.getText().equals("Enter Book Genre"))
            book.setGenre("");
        else
            book.setGenre(genre.getText());

        if (description.getText().equals("Enter Book Description"))
            book.setDescription("");
        else
            book.setDescription(description.getText());

        if (author.getText().equals("Enter Book Author"))
            book.setAuthor("");
        else
            book.setAuthor(author.getText());

        if (location.getText().equals("Enter Location"))
            book.setLocation("");
        else
            book.setLocation(location.getText());

        if (copies.getText().equals("Enter Copies Available"))
            book.setCopies(0);
        else
            book.setCopies(Integer.parseInt(copies.getText()));

        if (edition.getText().equals("Enter Book Edition"))
            book.setEdition("");
        else
            book.setEdition(edition.getText());

        book.setLanguage(language.getText());
        book.setFine(Double.parseDouble(fine.getText()));
        //book.printAll();
        return book;
    }
    private void clearAll(){
        isbn.setText("");
        title.setText("");
        edition.setText("");
        author.setText("");
        genre.setText("");
        description.setText("");
        language.setText("English");
        location.setText("");
        copies.setText("0");
        fine.setText("20");
    }

    public JFrame getFrame() {
        return win;
    }
}
