package login;
import attributes.Attributes;
import mainmenu.MainMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class RegistrationWindow extends LoginComponents {
    public RegistrationWindow(int width, int height) {
        super("Registration", width, height);
        //super.lgn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.headingLabel("CREATE A NEW ACCOUNT"); //LoginIntoAccount Label
        super.createLabel("User Name", 10, 120, 200, 30); //Username Label
        super.createLabel("Password", 10, 190, 200, 30); //Password Label
        super.createLabel("Email", 10, 260, 200, 30);
        super.createUserField(10,152,300,20); //create custom user field
        /*
        MyTextField bnb = new MyTextField();
        bnb.addFrame(super.win);
        * */
        super.createPasswordField(10,222,280,20); //create custom password field
        super.createEmailField(10, 292, 300, 20); //create custom email field
        super.pwdbtn(290,215,30,30);
        super.lbl6 = errorlabel("Invalid Username",10,170,300,20);
        super.lbl7=errorlabel("Too Short Password!",10,240,280,20);
        super.lbl8 = errorlabel("Invalid Email!",10,310,300,20);
        backBtn(100, 385, 100, 30);
        signupbtn();
    }

    public void showRegistrationWindow() {
        showWindow();
    }
    private void registerUser(){
        User user = new User(getUsr(),getPwd(),getEmail());
        UserManagement.getInstance().adduser(user);
        MainMenu mainMenu = new MainMenu();
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null,"Your Account Has Been Created Successfully!");
        LoginMainForm.main.dispose();
        //RegistrationWindow.super.lgn.dispose();
    }
    private void signupbtn() {
        JButton btn = new JButton("Signup");
        btn.setBounds(100, 350, 100, 30);
        btn.setBackground(new Color(0x0072BC));
        btn.setForeground(Color.white);
        btn.setCursor(Attributes.handcursor);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isvalidUsername() && isvalidPassword() && isvalidEmail())
                    registerUser();
                // && bnb.getText().equals(pwd.getText())
                //System.out.println("\nUsername: " + getUsr() + "\nPassword: " + getPwd());
            }
        });
        lgn.add(btn);
    }
    private boolean isvalidEmail(){
        if(email.getText().contains("@")){
            return true;
        }else
        {
            if (email.getText().equals("Enter Email"))
                lbl8.setText("*Required (Can't be left Blank!)");
            else if (email.getText().contains(" ")) {
                lbl8.setText("Invalid Input (Spaces Not Allowed)");
            } else
                lbl8.setText("Invalid Email (@ is missing)");
            super.lbl8.setVisible(true);
            super.email.setBorder(Attributes.rborder);
            Toolkit.getDefaultToolkit().beep();
            return false;
        }
    }
    private boolean isvalidUsername(){
        boolean duplicate = UserManagement.getInstance().duplicateUser(getUsr());
        if (usr.getText().equals("Enter Username") || duplicate || usr.getText().contains(" ")){
            if (usr.getText().equals("Enter Username"))
                super.lbl6.setText("*Required (Can't be left Blank!)");
            else if (usr.getText().contains(" ")) {
                super.lbl6.setText("Invalid Input (Spaces Not Allowed)");
            } else
                super.lbl6.setText("User Already Exists!");
            super.lbl6.setVisible(true);
            super.usr.setBorder(Attributes.rborder);
            Toolkit.getDefaultToolkit().beep();
            return false;
        }else
            return true;
    }

    private boolean isvalidPassword(){
        if(getPwd().contains(" ") || getPwd().length()<4)
        {
            if (getPwd().equals("Enter Password"))
                lbl7.setText("*Required (Can't be left Blank!)");
            else if(getPwd().contains(" "))
                lbl7.setText("Invalid Password (Spaces Not Allowed!)");
            else
                lbl7.setText("Password Too Short! (At-least 4 Characters required!)");
            super.lbl7.setVisible(true);
            super.pwd.setBorder(Attributes.rborder);
            Toolkit.getDefaultToolkit().beep();
            return false;
        }
        else return true;
    }
    public JFrame getFrame() {
        return lgn;
    }
    private void backBtn(int x, int y, int width,int height){
        JButton qt = new JButton("Back");
        qt.setBounds(x,y,width,height);
        qt.setBackground(new Color(0xBF141B));
        qt.setForeground(Color.white);
        qt.setCursor(Attributes.handcursor);
        qt.addActionListener(e -> {
            if (usr.getText().isBlank()||usr.getText().equals("Enter Username"))
                backToLoginForm();
            else {
                Toolkit.getDefaultToolkit().beep(); //Perform a Beep
                if(JOptionPane.showInternalConfirmDialog(null,"Account not created yet, Information will be unsaved\nContinue?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==0)
                    backToLoginForm();
            }
        });
        super.lgn.add(qt);
    }
    private void backToLoginForm(){
        LoginWindow login = new LoginWindow(340,560);
        //change to signup window
        LoginMainForm.internalFrame.getContentPane().removeAll();
        LoginMainForm.internalFrame.add(login.getFrame().getContentPane());
        LoginMainForm.internalFrame.setTitle("login");
        LoginMainForm.internalFrame.revalidate();
        LoginMainForm.internalFrame.repaint();
    }
}