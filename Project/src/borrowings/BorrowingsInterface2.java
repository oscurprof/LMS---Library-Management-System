package borrowings;

import java.awt.*;

public class BorrowingsInterface2 extends NewBorrowing {
    public BorrowingsInterface2(){
        super();
        //loadBook("Crimson");
        super.srcPatron.setVisible(false);
        super.memID.setEnabled(false);
        super.memID.setDisabledTextColor(Color.BLACK);
        //super.getFrame().setVisible(true);
    }
    public void loadPatron(String txt){
        super.memID.setText(txt);
        super.srcPatron.doClick();
    }
}
