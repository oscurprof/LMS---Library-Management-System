package patron;
import javax.swing.table.DefaultTableModel;

public class PatronTable {
    public static DefaultTableModel patronTable() {
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Disallow editing for cells
                return false;
            }
        };
        tableModel.addColumn("");
        tableModel.addColumn("");
        tableModel.addColumn("");
        tableModel.addColumn("MembershipID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Type");
        tableModel.addColumn("Fine");
        tableModel.addColumn("Status");
        for (Patron patron : PatronManagement.getInstance().getPatrons()){
            tableModel.addRow(new Object[]{
                    "Edit","Borrow","Delete",patron.getMembershipID(),patron.getName(),patron.getEmail(),patron.getMembershipType(),patron.getFineBalance(),patron.getExpiryStatus()
            });

        }
        return tableModel;

    }
}
