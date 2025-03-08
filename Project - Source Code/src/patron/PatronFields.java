package patron;

import attributes.Attributes;
import attributes.MyText;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatronFields {
    MyText membershipID,name,email,membershipType;
    JFormattedTextField expiryDate;
    JFrame win;
    JButton save;
    public PatronFields(){
        win=new JFrame();
        win.setSize(800,800);
        win.setLayout(null);
        this.membershipID=new MyText("Membership ID",10,120);
        this.membershipID.isRequired();
        this.name=new MyText("Name",10,160);
        this.name.isRequired();
        this.email=new MyText("Email",10,200);
        this.email.isRequired();
        this.membershipType=new MyText("Membership Type",10,240);
        createDateField();
        //setDefaultDate();
        addFields();
        //this.createSaveButton();
        //win.setVisible(true);
    }
    private void addFields(){
        membershipID.addFrame(win);
        name.addFrame(win);
        email.addFrame(win);
        membershipType.addFrame(win);
        win.add(expiryDate);
    }
    private void createDateField(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.expiryDate=new JFormattedTextField(simpleDateFormat);
        Attributes.CustomBorders(expiryDate);
        Attributes.createCustomLabel(win,"Expiry Date:",10,280);
        this.expiryDate.setBounds(165,280,250,20);
    }
    protected void setDefaultDate(){
        Date date = new Date();
        date.setYear(date.getYear()+2);
        expiryDate.setValue(date);
    }
    protected void createSaveButton(){
        save = new JButton("Save");
        save.setBounds(130,340,100,30);
        save.setCursor(Attributes.handcursor);
        save.addActionListener(e -> saveAction()); //replaced with lambda
        win.add(save);
    }
    private void saveAction(){
        //checks if any of required fields aren't empty!
        boolean proceed = membershipID.prompt()&& name.prompt()&& email.prompt()&&!expiryDate.getText().isBlank();
        //searches for Patron with same ID
        if (PatronManagement.getInstance().duplicateID(membershipID.getText())){
            //if a duplicate Patron is found, don't proceed further!
            membershipID.setPromptLabel("Patron Already Exists!");
            return;
        }
        //if everything is valid (proceed is true), then add book function will be called
        //authenticatedData() returns User object having all good data (in form that can be stored)
        if (proceed){
            PatronManagement.getInstance().addPatron(authenticatedData());
            JOptionPane.showMessageDialog(null,"Saved Successfully");
            setEmpty();
        }
    }

    protected Patron authenticatedData() {
        //Manages the text, and stores in Patron object and returns it!
        Patron patron = new Patron();
        patron.setMembershipID(membershipID.getText());
        patron.setName(name.getText());
        patron.setEmail(email.getText());
        //converting date from string into text
        try {
            Date date = new SimpleDateFormat("MM/dd/yyyy").parse(expiryDate.getText());
            patron.setExpiryDate(date);
        }
        catch (ParseException pe)
        {
            System.out.println("\nMessage: "+pe.getMessage());
        }


        if (membershipType.getText().equals("Enter Membership Type"))
            patron.setMembershipType("");
        else
            patron.setMembershipType(membershipType.getText());
        //System.out.println(patron.getExpiryDate());
        //patron.printAll();
        return patron;
    }
    private void setEmpty(){
        this.name.setText("");
        this.membershipID.setText("");
        this.email.setText("");
        this.membershipType.setText("");
        this.setDefaultDate();
    }
    public JFrame getFrame(){
        return win;
    }

}
