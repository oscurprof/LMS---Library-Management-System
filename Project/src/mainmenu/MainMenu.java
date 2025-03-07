package mainmenu;
import attributes.Attributes;
import book.*;
import borrowings.BorrowManagement;
import borrowings.NewBorrowing;
import borrowings.ReturnHistory;
import borrowings.ReturnsInterface;
import login.LoginMainForm;
import patron.NewPatronInterface;
import patron.PatronManagement;
import patron.PatronsInterface;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public final class MainMenu {

    public static JFrame main;
    public static JInternalFrame internalFrame;
    public static JButton booksList,patronsList;
    private JButton frontPage,patron,book,borrow,lgOut,qtBtn,aboutBtn;
    private final JPanel panel,bookMenu,patronMenu,borrowMenu;
    private JLabel logo;
    public MainMenu(){
        main = new JFrame("Main Menu");
        panel = new JPanel(); //menu panel
        bookMenu = new JPanel();
        patronMenu = new JPanel();
        borrowMenu = new JPanel();
        main.setSize(1000,850);
        main.setLayout(null);
        main.setLocationRelativeTo(null);
        FrontPage fp = new FrontPage();
        //***********************Internal Frame***************************\\
        internalFrame = new JInternalFrame("", false, false, false, true);
        internalFrame.add(fp.getFrame().getContentPane()); //adding to internal frame
        internalFrame.setBorder(BorderFactory.createEmptyBorder()); //removing borders
        ((javax.swing.plaf.basic.BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null); //Remove the heading label
        internalFrame.setBounds(180, 50, 800, 800); //setting dimensions
        internalFrame.setVisible(true); //setting visible
        internalFrame.setOpaque(false);
        //*********
        createMenuPanelBtn();
        createBookMenu();
        createPatronMenu();
        createBorrowMenu();
        main.add(panel);
        main.add(internalFrame);
        main.setResizable(false);
        //main.setMaximumSize(new Dimension(1000,850));
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
    private void createMenuPanelBtn(){ //creates all menu buttons
        createFrontPageBtn();
        createBookBtn();
        createPatronBtn();
        createBorrowsBtn();
        createAboutBtn();
        qtBtn();
        lgOut();
        csLogo();
        menuPanel();
    }
    private void menuPanel(){
        JLabel lbl=new JLabel(); //empty label
        JLabel lbl1=new JLabel();
        lbl.setPreferredSize(new Dimension(1,80));
        lbl1.setPreferredSize(new Dimension(1,250));//space after last button
        panel.add(logo,0);
        panel.add(lbl,1);
        panel.add(frontPage,2);
        panel.add(book,3);
        panel.add(patron,4);
        panel.add(borrow,5);
        panel.add(aboutBtn,6);
        //panel.add(lbl,7);
        panel.add(lbl1,7);
        panel.add(lgOut,8);
        panel.add(qtBtn,9);
        panel.setBorder(BorderFactory.createMatteBorder(0,0,0,2,Color.black));
        panel.setBounds(0,0,150,850);
    }
    private void createBookMenu(){
        JButton regBook = new JButton("Add New");
        booksList = new JButton("Book List");
        createSubMenuButtons(regBook);
        createSubMenuButtons(booksList);
        regBook.addActionListener(e -> {
            NewBookInterface newBookInterface = new NewBookInterface();
            Attributes.changeWindow(internalFrame,newBookInterface.getFrame().getContentPane(),"BookRegistration");
            bookMenu.setVisible(false);
        });
        booksList.addActionListener(e -> {
            if (BookManagement.getInstance().countBooks()>0){
            BooksInterface booksInterface = new BooksInterface();
                Attributes.changeWindow(internalFrame,booksInterface.getFrame().getContentPane(),"BooksInterface");
            bookMenu.setVisible(false);}else {
                Toolkit.getDefaultToolkit().beep();
                regBook.doClick();
                JOptionPane.showMessageDialog(null,"No Books Registered in the System!\n(Register a Book First and Then Try!)","",JOptionPane.WARNING_MESSAGE);
            }
        });
        bookMenu.add(regBook);
        bookMenu.add(booksList);
        bookMenu.setVisible(false);
        bookMenu.setBounds(152,280,120,120);
        bookMenu.setBorder(BorderFactory.createEtchedBorder());
        main.add(bookMenu);
    }
    private void createPatronMenu(){
        JButton regPatron = new JButton("New Patron");
        patronsList = new JButton("Patron List");
        createSubMenuButtons(regPatron);
        createSubMenuButtons(patronsList);
        regPatron.addActionListener(e -> {
            NewPatronInterface newPatronInterface = new NewPatronInterface();
            Attributes.changeWindow(internalFrame,newPatronInterface.getFrame().getContentPane(),"PatronRegistration");
            patronMenu.setVisible(false);
        });
        patronsList.addActionListener(e -> {
        if (PatronManagement.getInstance().countPatrons()>0){
            PatronsInterface patronsInterface = new PatronsInterface();
            Attributes.changeWindow(internalFrame,patronsInterface.getFrame().getContentPane(),"PatronsInterface");
        }else {
            Toolkit.getDefaultToolkit().beep();
            regPatron.doClick();
            JOptionPane.showMessageDialog(null,"No Patrons Registered in the System!\n(Register a Book First and Then Try!)","",JOptionPane.WARNING_MESSAGE);
        }
            patronMenu.setVisible(false);
        });
        patronMenu.add(regPatron);
        patronMenu.add(patronsList);
        patronMenu.setVisible(false);
        patronMenu.setBounds(152,322,120,120);
        patronMenu.setBorder(BorderFactory.createEtchedBorder());
        main.add(patronMenu);
    }
    private void createBorrowMenu(){
        JButton regBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton historyBtn = new JButton("History");
        createSubMenuButtons(historyBtn);
        createSubMenuButtons(regBtn);
        createSubMenuButtons(returnBtn);
        regBtn.addActionListener(e -> {
            NewBorrowing newBorrowing = new NewBorrowing();
            Attributes.changeWindow(internalFrame,newBorrowing.getFrame().getContentPane(),"Borrowings");
            borrowMenu.setVisible(false);
        });
        returnBtn.addActionListener(e -> {
            if (PatronManagement.getInstance().countPatrons()>0&&BookManagement.getInstance().countBooks()>0)
                if (BorrowManagement.getInstance().countBorrows()>0){
                    ReturnsInterface returns = new ReturnsInterface();
                    Attributes.changeWindow(internalFrame,returns.getFrame().getContentPane(),"Returns");
            }else {
                Toolkit.getDefaultToolkit().beep();
                regBtn.doClick();
                JOptionPane.showMessageDialog(null,"No Borrows Registered in the System!\n(Register a Borrow Before Trying to Return!)","",JOptionPane.WARNING_MESSAGE);
            }
            else {
                Toolkit.getDefaultToolkit().beep();
                regBtn.doClick();
                JOptionPane.showMessageDialog(null,"Ajeeb Yar! No Patrons & No Books in System)","",JOptionPane.WARNING_MESSAGE);
            }
            borrowMenu.setVisible(false);
        });
        historyBtn.addActionListener(e -> {
            if (PatronManagement.getInstance().countPatrons()>0&&BookManagement.getInstance().countBooks()>0)
                if (BorrowManagement.getInstance().countBorrows()>0){
                    ReturnHistory returns = new ReturnHistory();
                    Attributes.changeWindow(internalFrame,returns.getFrame().getContentPane(),"Returns");
                }else {
                    Toolkit.getDefaultToolkit().beep();
                    regBtn.doClick();
                    JOptionPane.showMessageDialog(null,"No Borrows Registered in the System!\n(Register a Borrow Before Trying to Return!)","",JOptionPane.WARNING_MESSAGE);
                }
            else {
                Toolkit.getDefaultToolkit().beep();
                regBtn.doClick();
                JOptionPane.showMessageDialog(null,"Ajeeb Yar! No Patrons & No Books in System)","",JOptionPane.WARNING_MESSAGE);
            }
            borrowMenu.setVisible(false);
        });
        borrowMenu.add(regBtn);
        borrowMenu.add(returnBtn);
        borrowMenu.add(historyBtn);
        borrowMenu.setVisible(false);
        borrowMenu.setBounds(152,365,120,120);
        borrowMenu.setBorder(BorderFactory.createEtchedBorder());
        main.add(borrowMenu);
    }
    private void createSubMenuButtons(JButton btn){
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setContentAreaFilled(false);
        btn.setPreferredSize(new Dimension(120,25));
        btn.setBorder(BorderFactory.createEtchedBorder());

    }
    private void hideSubMenus(){
        bookMenu.setVisible(false);
        borrowMenu.setVisible(false);
        patronMenu.setVisible(false);
    }
    private void createFrontPageBtn(){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/btnHome.png").getImage().getScaledInstance(120,30, Image.SCALE_SMOOTH));
        frontPage = new JButton(ic);
        frontPage.setContentAreaFilled(false);
        frontPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frontPage.addActionListener(e -> {
            FrontPage fp = new FrontPage();
            Attributes.changeWindow(internalFrame,fp.getFrame().getContentPane(),"fp");
            hideSubMenus();
        });
    }
    private void createBookBtn(){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/btnBook.png").getImage().getScaledInstance(120,30, Image.SCALE_SMOOTH));
        book = new JButton(ic);
        book.setCursor(new Cursor(Cursor.HAND_CURSOR));
        book.setContentAreaFilled(false);
        book.addActionListener(e -> {
            hideSubMenus();
            bookMenu.setVisible(!bookMenu.isVisible());
        });
        //book.setPreferredSize(new Dimension(120,30));
    }
    private void createPatronBtn(){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/btnPatron.png").getImage().getScaledInstance(120,30, Image.SCALE_SMOOTH));
        patron = new JButton(ic);
        patron.setCursor(new Cursor(Cursor.HAND_CURSOR));
        patron.setContentAreaFilled(false);
        patron.addActionListener(e -> {
            borrowMenu.setVisible(false);
            bookMenu.setVisible(false);
            patronMenu.setVisible(!patronMenu.isVisible());
        });
    }
    private void createBorrowsBtn(){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/btnBorrows.png").getImage().getScaledInstance(120,30, Image.SCALE_SMOOTH));
        borrow = new JButton(ic);
        borrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        borrow.setContentAreaFilled(false);
        borrow.addActionListener(e -> {
            patronMenu.setVisible(false);
            bookMenu.setVisible(false);
            borrowMenu.setVisible(!borrowMenu.isVisible());
        });
    }
    private void createAboutBtn(){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/btnAbout.png").getImage().getScaledInstance(120,30, Image.SCALE_SMOOTH));
        aboutBtn = new JButton(ic);
        aboutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutBtn.setContentAreaFilled(false);
        aboutBtn.addActionListener(e -> {
            hideSubMenus();
            AboutPage about = new AboutPage();
            Attributes.changeWindow(internalFrame,about.getFrame().getContentPane(),"about");
        });
    }
    private void lgOut(){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/qtBtn.png").getImage().getScaledInstance(120,30, Image.SCALE_SMOOTH));
        lgOut = new JButton(ic);
        lgOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lgOut.setContentAreaFilled(false);
        lgOut.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want to Logout?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
                LoginMainForm lgn = new LoginMainForm();
                lgn.nothing();
                main.dispose();}
        });
    }
    private void qtBtn(){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/quitBtn.png").getImage().getScaledInstance(120,30, Image.SCALE_SMOOTH));
        qtBtn = new JButton(ic);
        qtBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        qtBtn.setContentAreaFilled(false);
        qtBtn.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            if (JOptionPane.showConfirmDialog(null,"Are You Sure You Want to Quit?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION)
                System.exit(0);
        });
    }
    private void csLogo(){
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/logo.png").getImage().getScaledInstance(140,140, Image.SCALE_SMOOTH));
        logo = new JLabel(ic);
    }

}
