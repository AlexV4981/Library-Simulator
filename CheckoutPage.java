/**
 * this showcases all the books that you have added to your cart. You may select
 * which ones to actually check out
 *
 * @author Hung Nguyen
 */
import javax.swing.*;
import java.awt.*;

public class CheckoutPage extends JFrame {

    private Library_Sim library;
    private User currentUser;

    public CheckoutPage(Library_Sim library, User currentUser) {
        this.library = library;
        this.currentUser = currentUser;

        setTitle("Checkout Cart");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("CHECKOUT CART", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Cart list panel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (Book b : currentUser.getCart()) {

            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel info = new JLabel("<html><b>" + b.getTitle() + "</b><br>"
                    + b.getAuthor() + "<br>"
                    + "Genre: " + b.getGenre() + "</html>");

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                currentUser.getCart().remove(b);
                JOptionPane.showMessageDialog(this, b.getTitle() + " removed from cart.");
                dispose();
                new CheckoutPage(library, currentUser); // refresh page
            });

            row.add(info);
            row.add(removeButton);
            listPanel.add(row);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());

        JButton confirmButton = new JButton("Confirm Checkout");
        confirmButton.addActionListener(e -> {
            for (Book b : currentUser.getCart()) {
                currentUser.checkOut(b);
            }
            currentUser.clearCart();
            JOptionPane.showMessageDialog(this, "Checkout complete!");
            dispose();
            new LibrarianDashboard(library, (Librarian) currentUser);
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, (Librarian) currentUser);
            dispose();
        });

        bottomPanel.add(confirmButton);
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
