/**
 * this controls what grouping of books you will see.
 *
 * @author Hung Nguyen
 */

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookListPage extends JFrame {

    private Library_Sim library;
    private User currentUser;
    private Shelf shelf; // null means "show full catalog"

    // this is the view used for the full catalog
    public BookListPage(Library_Sim library, User currentUser) {
        this(library, currentUser, null); // call the main constructor
    }

    // if you are looking through specific shelves, this is what you will see.
    public BookListPage(Library_Sim library, User currentUser, Shelf shelf) {
        this.library = library;
        this.currentUser = currentUser;
        this.shelf = shelf;

        setTitle(shelf == null ? "Library Catalog" : shelf.getName());
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(
                shelf == null ? "FULL CATALOG" : shelf.getName(),
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        // Decide which books to show
        List<Book> booksToShow = (shelf == null)
                ? library.getCatalog()
                : shelf.getBooks();

        for (Book b : booksToShow) {

            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel info = new JLabel("<html><b>" + b.getTitle() + "</b><br>"
                    + b.getAuthor() + "<br>"
                    + "Genre: " + b.getGenre() + "<br>"
                    + "Available: " + b.getIsAvailable() + "</html>");

            // Only show "Add to Cart" if this is a patron/librarian browsing the catalog
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

        JScrollPane scrollPane = new JScrollPane(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            if (shelf == null) {
                new LibrarianDashboard(library, (Librarian) currentUser);
            } else {
                new ViewShelvesPage(library, (Librarian) currentUser);
            }
            dispose();
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
