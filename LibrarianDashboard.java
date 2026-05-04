//Imports standard Java utility and GUI classes for layout and interaction.
//IMPORTS
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

//Defines the central navigation hub for librarians, providing access to all administrative and operational tools.
//CLASS DEFINITION
public class LibrarianDashboard extends JFrame {

    //UI components representing the various actions a librarian can perform.
    //UI COMPONENTS
    private JButton viewCatalogButton;
    private JButton viewShelvesButton;
    private JButton addBookButton;
    private JButton addShelfButton;
    private JButton checkOutButton;
    private JButton checkInButton;
    private JButton finePageButton;
    private JButton logoutButton;

    //Contextual data for the library state and the specific librarian session.
    //INSTANCE VARIABLES
    private Library_Sim library;
    private Librarian currentLibrarian;

    //Sets up the dashboard layout and defines the navigation logic for each button.
    //CONSTRUCTOR
    public LibrarianDashboard(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        //Configures the window's basic appearance and behavior.
        //FRAME SETTINGS
        setTitle("Librarian Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);

        //Utilizes GridBagLayout for flexible, grid-based positioning of UI elements.
        //LAYOUT CONFIGURATION
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Header label spanning across the top of the grid.
        //HEADER SECTION
        JLabel titleLabel = new JLabel("LIBRARIAN PORTAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        //Opens the full library catalog view.
        //VIEW CATALOG ACTION
        gbc.gridx = 0;
        gbc.gridy = 1;
        viewCatalogButton = new JButton("View Catalog");
        panel.add(viewCatalogButton, gbc);

        viewCatalogButton.addActionListener(e -> {
            new BookListPage(library, currentLibrarian);
            dispose();
        });

        //Navigates to the shelf organization view.
        //VIEW SHELVES ACTION
        gbc.gridx = 0;
        gbc.gridy = 2;
        viewShelvesButton = new JButton("View Shelves");
        panel.add(viewShelvesButton, gbc);

        viewShelvesButton.addActionListener(e -> {
            new ViewShelvesPage(library, currentLibrarian);
            dispose();
        });

        //Redirects to the checkout cart for processing loans.
        //CHECK OUT ACTION
        gbc.gridx = 0;
        gbc.gridy = 3;
        checkOutButton = new JButton("Check Out Books");
        panel.add(checkOutButton, gbc);

        checkOutButton.addActionListener(e -> {
            new CheckoutPage(library, currentLibrarian);
            dispose();
        });

        //Redirects to the book return interface.
        //CHECK IN ACTION
        gbc.gridx = 0;
        gbc.gridy = 4;
        checkInButton = new JButton("Check In Books");
        panel.add(checkInButton, gbc);

        checkInButton.addActionListener(e -> {
            new CheckInPage(library, currentLibrarian);
            dispose();
        });

        //Opens the interface for applying fines to patron accounts.
        //FINE MANAGEMENT ACTION
        gbc.gridx = 0;
        gbc.gridy = 5;
        finePageButton = new JButton("Fine Management");
        panel.add(finePageButton, gbc);

        finePageButton.addActionListener(e -> {
            new FineManagementPage(library, currentLibrarian);
            dispose();
        });

        //Terminates the current session and returns to the login screen.
        //LOGOUT ACTION
        gbc.gridx = 0;
        gbc.gridy = 6;
        logoutButton = new JButton("Logout");
        panel.add(logoutButton, gbc);

        logoutButton.addActionListener(e -> {
            dispose();
            new LibrarianLogin(library);
        });

        //Navigates to the form for registering new books.
        //ADD BOOK ACTION
        gbc.gridx = 1;
        gbc.gridy = 1;
        addBookButton = new JButton("Add Book");
        panel.add(addBookButton, gbc);

        addBookButton.addActionListener(e -> {
            new AddBookPage(library, currentLibrarian);
            dispose();
        });

        //Navigates to the form for creating new shelves.
        //ADD SHELF ACTION
        gbc.gridx = 1;
        gbc.gridy = 2;
        addShelfButton = new JButton("Add Shelf");
        panel.add(addShelfButton, gbc);

        addShelfButton.addActionListener(e -> {
            new AddShelfPage(library, currentLibrarian);
            dispose();
        });

        //Finalizes panel assembly and centers content vertically within the frame.
        //FINALIZATION
        gbc.gridx = 0;
        gbc.gridy = 99;
        gbc.weighty = 1;
        panel.add(Box.createVerticalGlue(), gbc);
        add(panel);
        setVisible(true);
    }
}