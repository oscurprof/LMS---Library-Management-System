package mainmenu;

import javax.swing.*;
import java.awt.*;

public class AboutPage {
    JFrame frontPage;
    AboutPage(){
        frontPage=new JFrame();
        frontPage.setSize(800,800);
        frontPage.setLayout(null);
        ImageIcon ic = new ImageIcon("resources/about.png");
        JLabel jLabel = new JLabel();
        jLabel.setIcon(ic);

        jLabel.setBounds(0,0,750,750);
        frontPage.add(jLabel);
    }

    public JFrame getFrame() {
        return frontPage;
    }
}
