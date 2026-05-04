//Imports necessary Java Swing and AWT classes for GUI construction.
//IMPORTS
import javax.swing.*;
import java.awt.*;

//Defines a window for librarians to create and configure new physical storage units (shelves) for the library.
//CLASS DEFINITION
public class AddShelfPage extends JFrame {

    //References to the main library simulation data and the librarian currently using the system.
    //INSTANCE VARIABLES
    private Library_Sim library;
    private Librarian currentLibrarian;

    //Initializes the frame and sets up the layout components.
    //CONSTRUCTOR
    public AddShelfPage(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        //Configures the window dimensions, title, and centering behavior.
        //FRAME SETTINGS
        setTitle("Add New Shelf");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //The primary container using BorderLayout for structured placement of header, form, and buttons.
        //MAIN LAYOUT
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Large header text at the top of the interface.
        //HEADER SECTION
        JLabel titleLabel = new JLabel("ADD NEW SHELF", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        //Grid-based panel containing labels and input fields for shelf properties.
        //FORM PANEL
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //Input components for shelf identification, category, and physical dimensions.
        //INPUT FIELDS
        JTextField nameField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField rowsField = new JTextField();
        JTextField colsField = new JTextField();

        //Adding descriptive labels and their corresponding text boxes to the grid.
        //FORM ASSEMBLY
        formPanel.add(new JLabel("Shelf Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Genre:"));
        formPanel.add(genreField);

        formPanel.add(new JLabel("Max Rows:"));
        formPanel.add(rowsField);

        formPanel.add(new JLabel("Max Columns:"));
        formPanel.add(colsField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        //Container for navigation and submission buttons.
        //BOTTOM BUTTON PANEL
        JPanel bottomPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Shelf");
        JButton backButton = new JButton("Back");

        //Captures input, validates data types/ranges, and saves the new shelf to the system.
        //ADD BUTTON LOGIC
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String genre = genreField.getText().trim();
            String rowsText = rowsField.getText().trim();
            String colsText = colsField.getText().trim();

            //Ensure no input fields are left blank.
            if (name.isEmpty() || genre.isEmpty() || rowsText.isEmpty() || colsText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            try {
                int rows = Integer.parseInt(rowsText);
                int cols = Integer.parseInt(colsText);

                //Logical check to ensure shelf dimensions are physically possible.
                if (rows <= 0 || cols <= 0) {
                    JOptionPane.showMessageDialog(this, "Rows and columns must be positive numbers.");
                    return;
                }

                //Instantiation and registration of the new shelf object.
                Shelf newShelf = new Shelf(name, genre, rows, cols);
                library.addShelf(newShelf);

                JOptionPane.showMessageDialog(this, "Shelf added successfully!");

                //Redirect back to the dashboard upon success.
                new LibrarianDashboard(library, currentLibrarian);
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Rows and columns must be valid integers.");
            }
        });

        //Closes the current window and returns the user to the Librarian Dashboard.
        //BACK BUTTON LOGIC
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, currentLibrarian);
            dispose();
        });

        //Final assembly of the bottom panel and adding the main panel to the JFrame.
        //FINALIZATION
        bottomPanel.add(addButton);
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}