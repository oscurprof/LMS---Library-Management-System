package patron;
import attributes.Attributes;
import mainmenu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class PatronInterface extends PatronFields{
    int index;
    public PatronInterface() {
        super();
        Attributes.headingLabel(super.win,"Patron Details",10,40);
        setData(0);
        createSaveButton();
        membershipID.setEnabled(false);
        membershipID.setDisabledTextColor(Color.BLACK);
        //super.win.setVisible(true);
        //super.win.repaint();
        //super.win.revalidate();

    }
    protected void setData(int index){
        //PatronManagement.getInstance().loadPatrons();
        this.index=index;
        super.membershipID.setText(PatronManagement.getInstance().getPatrons().get(index).getMembershipID());
        super.name.setText(PatronManagement.getInstance().getPatrons().get(index).getName());
        super.email.setText(PatronManagement.getInstance().getPatrons().get(index).getEmail());
        super.membershipType.setText(PatronManagement.getInstance().getPatrons().get(index).getMembershipType());
        //this.expiryDate=new JFormattedTextField(simpleDateFormat);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        super.expiryDate.setText(dateFormat.format(PatronManagement.getInstance().getPatrons().get(index).getExpiryDate()));
    }
    @Override
    protected void createSaveButton(){
        save = new JButton("Save");
        save.setBounds(130,340,100,30);
        save.setCursor(Attributes.handcursor);
        save.addActionListener(e -> saveAction()); //replaced with lambda
        win.add(save);
    }
    private void saveAction(){
        boolean proceed = membershipID.prompt()&& name.prompt()&& email.prompt()&&!expiryDate.getText().isBlank();
        //searches for Patron with same ID
        int i =-1;
        for (Patron patron: PatronManagement.getInstance().getPatrons()){
            i=i+1;
            if (i==index) //just making sure same book isbn is not checked
                continue;
            if (patron.getMembershipID().equals(super.membershipID.getText()))
            {
                membershipID.setPromptLabel("Patron Already Exists!");
                return;
            }
        }
        if (proceed){
            Patron patron = super.authenticatedData(); //Returns Store able form of data
            PatronManagement.getInstance().getPatrons().get(index).setMembershipID(patron.getMembershipID());
            PatronManagement.getInstance().getPatrons().get(index).setName(patron.getName());
            PatronManagement.getInstance().getPatrons().get(index).setEmail(patron.getEmail());
            PatronManagement.getInstance().getPatrons().get(index).setMembershipType(patron.getMembershipType());
            PatronManagement.getInstance().getPatrons().get(index).setExpiryDate(patron.getExpiryDate());
            PatronManagement.getInstance().savePatron();
            try {
                MainMenu.patronsList.doClick();
            }catch (Exception ignored){}
        }

    }
}
