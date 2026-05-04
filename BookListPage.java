//Imports GUI components, layout managers, and utility collections for displaying the list.
//IMPORTS
import javax.swing.*;
import java.awt.*;
import java.util.List;

//Displays a scrollable list of books, filtered either by a specific shelf or showing the entire library catalog.
//CLASS DEFINITION
public class BookListPage extends JFrame {

    //State variables to track the library data, the active user, and the current filtering context.
    //INSTANCE VARIABLES
    private Library_Sim library;
    private User currentUser;
    private Shelf shelf; // null means "show full catalog"

    //Convenience constructor for viewing the entire library catalog.
    //DEFAULT CONSTRUCTOR
    public BookListPage(Library_Sim library, User currentUser) {
        this(library, currentUser, null); // call the main constructor
    }

    //Main constructor that builds the UI based on whether a specific shelf is being viewed.
    //PRIMARY CONSTRUCTOR
    public BookListPage(Library_Sim library, User currentUser, Shelf shelf) {
        this.library = library;
        this.currentUser = currentUser;
        this.shelf = shelf;

        //Sets window title and basic closing operations.
        //FRAME SETTINGS
        setTitle(shelf == null ? "Library Catalog" : shelf.getName());
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Root container for the page structure.
        //MAIN LAYOUT
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Dynamic header label that adapts based on the shelf context.
        //HEADER SECTION
        JLabel titleLabel = new JLabel(
                shelf == null ? "FULL CATALOG" : shelf.getName(),
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        //Vertical box layout to stack book entries on top of each other.
        //LIST CONTAINER
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        //Determines the source of the book data based on the presence of a shelf object.
        //DATA RETRIEVAL
        List<Book> booksToShow = (shelf == null)
                ? library.getCatalog()
                : shelf.getBooks();

        //Iterates through the book list to create individual visual rows.
        //LIST POPULATION
        for (Book b : booksToShow) {

            //Row container for a single book's information and actions.
            //ROW DESIGN
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Formatted HTML label to show detailed book metadata over multiple lines.
            //BOOK INFO LABEL
            JLabel info = new JLabel("<html><b>" + b.getTitle() + "</b><br>"
                    + b.getAuthor() + "<br>"
                    + "Genre: " + b.getGenre() + "<br>"
                    + "Available: " + b.getIsAvailable() + "</html>");

            //Adds a button to the row only if the user has permission to borrow books.
            //ACTION BUTTONS
            if (currentUser instanceof Patron || currentUser instanceof Librarian) {
                JButton addButton = new JButton("Add to Cart");
                addButton.addActionListener(e -> {
                    currentUser.addToCart(b);
                    JOptionPane.showMessageDialog(this, b.getTitle() + " added to cart.");
                });
                row.add(addButton);
            }

            row.add(info);
            listPanel.add(row);
        }

        //Wraps the book list in a scrollable view for handling large catalogs.
        //SCROLLABLE VIEW
        JScrollPane scrollPane = new JScrollPane(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        //Handles logic for returning the user to the correct previous screen.
        //NAVIGATION LOGIC
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            if (shelf == null) {
                new LibrarianDashboard(library, (Librarian) currentUser);
            } else {
                new ViewShelvesPage(library, (Librarian) currentUser);
            }
            dispose();
        });

        //Final assembly of the footer and adding the main panel to the frame.
        //FINALIZATION
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}