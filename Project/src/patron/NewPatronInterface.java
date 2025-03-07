package patron;

import attributes.Attributes;

public class NewPatronInterface extends PatronFields {
    public NewPatronInterface(){
        super();
        Attributes.headingLabel(super.win,"New Patron Registration",10,40);
        super.setDefaultDate();
        super.createSaveButton();
        //super.win.setVisible(true);
    }
}
