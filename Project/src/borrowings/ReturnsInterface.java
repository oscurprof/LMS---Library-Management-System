package borrowings;
import attributes.Attributes;
import attributes.MyText;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReturnsInterface extends BorrowReturnMethods {
    public ReturnsInterface(){
        Returns returns = new Returns();
        borrowingTable = BorrowingTable.borrowingTable();
        borrows=new JTable(borrowingTable);
        borrows.getTableHeader().setFont(new Font("Arial",Font.BOLD,12));
        borrows.getTableHeader().setReorderingAllowed(false);
        JScrollPane jsp = new JScrollPane(borrows);
        jsp.setBounds(10,50,760,100);

        //*********** Creating Search Field & Adding Sorter ***********//
        createSearchField();
        sorter = new TableRowSorter<>(borrowingTable);
        borrows.setRowSorter(sorter);

        //******** Changing Color ********\\
        changeColumnColor(borrows.getColumnModel().getColumn(0));
        changeColumnColor(borrows.getColumnModel().getColumn(1));
        borrows.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = borrows.rowAtPoint(e.getPoint());
                int column = borrows.columnAtPoint(e.getPoint());
                if(column==1)
                    returns.loadData(getIndex((String) borrows.getValueAt(row,2))); //pass borrow id to get index
                else if (column==0)
                    System.out.println("wait Nigga! & lemme do the work");
                //deletePatron((String) borrows.getValueAt(row,2),row);
            }
        });

        borrows.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (borrows.columnAtPoint(e.getPoint())==0 || borrows.columnAtPoint(e.getPoint())==1)
                    borrows.setCursor(Attributes.handcursor);
                else
                    borrows.setCursor(Cursor.getDefaultCursor());
            }
        });
        //***********//**********//Inner Form\\*********\\*********\\
        // **********************************************************\\
        JInternalFrame internalFrame = new JInternalFrame("", false, false, false, true);
        internalFrame.add(returns.getFrame().getContentPane()); //adding patronForm to internal frame
        internalFrame.setBorder(BorderFactory.createEmptyBorder()); //removing borders
        ((javax.swing.plaf.basic.BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null); //Remove the heading label
        internalFrame.setBounds(10, 150, 760, 800); //setting dimensions
        internalFrame.setVisible(true); //setting visible
        // **********************************************************\\
        win.add(jsp);
        win.add(internalFrame);
        //win.setVisible(true);
    }
}
