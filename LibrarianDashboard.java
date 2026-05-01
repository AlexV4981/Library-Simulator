import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class LibrarianDashboard extends JFrame {

    private JButton viewCatalogButton;
    private JButton viewShelvesButton;
    private JButton addBookButton;
    private JButton addShelfButton;
    private JButton checkOutButton;
    private JButton checkInButton;
    private JButton finePageButton;
    private JButton logoutButton;
    private Library_Sim library;
    private Librarian currentLibrarian;

    public LibrarianDashboard(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        // TITLE
        setTitle("Librarian Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("LIBRARIAN PORTAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // View Catalog
        gbc.gridx = 0;
        gbc.gridy = 1;
        viewCatalogButton = new JButton("View Catalog");
        panel.add(viewCatalogButton, gbc);

        viewCatalogButton.addActionListener(e -> {
            new BookListPage(library, currentLibrarian);
            dispose();
        });

        // View Shelves
        gbc.gridx = 0;
        gbc.gridy = 2;
        viewShelvesButton = new JButton("View Shelves");
        panel.add(viewShelvesButton, gbc);

        viewShelvesButton.addActionListener(e -> {
            new ViewShelvesPage(library, currentLibrarian); // or whatever screen you want
            dispose();
        });

        // Check Out Books
        gbc.gridx = 0;
        gbc.gridy = 3;
        checkOutButton = new JButton("Check Out Books");
        panel.add(checkOutButton, gbc);

        checkOutButton.addActionListener(e -> {
            new CheckoutPage(library, currentLibrarian);
            dispose();
        });

        // Check In Books
        gbc.gridx = 0;
        gbc.gridy = 4;
        checkInButton = new JButton("Check In Books");
        panel.add(checkInButton, gbc);

        checkInButton.addActionListener(e -> {
            new CheckInPage(library, currentLibrarian);
            dispose();
        });

        // Fine Management Page
        gbc.gridx = 0;
        gbc.gridy = 5;
        finePageButton = new JButton("Fine Management");
        panel.add(finePageButton, gbc);

        finePageButton.addActionListener(e -> {
            new FineManagementPage(library, currentLibrarian);
            dispose();
        });

        // Logout Page
        gbc.gridx = 0;
        gbc.gridy = 6;
        logoutButton = new JButton("Logout");
        panel.add(logoutButton, gbc);

        logoutButton.addActionListener(e -> {
            dispose(); // close dashboard
            new LibrarianLogin(library); // reopen login screen
        });

        // Add Book
        gbc.gridx = 1;
        gbc.gridy = 1;
        addBookButton = new JButton("Add Book");
        panel.add(addBookButton, gbc);

        addBookButton.addActionListener(e -> {
            new AddBookPage(library, currentLibrarian); // or whatever screen you want
            dispose();
        });

        // Add Shelf
        gbc.gridx = 1;
        gbc.gridy = 2;
        addShelfButton = new JButton("Add Shelf");
        panel.add(addShelfButton, gbc);

        addShelfButton.addActionListener(e -> {
            new AddShelfPage(library, currentLibrarian); // or whatever screen you want
            dispose();
        });

        // Spacer to center everything vertically
        gbc.gridx = 0;
        gbc.gridy = 99;
        gbc.weighty = 1;
        panel.add(Box.createVerticalGlue(), gbc);
        add(panel);
        setVisible(true);
    }
}
