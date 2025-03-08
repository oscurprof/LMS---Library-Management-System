package borrowings;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowingTable {
    public static DefaultTableModel borrowingTable() {
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Disallow editing for cells
                return false;
            }
        };
        //BookManagement.getInstance().loadBooks();
        tableModel.addColumn("");
        tableModel.addColumn("");
        tableModel.addColumn("BorrowID");
        tableModel.addColumn("PatronID");
        tableModel.addColumn("Name");
        tableModel.addColumn("BookISBN");
        tableModel.addColumn("Title");
        //tableModel.addColumn("Status");
        for (Borrowings borrowing : BorrowManagement.getInstance().getBorrows()){
            if (!borrowing.isReturned) //The Borrowings that are not returned
                tableModel.addRow(new Object[]{
                    "Edit","Return",borrowing.getBorrowID(),borrowing.getsPatron().getMembershipID(),borrowing.getsPatron().getName(),borrowing.getsBook().getIsbn(),borrowing.getsBook().getTitle()
                });

        }
        return tableModel;
    }
    public static DefaultTableModel historyTable(){
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Disallow editing for cells
                return false;
            }
        };
        //BookManagement.getInstance().loadBooks();
        tableModel.addColumn("");
        tableModel.addColumn("BorrowID");
        tableModel.addColumn("PatronID");
        tableModel.addColumn("Name");
        tableModel.addColumn("BookISBN");
        tableModel.addColumn("Title");
        tableModel.addColumn("Return Date");
        //tableModel.addColumn("Status");
        for (Borrowings borrowing : BorrowManagement.getInstance().getBorrows()){
            if (borrowing.isReturned) //The Borrowings that are not returned
                tableModel.addRow(new Object[]{
                        "Select",borrowing.getBorrowID(),borrowing.getsPatron().getMembershipID(),borrowing.getsPatron().getName(),borrowing.getsBook().getIsbn(),borrowing.getsBook().getTitle(),borrowing.getReturnedOn()
                });

        }
        return tableModel;
    }
}
