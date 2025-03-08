package book;
import attributes.Attributes;
import attributes.MyText;
import borrowings.BorrowManagement;
import borrowings.BorrowingsInterface1;
import mainmenu.MainMenu;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BooksInterface {
    JInternalFrame internalFrame;
    JFrame win; DefaultTableModel bookTable; JTable books; TableRowSorter<TableModel> sorter;
    public BooksInterface(){
        createNewInterface();
        //creating these for inner form
        BookInterface bookInterface = new BookInterface();
        BorrowingsInterface1 borrowingsInterface = new BorrowingsInterface1();
        //Creating JTable, bookTable is a method created in BookTable Class
        bookTable = BookTable.bookTable();
        //assigning the data to table (all the books)
        books = new JTable(bookTable);
        books.getTableHeader().setFont(new Font("Arial",Font.BOLD,12));
        JScrollPane jsp = new JScrollPane(books); //I guess it's frame needed to display table
        jsp.setBounds(10,50,760,100); //Location on Screen
        //************************//************//***********//**************//************//****//

        createSearchField();
        //******Adding Sorter That'll be used for searching
        sorter = new TableRowSorter<>(bookTable); //initialized the sorter to our table model (data)
        books.setRowSorter(sorter); //then settled it on our table (display)
        books.getTableHeader().setReorderingAllowed(false); //Did not allow reordering as some functions depends on column no.s
        //*******These two are only for customizing columns colors, centralizing etc
        changeColumnColor(books.getColumnModel().getColumn(0));
        changeColumnColor(books.getColumnModel().getColumn(1));
        changeColumnColor(books.getColumnModel().getColumn(2));

        //evaluating click on open/delete button in table
        books.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = books.rowAtPoint(e.getPoint());
                int column = books.columnAtPoint(e.getPoint());
                if(column==2){ //delete button
                    deleteBook((String) books.getValueAt(row,3),row);
                    }
                else if (column==1) //open button
                {
                    Attributes.changeWindow(internalFrame,borrowingsInterface.getFrame().getContentPane(),"Borrow");
                    borrowingsInterface.loadBook((String) books.getValueAt(row, 3));
                }
                else if (column==0) //open button
                {
                    Attributes.changeWindow(internalFrame,bookInterface.getFrame().getContentPane(),"Edit");
                    bookInterface.setValues(getIndex((String) books.getValueAt(row, 3)));
                }//System.out.println("Index: "+));

            }
        });
        //Displaying Hand Symbol in case of Column 0 & 1 (Open & Delete Button)
        books.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (books.columnAtPoint(e.getPoint())==0 || books.columnAtPoint(e.getPoint())==1 || books.columnAtPoint(e.getPoint())==2)
                    books.setCursor(Attributes.handcursor);
                else
                    books.setCursor(Cursor.getDefaultCursor());
            }
        });

        //***********//**********//Inner Form\\*********\\*********\\
        // **********************************************************\\
        internalFrame = new JInternalFrame("", false, false, false, true);
        internalFrame.add(bookInterface.getFrame().getContentPane()); //adding books form to internal frame
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
        this.win = new JFrame("Book Registration");
        this.win.setSize(800,800);
        this.win.setLayout(null);
    }
    //******Function for changing column color
    protected void changeColumnColor(TableColumn column){
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
    private int getIndex(String isbn){
        return BookManagement.getInstance().getIndex(isbn);
    }
    private void deleteBook(String isbn,int row){
        Toolkit.getDefaultToolkit().beep();
        if (JOptionPane.showConfirmDialog(null,"Are You Sure to Delete this Book?\n(All the Related Borrowings will also be deleted)","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==0){
            //BorrowManagement.getInstance().bookDeleted(isbn);
            BookManagement.getInstance().deleteBook(isbn);
            try {
                MainMenu.booksList.doClick(); //refresh list
            }catch (Exception ignored){} //ignoreException
            //bookTable.removeRow(row);
            //books.revalidate();
        }
    }
    private void createSearchField()
    {
        MyText search = new MyText("Enter Text",10,10);
        search.addFrame(win);
        search.setBounds(110,10,300,20);
        ImageIcon ic = new ImageIcon(new ImageIcon("resources/Find.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        JButton btn = new JButton(ic);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
public JFrame getFrame(){
        return this.win;
}
}