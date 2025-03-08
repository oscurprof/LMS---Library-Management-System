package login;
import attributes.Attributes;
import mainmenu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends LoginComponents {
    public LoginWindow(int width, int height){
        super("Login", width, height);
        //UserManagement.getInstance().loaduserfromfile("data/users.txt"); //Load the username/password from the file
        super.lgn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.headingLabel("LOGIN INTO ACCOUNT"); //LoginIntoAccount Label
        super.createLabel("User Name",10,140,200,30); //Username Label
        super.createLabel("Password",10,210,200,30); //Password Label
        super.createUserField(10,172,300,20); //create custom user field
        super.createPasswordField(10,242,280,20); //create custom password field
        Attributes.qtbtn(super.lgn,100,335,100,30); //creating a quit button
        super.pwdbtn(290,235,30,30);
        label4(); //Don't have an Account?
        label5();//Forgot Password
        super.lbl6 = errorlabel("User Not Found!",10,190,300,20);
        super.lbl7=errorlabel("Incorrect Password",10,260,280,20);
        lgnbtn();

    }
    public void showloginwindow(){
        super.showWindow();
    }
    private void label4(){
        JLabel lbl = new JLabel("Don't Have Account? ");
        lbl.setBounds(60,380,120,30);
        JLabel lblx = new JLabel("<html><a href=''>Register Here</a></html>");
        lblx.setBounds(182,380,80,30);
        lblx.setCursor(Attributes.handcursor);
        lblx.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toRegistrationForm();
            }
        });
        lgn.add(lbl);
        lgn.add(lblx);
    }
    private void toRegistrationForm(){
        RegistrationWindow signup = new RegistrationWindow(340,560);
        //change to signup window
        LoginMainForm.internalFrame.getContentPane().removeAll();
        LoginMainForm.internalFrame.add(signup.getFrame().getContentPane());
        LoginMainForm.internalFrame.setTitle("signup");
        LoginMainForm.internalFrame.revalidate();
        LoginMainForm.internalFrame.repaint();
    }
    private void label5(){
        JLabel lbl5 = new JLabel();
        lbl5.setText("<html><a href=''>Forgot Password?</a></html>");
        lbl5.setBounds(210,260,300,30);
        lbl5.setCursor(Attributes.handcursor);
        lbl5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Focus & Try to remember the Password!");
            }
        });
        lgn.add(lbl5);
    }
    private void lgnbtn(){
        JButton btn = new JButton("Login");
        btn.setBounds(100,300,100,30);
        btn.setBackground(new Color(0x0072BC));
        btn.setForeground(Color.white);
        btn.setCursor(Attributes.handcursor);
        btn.addActionListener(e -> login());
        lgn.add(btn);
    }
    private void login(){
        String msg =  UserManagement.getInstance().validateLogin(getUsr(),getPwd());
        //System.out.println(msg);
        if (msg.equals("success")) {
            //lgn.dispose();
            MainMenu mainMenu = new MainMenu();
            LoginMainForm.main.dispose();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Login Successful!");

        }else if (msg.equals("usr")) {
            lbl6.setText("User Not Found!");
            lbl6.setVisible(true);
            usr.setBorder(Attributes.rborder);
            Toolkit.getDefaultToolkit().beep();

        }else if(msg.equals("pwd")){
            lbl7.setText("Invalid Password");
            lbl7.setVisible(true);
            pwd.setBorder(Attributes.rborder);
            Toolkit.getDefaultToolkit().beep();
        }
    }
    public JFrame getFrame() {
        return lgn;
    }

}
