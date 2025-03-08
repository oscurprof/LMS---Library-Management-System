package login;

import attributes.Attributes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class LoginComponents {
    JLabel lbl6,lbl7,lbl8; //incorrect username&pwdLabels
    protected final JFrame lgn;
    protected JTextField usr,email;
    protected JPasswordField pwd;
    protected LoginComponents(String frameName,int width, int height){
        lgn = new JFrame(frameName);
        lgn.setSize(width,height);
        lgn.setLayout(null);    //No Built-in Layout
        lgn.getContentPane().setBackground(Color.white); //Set Background Color to White
        }

    protected void showWindow(){
        lgn.setLocationRelativeTo(null);
        lgn.setVisible(true);
    }
    protected void headingLabel(String txt){
        JLabel lbl1 = new JLabel(txt);
        lbl1.setBounds(30,40,300,30);
        lbl1.setFont(new Font("Calibri (Detail)",Font.BOLD,18));
        lbl1.setForeground(Color.BLACK);
        lgn.add(lbl1);
    }
    protected void createLabel(String txt,int x,int y, int width,int height){
        JLabel lbl = new JLabel(txt);
        lbl.setBounds(x,y,width,height);
        lbl.setFont(Attributes.def);
        lbl.setForeground(Color.BLACK);
        lgn.add(lbl);
    }
    protected JLabel errorlabel(String txt,int x,int y, int width,int height){
        JLabel lbl = new JLabel(txt);
        lbl.setBounds(x,y,width,height);
        lbl.setFont(new Font("Arial",Font.ITALIC,12));
        lbl.setForeground(Color.RED);
        lbl.setVisible(false);
        lgn.add(lbl);
        return lbl;
    }
    protected void createUserField(int x, int y, int width, int height){
        usr = new JTextField("Enter Username");
        usr.setBounds(x,y,width,height);
        Attributes.CustomBorders(usr);
        usr.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usr.getText().equals("Enter Username"))
                    usr.setText("");
                try{lbl6.setVisible(false);}
                catch (NullPointerException ignore){}
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (usr.getText().isBlank())
                    usr.setText("Enter Username");
            }
        });
        lgn.add(usr);
        usr.setVisible(true);
    }
    protected void createPasswordField(int x, int y, int width, int height){
        pwd = new JPasswordField("Enter Password");
        Attributes.CustomBorders(pwd);
        pwd.setEchoChar((char) 0);
        pwd.setBounds(x,y,width,height);
        pwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (pwd.getText().equals("Enter Password")) {
                    pwd.setText("");
                    pwd.setEchoChar('*');
                }
                //if (lgn.getName().equals("Login"))
                try{lbl7.setVisible(false);}
                catch (Exception ignore){}
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (pwd.getText().isBlank()) {
                    pwd.setEchoChar((char) 0);
                    pwd.setText("Enter Password");
                }
            }
        });
        lgn.add(pwd);
        pwd.setVisible(true);
    }
    protected void createEmailField(int x, int y, int width, int height) {
        email = new JTextField("Enter Email");
        Attributes.CustomBorders(email);
        email.setBounds(x,y,width,height);
        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (email.getText().equals("Enter Email"))
                    email.setText("");
                try{lbl8.setVisible(false);}
                catch (Exception ignore){}
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().isBlank())
                    email.setText("Enter Email");
            }
        });
        lgn.add(email);
    }
    protected void pwdbtn(int x, int y, int width, int height){
        JToggleButton rpwd = new JToggleButton();
        ImageIcon eye = new ImageIcon(new ImageIcon("resources/EYE.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        ImageIcon eye2 = new ImageIcon(new ImageIcon("resources/EYE2.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        rpwd.setBorder(new EmptyBorder(0,0,0,0));
        rpwd.setIcon(eye);
        rpwd.setBounds(x,y,width,height);
        ItemListener chang = e -> {
            int state = e.getStateChange();
            if (state== ItemEvent.SELECTED){
                rpwd.setIcon(eye2);
                pwd.setEchoChar((char) 0);
            }
            else {
                rpwd.setIcon(eye);
                if (pwd.getText().equals("Enter Password"))
                    pwd.setEchoChar((char) 0);
                else
                    pwd.setEchoChar('*');
            }
        };
        rpwd.addItemListener(chang);
        lgn.add(rpwd);
    }
    public String getPwd() {
        return pwd.getText();
    }

    public String getUsr() {
        return usr.getText();
    }

    public String getEmail() {
        return email.getText();
    }

}
