package attributes;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
//Class Containing Some Common things that can be used among others
public class Attributes {
    public static boolean lgn = false;
    public static Font def = new Font("Calibri (Detail)",Font.BOLD,16); //Custom Font
    public static Font fed = new Font("Calibri (Detail)",Font.BOLD,14); //Custom Font
    public static Cursor handcursor = new Cursor(Cursor.HAND_CURSOR);
    public static Border bborder = BorderFactory.createMatteBorder(0,0,1,0,Color.darkGray);
    public static Border gborder = BorderFactory.createMatteBorder(0,0,1,0,Color.GREEN);
    public static Border rborder = BorderFactory.createMatteBorder(0,0,1,0,Color.RED);
    public static JTextField CustomBorders(JTextField textfield){

        textfield.setBorder(bborder);
        textfield.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textfield.setBorder(gborder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textfield.setBorder(bborder);
            }
        });
        textfield.setOpaque(false);
        return textfield;
    }
    //as quit button is something common, so created that here...
    public static void qtbtn(JFrame frame,int x, int y, int width,int height){
        JButton qt = new JButton("Quit");
        qt.setBounds(x,y,width,height);
        qt.setBackground(new Color(0xBF141B));
        qt.setForeground(Color.white);
        qt.setCursor(Attributes.handcursor);
        qt.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep(); //Perform a Beep
            if(JOptionPane.showInternalConfirmDialog(null,"Are You Sure You Want To Quit?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==0)
                System.exit(0);
        });
        frame.add(qt);
    }
    public static void headingLabel(JFrame frame,String txt, int x, int y){
        JLabel lbl = new JLabel(txt);
        lbl.setBounds(x,y,500,30);
        lbl.setFont(new Font("Montserrat", Font.PLAIN,24));
        lbl.setForeground(Color.BLACK);
        frame.add(lbl);
    }
    public static void createCustomLabel(JFrame frame, String text,int x,int y){
        JLabel customLabel = new JLabel(text);
        customLabel.setBounds(x,y,150,20);
        customLabel.setFont(Attributes.fed);
        frame.add(customLabel);
    }
    public static void changeWindow(JInternalFrame internalFrame, Component cmp,String title){
        //change the window
        internalFrame.getContentPane().removeAll();
        internalFrame.add(cmp);
        internalFrame.setTitle(title);
        internalFrame.revalidate();
        internalFrame.repaint();
    }
}
