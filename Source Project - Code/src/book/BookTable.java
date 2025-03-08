package book;
import javax.swing.table.DefaultTableModel;

public class BookTable {

    public static DefaultTableModel bookTable() {
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
        tableModel.addColumn("");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("Title");
        tableModel.addColumn("Edition");
        tableModel.addColumn("Author");
        tableModel.addColumn("Genre");
        tableModel.addColumn("Status");
        for (Book book : BookManagement.getInstance().getBooks()){
            tableModel.addRow(new Object[]{
                    "Edit","Borrow","Delete",book.getIsbn(),book.getTitle(),book.getEdition(),book.getAuthor(),book.getGenre(),book.getStatus()
            });

    }
        return tableModel;

    }
}
