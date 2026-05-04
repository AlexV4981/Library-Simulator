//Imports the Swing components for the user interface and AWT for layout and styling.
//IMPORTS
import javax.swing.*;
import java.awt.*;

//Defines a window for users to review books in their cart and finalize the borrowing process.
//CLASS DEFINITION
public class CheckoutPage extends JFrame {

    //Stores the library system state and the user currently managing their cart.
    //INSTANCE VARIABLES
    private Library_Sim library;
    private User currentUser;

    //Initializes the frame and populates the checkout list based on the user's cart contents.
    //CONSTRUCTOR
    public CheckoutPage(Library_Sim library, User currentUser) {
        this.library = library;
        this.currentUser = currentUser;

        //Sets the basic window properties like title, dimensions, and screen placement.
        //FRAME SETTINGS
        setTitle("Checkout Cart");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //The root panel using BorderLayout to separate the header, the list, and the actions.
        //MAIN LAYOUT
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Section title displayed at the top of the window.
        //HEADER SECTION
        JLabel titleLabel = new JLabel("CHECKOUT CART", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        //Vertical container configured to stack book rows on top of each other.
        //CART LIST PANEL
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        //Generates a UI row for every book currently held in the user's temporary cart.
        //CART POPULATION
        for (Book b : currentUser.getCart()) {

            //Individual row using FlowLayout to align book details and a removal button.
            //ROW COMPONENT
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Formatted text showing book info; uses HTML for bold titles and line breaks.
            //BOOK INFO
            JLabel info = new JLabel("<html><b>" + b.getTitle() + "</b><br>"
                    + b.getAuthor() + "<br>"
                    + "Genre: " + b.getGenre() + "</html>");

            //Enables users to delete a book from their cart and refreshes the UI.
            //REMOVE BUTTON LOGIC
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

        //Adds scrollable support for cases where the user has a large number of books in the cart.
        //SCROLLABLE VIEW
        JScrollPane scrollPane = new JScrollPane(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        //Bottom area containing the primary action buttons for confirmation and navigation.
        //BOTTOM BUTTON PANEL
        JPanel bottomPanel = new JPanel(new FlowLayout());

        //Processes all books in the cart as official checkouts and clears the cart data.
        //CONFIRM BUTTON LOGIC
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

        //Exits the checkout screen and returns the user to the Librarian Dashboard.
        //BACK BUTTON LOGIC
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, (Librarian) currentUser);
            dispose();
        });

        //Final assembly of buttons into the footer and adding the main panel to the JFrame.
        //FINALIZATION
        bottomPanel.add(confirmButton);
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}