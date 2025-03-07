package login;
import javax.swing.*;
import java.awt.*;

//Design A New Login Window, Having a Large Image and Internal Frame
public class LoginMainForm {
    protected static JFrame main;
    protected static JInternalFrame internalFrame;
    public LoginMainForm(){
    ImageIcon ic = new ImageIcon(new ImageIcon("resources/LIB.png").getImage().getScaledInstance(760,570, Image.SCALE_SMOOTH));
    JLabel img = new JLabel(ic);
    img.setBounds(30,120,760,570);
    //*********
    main = new JFrame("User Authentication");
    main.setSize(850,850);
    main.setLayout(null);
    main.setLocationRelativeTo(null);
    LoginWindow loginWindow = new LoginWindow(340,560);
    //***********//**********//Inner Form\\*********\\*********\\
    // **********************************************************\\
    internalFrame = new JInternalFrame("", false, false, false, true);
    internalFrame.add(loginWindow.getFrame().getContentPane()); //adding patronForm to internal frame
    internalFrame.setBorder(BorderFactory.createEmptyBorder()); //removing borders
    ((javax.swing.plaf.basic.BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null); //Remove the heading label
    internalFrame.setBounds(430, 120, 340, 560); //setting dimensions
    internalFrame.setVisible(true); //setting visible
    internalFrame.setOpaque(false);
    // **********************************************************\\

    main.add(internalFrame);
    main.add(img);
    main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    main.setVisible(true);
    }
    public void nothing()
    {
        System.out.println();
    }
}
