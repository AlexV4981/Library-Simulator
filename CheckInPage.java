//Imports Swing components for the UI and AWT for layout management and fonts.
//IMPORTS
import javax.swing.*;
import java.awt.*;

//Defines a window that displays a user's currently borrowed books and allows them to return them.
//CLASS DEFINITION
public class CheckInPage extends JFrame {

    //References to the library system and the user performing the return.
    //INSTANCE VARIABLES
    private Library_Sim library;
    private User currentUser;

    //Constructs the return interface and populates the list of borrowed books.
    //CONSTRUCTOR
    public CheckInPage(Library_Sim library, User currentUser) {
        this.library = library;
        this.currentUser = currentUser;

        //Configures window title, size, and standard close behavior.
        //FRAME SETTINGS
        setTitle("Return Books");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Main container utilizing BorderLayout for top-level organization.
        //MAIN LAYOUT
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Section header displayed at the top of the window.
        //HEADER SECTION
        JLabel titleLabel = new JLabel("BOOKS YOU HAVE CHECKED OUT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        //Vertical container to hold individual book entries.
        //LIST PANEL
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        //Iterates through the user's borrowed books to create a return entry for each.
        //LIST GENERATION
        for (Book b : currentUser.getBooks()) {

            //A horizontal row containing book details and a "Return" button.
            //ROW COMPONENT
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Displays book metadata using HTML formatting.
            //BOOK INFO
            JLabel info = new JLabel("<html><b>" + b.getTitle() + "</b><br>"
                    + b.getAuthor() + "<br>"
                    + "Genre: " + b.getGenre() + "</html>");

            //Triggers the check-in logic and refreshes the page to update the list.
            //RETURN ACTION
            JButton returnButton = new JButton("Return");
            returnButton.addActionListener(e -> {
                currentUser.checkIn(b, b.getTitle());
                JOptionPane.showMessageDialog(this, b.getTitle() + " returned to library.");

                dispose();
                new CheckInPage(library, currentUser); // refresh the page
            });

            row.add(info);
            row.add(returnButton);
            listPanel.add(row);
        }

        //Adds scroll functionality in case the user has many borrowed books.
        //SCROLLABLE CONTENT
        JScrollPane scrollPane = new JScrollPane(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        //Contains navigation buttons to exit the page.
        //BOTTOM PANEL
        JPanel bottomPanel = new JPanel(new FlowLayout());

        //Redirects the user back to the Librarian Dashboard.
        //BACK BUTTON LOGIC
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, (Librarian) currentUser);
            dispose();
        });

        //Final assembly of the interface components.
        //FINALIZATION
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}