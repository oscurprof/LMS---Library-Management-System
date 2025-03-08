package book;

import attributes.Attributes;
import borrowings.BorrowManagement;
import mainmenu.MainMenu;

import javax.swing.*;
import java.awt.*;

public class BookInterface extends BookFields{
    int index; JButton save;
    String oldISBN;
    public BookInterface(){
        super.createNewInterface();
        Attributes.headingLabel(super.win,"Book Details",10,40);
        //Default Values for Some Fields
        super.addToFrame();
        this.createSaveButton();
        //isbn.setEnabled(false);
        //isbn.setDisabledTextColor(Color.BLACK);
        setValues(0);
        //super.win.setVisible(true);
    }
    public void setValues(int index){
        //BookManagement.getInstance().loadBooks();
        this.index=index;
        super.isbn.setText(BookManagement.getInstance().getBooks().get(index).getIsbn());
        super.title.setText(BookManagement.getInstance().getBooks().get(index).getTitle());
        super.edition.setText(BookManagement.getInstance().getBooks().get(index).getEdition());
        super.genre.setText(BookManagement.getInstance().getBooks().get(index).getGenre());
        super.description.setText(BookManagement.getInstance().getBooks().get(index).getDescription());
        super.author.setText(BookManagement.getInstance().getBooks().get(index).getAuthor());
        super.language.setText(BookManagement.getInstance().getBooks().get(index).getLanguage());
        super.copies.setText(""+BookManagement.getInstance().getBooks().get(index).getCopies());
        super.fine.setText(""+BookManagement.getInstance().getBooks().get(index).getFine());
        super.location.setText(BookManagement.getInstance().getBooks().get(index).getLocation());
        oldISBN=isbn.getText();
    }
    @Override
    protected void createSaveButton(){
        save = new JButton("Save");
        save.setBounds(60,540,100,30);
        save.setCursor(Attributes.handcursor);
        //win.setLayout(null);
        save.addActionListener(e -> saveAction()); //replaced with lambda
        win.add(save);
    }
    private void saveAction(){
        boolean proceed = isbn.prompt()&& title.prompt()&& copies.prompt()&& fine.prompt();
        int i =-1;
        for (Book book:BookManagement.getInstance().getBooks()){
            i=i+1;
            if (i==index) //just making sure same book isbn is not checked
                continue;
            if (book.getIsbn().equals(super.isbn.getText()))
            {
                isbn.setPromptLabel("Book Already Exists!");
                return;
            }
        }
        if (proceed){
            Book book = super.authenticatedData(); //Returns Store able form of data
            BorrowManagement.getInstance().bookUpdated(oldISBN,book); //update books in borrows
            BookManagement.getInstance().getBooks().get(index).setIsbn(book.getIsbn());
            BookManagement.getInstance().getBooks().get(index).setTitle(book.getTitle());
            BookManagement.getInstance().getBooks().get(index).setEdition(book.getEdition());
            BookManagement.getInstance().getBooks().get(index).setGenre(book.getGenre());
            BookManagement.getInstance().getBooks().get(index).setDescription(book.getDescription());
            BookManagement.getInstance().getBooks().get(index).setAuthor(book.getAuthor());
            BookManagement.getInstance().getBooks().get(index).setLanguage(book.getLanguage());
            BookManagement.getInstance().getBooks().get(index).setCopies(book.getCopies());
            BookManagement.getInstance().getBooks().get(index).setFine(book.getFine());
            BookManagement.getInstance().getBooks().get(index).setLocation(book.getLocation());
            BookManagement.getInstance().saveBook();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Changes saved successfully!");
            try {
                MainMenu.booksList.doClick(); //refresh list
            }catch (Exception ignored){} //ignoreException
        }

    }
}
