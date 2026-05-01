import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class LibrarianDashboard extends JFrame {

    private JButton viewCatalogButton;
    private JButton viewShelvesButton;
    private JButton checkOutButton;
    private JButton checkInButton;
    private JButton finePageButton;
    private JButton logoutButton;
    private Library_Sim library;
    private Librarian currentLibrarian;


    public LibrarianDashboard(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

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
        gbc.gridy = 1;
        viewCatalogButton = new JButton("View Catalog");
        panel.add(viewCatalogButton, gbc);

        viewCatalogButton.addActionListener(e -> {
            new ViewCatalogPage(library, currentLibrarian);
            dispose();
        });

        // View Shelves
        gbc.gridy = 2;
        viewShelvesButton = new JButton("View Shelves");
        panel.add(viewShelvesButton, gbc);

        /*
        viewShelvesButton.addActionListener(e -> {
            new ViewShelvesPage(library); // or whatever screen you want
            dispose();
        });
        */

        // Check Out Books
        gbc.gridy = 3;
        checkOutButton = new JButton("Check Out Books");
        panel.add(checkOutButton, gbc);

        checkOutButton.addActionListener(e -> {
            new CheckoutPage(library, currentLibrarian);
            dispose();
        });

        // Check In Books
        gbc.gridy = 4;
        checkInButton = new JButton("Check In Books");
        panel.add(checkInButton, gbc);

        // Fine Management Page
        gbc.gridy = 5;
        finePageButton = new JButton("Fine Management");
        panel.add(finePageButton, gbc);

        // Logout Page
        gbc.gridy = 6;
        gbc.gridx = 0;
        logoutButton = new JButton("Logout");
        panel.add(logoutButton, gbc);

        logoutButton.addActionListener(e -> {
            dispose(); // close dashboard
            new LibrarianLogin(library); // reopen login screen
        });

        add(panel);
        setVisible(true);
    }
}
