package mainmenu;

import javax.swing.*;
import java.awt.*;

public class FrontPage {
    JFrame frontPage;
    FrontPage(){
        frontPage=new JFrame();
        frontPage.setSize(800,800);
        frontPage.setLayout(null);
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/fp.png").getImage().getScaledInstance(580,320,Image.SCALE_SMOOTH));
        //ImageIcon ic = new ImageIcon(new ImageIcon("resources/loading.gif").getImage().getScaledInstance(760,570, Image.SCALE_DEFAULT));
        ImageIcon ic2 = new ImageIcon(new ImageIcon("resources/librarypp.png").getImage().getScaledInstance(580,320,Image.SCALE_SMOOTH));
        JLabel pic =  new JLabel(ic2);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(ic);

        jLabel.setBounds(50,350,500,500);
        //jLabel.setFont(new Font("Arial",Font.BOLD,60));
        //frontPage.add(pic);
        frontPage.add(jLabel);
    }

    public JFrame getFrame() {
        return frontPage;
    }
}
