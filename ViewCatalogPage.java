import javax.swing.*;
import java.awt.*;

public class ViewCatalogPage extends JFrame {
    private Library_Sim library;
    private User currentUser;

    public ViewCatalogPage(Library_Sim library, User currentUser) {
        this.library = library;
        this.currentUser = currentUser;

        setTitle("View Catalog");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("ALL BOOKS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Book list area
        // Panel that holds all book rows
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (Book b : library.getCatalog()) {

            JPanel bookRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            bookRow.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Compact book info using HTML
            JLabel info = new JLabel("<html><b>" + b.getTitle() + "</b><br>"
                    + b.getAuthor() + "<br>"
                    + "Genre: " + b.getGenre() + "<br>"
                    + "Available: " + b.getIsAvailable() + "</html>");

            JButton addButton = new JButton("Add to Cart");
            addButton.addActionListener(e -> {
                currentUser.addToCart(b);
                JOptionPane.showMessageDialog(this, b.getTitle() + " added to cart.");
            });

            bookRow.add(info);
            bookRow.add(addButton);

            listPanel.add(bookRow);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, (Librarian) currentUser);
            dispose();
        });

        mainPanel.add(backButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
