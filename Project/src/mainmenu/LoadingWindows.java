package mainmenu;

import book.BookManagement;
import borrowings.BorrowManagement;
import login.LoginMainForm;
import login.UserManagement;
import patron.PatronManagement;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public final class LoadingWindows {
    JFrame loading;
    public LoadingWindows(){
        loading = new JFrame();
        //loadData();
        loading.setSize(800,536);
        loading.setLayout(null);
        loading.setLocationRelativeTo(null);
        loading.getContentPane().setBackground(new Color(0xf5f5f5));
        loading.setUndecorated(true);
        loading.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon ic = new ImageIcon("resources/loadingMain.gif");
        JLabel img = new JLabel(ic);
        img.setBounds(0,0,800,536);
        loading.add(img);
    }
    private void toLoginForm(){
        LoginMainForm login = new LoginMainForm();
        login.nothing();
        this.loading.dispose();
    }
    private void loadData(){
        UserManagement.getInstance().loaduserfromfile();
        BookManagement.getInstance().loadBooks();
        PatronManagement.getInstance().loadPatrons();
        BorrowManagement.getInstance().loadBorrows();
    }
    public void beginShow(){
        loading.setVisible(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadData();
                toLoginForm();

            }
        },4000);
    }
}
