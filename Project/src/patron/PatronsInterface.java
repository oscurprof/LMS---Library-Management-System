package patron;

import attributes.Attributes;
import attributes.MyText;
//import book.BookManagement;
import borrowings.BorrowManagement;
import borrowings.BorrowingsInterface2;
import mainmenu.MainMenu;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PatronsInterface {
    JFrame win; DefaultTableModel patronTable; JTable patrons; TableRowSorter<TableModel> sorter;
    public PatronsInterface(){
        createNewInterface();
        PatronInterface patronInterface = new PatronInterface();
        JInternalFrame internalFrame = new JInternalFrame("", false, false, false, true);
        BorrowingsInterface2 borrowingsInterface = new BorrowingsInterface2();
        patronTable = PatronTable.patronTable();
        patrons=new JTable(patronTable);
        patrons.getTableHeader().setFont(new Font("Arial",Font.BOLD,12));
        patrons.getTableHeader().setReorderingAllowed(false);
        JScrollPane jsp = new JScrollPane(patrons);
        jsp.setBounds(10,50,760,100);

        //*********** Creating Search Field & Adding Sorter ***********//
        createSearchField();
        sorter = new TableRowSorter<>(patronTable);
        patrons.setRowSorter(sorter);

        //******** Changing Color ********\\
        changeColumnColor(patrons.getColumnModel().getColumn(0));
        changeColumnColor(patrons.getColumnModel().getColumn(1));
        changeColumnColor(patrons.getColumnModel().getColumn(2));

        patrons.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = patrons.rowAtPoint(e.getPoint());
                int column = patrons.columnAtPoint(e.getPoint());
                if(column==0) {
                    Attributes.changeWindow(internalFrame,patronInterface.getFrame().getContentPane(),"Edit");
                    patronInterface.setData(getIndex((String) patrons.getValueAt(row, 3))); //pass membership id to get index
                }else if(column==1) {
                    Attributes.changeWindow(internalFrame,borrowingsInterface.getFrame().getContentPane(),"Borrow");
                    borrowingsInterface.loadPatron((String) patrons.getValueAt(row, 3));
                } else if (column==2){
                    deletePatron((String) patrons.getValueAt(row,3),row);//delete patron
                }
            }
        });

        patrons.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (patrons.columnAtPoint(e.getPoint())==0 || patrons.columnAtPoint(e.getPoint())==1 || patrons.columnAtPoint(e.getPoint())==2)
                    patrons.setCursor(Attributes.handcursor);
                else
                    patrons.setCursor(Cursor.getDefaultCursor());
            }
        });
        //***********//**********//Inner Form\\*********\\*********\\
        // **********************************************************\\

        internalFrame.add(patronInterface.getFrame().getContentPane()); //adding patronForm to internal frame
        internalFrame.setBorder(BorderFactory.createEmptyBorder()); //removing borders
        ((javax.swing.plaf.basic.BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null); //Remove the heading label
        internalFrame.setBounds(10, 150, 760, 800); //setting dimensions
        internalFrame.setVisible(true); //setting visible
        // **********************************************************\\
        win.add(jsp);
        win.add(internalFrame);
        //win.setVisible(true);
    }
    protected void createNewInterface(){
        this.win = new JFrame("Patrons");
        this.win.setSize(800,800);
        this.win.setLayout(null);
    }
    private void createSearchField()
    {
        MyText search = new MyText("Enter Text",10,10);
        search.addFrame(win);
        search.setBounds(110,10,300,20);
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/Find.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        JButton btn = new JButton(ic);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder());
        JButton clr = new JButton("Clear");
        btn.setBounds(430,10,30,30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search(search.getText());
            }
        });
        clr.setBounds(520,10,80,20);
        clr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter(null);
                search.setText("");
            }
        });
        win.add(clr);
        win.add(btn);
    }
    private void search(String txt){
        RowFilter<TableModel,Integer> filter = RowFilter.regexFilter("(?i)" +txt,3,4,5,6,7,8);
        sorter.setRowFilter(filter);
    }
    private void changeColumnColor(TableColumn column){
        DefaultTableCellRenderer dfc = new DefaultTableCellRenderer();
        if (column.getModelIndex()==0)
            dfc.setForeground(Color.BLUE);
        else if (column.getModelIndex()==1)
            dfc.setForeground(new Color(0x126c08));
        else if (column.getModelIndex()==2){
            dfc.setForeground(Color.RED);}
        column.setPreferredWidth(40);
        dfc.setHorizontalAlignment(JLabel.CENTER);
        column.setCellRenderer(dfc);
    }
    private int getIndex(String memID){
        return PatronManagement.getInstance().getIndex(memID);
    }
    private void deletePatron(String memID,int row){
        Toolkit.getDefaultToolkit().beep();
        if (JOptionPane.showConfirmDialog(null,"Are You Sure to Delete this Record?\n(All the Related Borrowings will also be deleted)","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==0){
            BorrowManagement.getInstance().patronDeleted(memID); //delete related borrowings
            PatronManagement.getInstance().deletePatron(memID);
            try {
                MainMenu.patronsList.doClick();
            }catch (Exception ignored){}
        }
    }
    public JFrame getFrame(){
        return win;
    }
}
