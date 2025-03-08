package attributes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyText extends JTextField {
    private JLabel customLabel,promptLabel;
    private boolean required;
    private int x,y,width,height;
    private String txt;

    public JLabel getCustomLabel() {
        return customLabel;
    }

    public MyText(String label, int x, int y){
        txt = "Enter "+label;
        this.x=x;this.y=y;this.width=250;this.height=20; //NeededForCustomLabel&PromptLabel
        required=false;
        setBounds((x+155),y,width,height); //CreateTextFieldRightToTheLabel
        Attributes.CustomBorders(this);
        this.textdisplay(txt); //DisplayTextInField&RemoveOnFocus
        this.createCustomLabel(label+":"); //creating custom label
    }
    public void isRequired(){
        createPromptLabel(); //create prompt label only if field is required
        required=true;
    }
    private void createCustomLabel(String text){
        customLabel = new JLabel(text);
        customLabel.setBounds(x,y,150,height);
        customLabel.setFont(Attributes.fed);
    }

    public JLabel getPromptLabel() {
        return promptLabel;
    }

    private void createPromptLabel(){
        promptLabel=new JLabel("Required Field Can't be Left Blank");
        promptLabel.setBounds((x+155),(y+(height-2)),width,height);
        promptLabel.setFont(new Font("Arial",Font.ITALIC,12));
        promptLabel.setForeground(Color.RED);
        promptLabel.setVisible(false);
    }
    private void textdisplay(String text){
        setText(text);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(text))
                    setText("");
                try{promptLabel.setVisible(false);}
                catch (NullPointerException ignore){}
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isBlank() && required)
                    setBorder(Attributes.rborder);
                    //setText(text);
            }
        });
    }
    public void addFrame(JFrame frame){
        //this.frame=frame;
        frame.add(this);
        frame.add(customLabel);
        if (required)
            frame.add(promptLabel);
    }

    public void setPromptLabel(String promptLabel) {
        this.promptLabel.setText(promptLabel);
        this.promptLabel.setVisible(true);
        Toolkit.getDefaultToolkit().beep();
        setText("");
        setBorder(Attributes.rborder);
    }
    public boolean prompt() {
        this.promptLabel.setText("Required Field Can't be Left Blank");
        if (getText().equals(txt)||getText().isBlank()){
        this.promptLabel.setVisible(true);
        Toolkit.getDefaultToolkit().beep();
        setText("");
        setBorder(Attributes.rborder);
        return false;
        }else return true;
    }
}
