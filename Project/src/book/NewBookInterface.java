package book;
import attributes.*;
public class NewBookInterface extends BookFields {
    public NewBookInterface(){
        super.createNewInterface();
        Attributes.headingLabel(super.win,"New Book Registration",10,40);
        //Default Values for Some Fields
        super.language.setText("English");
        super.fine.setText("20");
        super.addToFrame();
        super.createSaveButton();
        //super.win.setVisible(true);
    }
}