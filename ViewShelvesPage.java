//Imports Swing components for the GUI and AWT for layout management and fonts.
//IMPORTS
import javax.swing.*;
import java.awt.*;

//Defines a window that displays all physical shelves in the library, showing their stats and allowing users to browse their contents.
//CLASS DEFINITION
public class ViewShelvesPage extends JFrame {

    //State variables for the library data and the librarian session.
    //INSTANCE VARIABLES
    private Library_Sim library;
    private Librarian currentLibrarian;

    //Initializes the frame and builds the shelf list interface.
    //CONSTRUCTOR
    public ViewShelvesPage(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        //Standard window configuration for size and placement.
        //FRAME SETTINGS
        setTitle("Library Shelves");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Root panel using BorderLayout for structure.
        //MAIN LAYOUT
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Section header displayed at the top of the window.
        //HEADER SECTION
        JLabel titleLabel = new JLabel("LIBRARY SHELVES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        //Vertical container to hold the list of shelves.
        //SHELF LIST PANEL
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        //Iterates through the library's shelves to create a visual entry for each.
        //LIST POPULATION
        for (Shelf shelf : library.getShelves()) {

            //Row container for a specific shelf's data and actions.
            //ROW COMPONENT
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Displays shelf name, genre, and current occupancy using HTML.
            //SHELF INFO
            JLabel info = new JLabel("<html><b>" + shelf.getName() + "</b><br>"
                    + "Genre: " + shelf.getGenre() + "<br>"
                    + "Capacity: " + shelf.getMaxColumns() * shelf.getMaxRows() + "<br>"
                    + "Books: " + shelf.getBooks().size() + "</html>");

            //Triggers the BookListPage to show only the books on this specific shelf.
            //VIEW ACTION
            JButton viewButton = new JButton("View Books");
            viewButton.addActionListener(e -> {
                new BookListPage(library, currentLibrarian, shelf);
                dispose();
            });

            row.add(info);
            row.add(viewButton);
            listPanel.add(row);
        }

        //Wraps the list in a scroll pane to handle many shelves.
        //SCROLLABLE VIEW
        JScrollPane scrollPane = new JScrollPane(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        //Navigation button to return to the dashboard.
        //BACK BUTTON LOGIC
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, currentLibrarian);
            dispose();
        });

        //Final assembly of the footer and adding the main panel to the JFrame.
        //FINALIZATION
        JPanel bottom = new JPanel(new FlowLayout());
        bottom.add(backButton);

        mainPanel.add(bottom, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}