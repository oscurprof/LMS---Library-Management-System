package borrowings;

import attributes.MyText;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class BorrowReturnMethods {
    JFrame win; DefaultTableModel borrowingTable; JTable borrows; TableRowSorter<TableModel> sorter;
    protected BorrowReturnMethods(){
        createNewInterface();
    }
    protected void createNewInterface(){
        this.win = new JFrame("Patrons");
        this.win.setSize(800,800);
        this.win.setLayout(null);
    }
    protected void createSearchField()
    {
        MyText search = new MyText("Enter Text",10,10);
        search.addFrame(win);
        search.setBounds(110,10,300,20);
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/Find.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        JButton btn = new JButton(ic);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder());
        JButton clr = new JButton("Clear");
        btn.setBounds(430,10,30,30);
        btn.addActionListener(e -> search(search.getText()));
        clr.setBounds(520,10,80,20);
        clr.addActionListener(e -> {
            sorter.setRowFilter(null);
            search.setText("");
        });
        win.add(clr);
        win.add(btn);
    }
    protected void search(String txt){
        RowFilter<TableModel,Integer> filter = RowFilter.regexFilter("(?i)" +txt,1,2,3,4,5,6);
        sorter.setRowFilter(filter);
    }
    protected void changeColumnColor(TableColumn column){
        DefaultTableCellRenderer dfc = new DefaultTableCellRenderer();
        dfc.setForeground(Color.BLUE);
        column.setPreferredWidth(40);
        dfc.setHorizontalAlignment(JLabel.CENTER);
        column.setCellRenderer(dfc);
    }
    protected int getIndex(String bid){
        return BorrowManagement.getInstance().getIndex(bid);
    }
    public JFrame getFrame(){
        return win;
    }
}
