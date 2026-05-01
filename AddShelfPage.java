import javax.swing.*;
import java.awt.*;

public class AddShelfPage extends JFrame {

    private Library_Sim library;
    private Librarian currentLibrarian;

    public AddShelfPage(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        setTitle("Add New Shelf");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("ADD NEW SHELF", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField rowsField = new JTextField();
        JTextField colsField = new JTextField();

        formPanel.add(new JLabel("Shelf Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Genre:"));
        formPanel.add(genreField);

        formPanel.add(new JLabel("Max Rows:"));
        formPanel.add(rowsField);

        formPanel.add(new JLabel("Max Columns:"));
        formPanel.add(colsField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Shelf");
        JButton backButton = new JButton("Back");

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String genre = genreField.getText().trim();
            String rowsText = rowsField.getText().trim();
            String colsText = colsField.getText().trim();

            if (name.isEmpty() || genre.isEmpty() || rowsText.isEmpty() || colsText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            try {
                int rows = Integer.parseInt(rowsText);
                int cols = Integer.parseInt(colsText);

                if (rows <= 0 || cols <= 0) {
                    JOptionPane.showMessageDialog(this, "Rows and columns must be positive numbers.");
                    return;
                }

                Shelf newShelf = new Shelf(name, genre, rows, cols);
                library.addShelf(newShelf);

                JOptionPane.showMessageDialog(this, "Shelf added successfully!");

                new LibrarianDashboard(library, currentLibrarian);
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Rows and columns must be valid integers.");
            }
        });

        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, currentLibrarian);
            dispose();
        });

        bottomPanel.add(addButton);
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
