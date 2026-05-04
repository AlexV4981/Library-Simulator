/**
 * This allows the librarian to add books to the library
 *
 * @author Hung Nguyen
 */

import javax.swing.*;
import java.awt.*;


public class AddBookPage extends JFrame {

    private Library_Sim library;
    private Librarian currentLibrarian;

    public AddBookPage(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        setTitle("Add New Book");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("ADD NEW BOOK", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();

        // Shelf dropdown
        JComboBox<Shelf> shelfDropdown = new JComboBox<>();
        for (Shelf s : library.getShelves()) {
            shelfDropdown.addItem(s);
        }

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);

        formPanel.add(new JLabel("Author:"));
        formPanel.add(authorField);

        formPanel.add(new JLabel("Genre:"));
        formPanel.add(genreField);

        formPanel.add(new JLabel("Year:"));
        formPanel.add(yearField);

        formPanel.add(new JLabel("Shelf:"));
        formPanel.add(shelfDropdown);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Book");
        JButton backButton = new JButton("Back");

        addButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String genre = genreField.getText().trim();
            String yearText = yearField.getText().trim();
            Shelf selectedShelf = (Shelf) shelfDropdown.getSelectedItem();

            if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || yearText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            try {
                int year = Integer.parseInt(yearText);

                Book newBook = new Book(title, author, year, genre);

                // Add to catalog
                library.addBook(newBook);

                // Add to shelf
                if (selectedShelf != null) {
                    if (!selectedShelf.isFull()) {
                        selectedShelf.addBook(newBook);
                    } else {
                        JOptionPane.showMessageDialog(this, "Selected shelf is full!");
                        return;
                    }
                }

                JOptionPane.showMessageDialog(this, "Book added successfully!");

                new LibrarianDashboard(library, currentLibrarian);
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Year must be a valid number.");
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
