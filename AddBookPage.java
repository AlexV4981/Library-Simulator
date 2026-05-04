//Imports necessary Java Swing and AWT classes for the GUI.
//IMPORTS
import javax.swing.*;
import java.awt.*;

//Defines a window for librarians to input and save new book details to the system.
//CLASS DEFINITION
public class AddBookPage extends JFrame {

    //References to the core system data and the user currently logged in.
    //INSTANCE VARIABLES
    private Library_Sim library;
    private Librarian currentLibrarian;

    //Initializes the frame and builds the user interface.
    //CONSTRUCTOR
    public AddBookPage(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        //Configures basic window settings like size and position.
        //FRAME SETTINGS
        setTitle("Add New Book");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //The primary container using BorderLayout to organize the header, form, and buttons.
        //MAIN LAYOUT
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Large header text at the top of the window.
        //HEADER SECTION
        JLabel titleLabel = new JLabel("ADD NEW BOOK", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        //Grid layout containing labels and input fields for book metadata.
        //FORM PANEL
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //Input components for title, author, genre, and publication year.
        //INPUT FIELDS
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();

        //Populates a dropdown menu with available physical shelves from the library.
        //SHELF SELECTION
        JComboBox<Shelf> shelfDropdown = new JComboBox<>();
        for (Shelf s : library.getShelves()) {
            shelfDropdown.addItem(s);
        }

        //Mapping labels to their respective input components in the grid.
        //FORM ASSEMBLY
        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Author:"));
        formPanel.add(authorField);
        formPanel.add(new JLabel("Genre:"));
        formPanel.add(genreField);
        formPanel.add(new JLabel("Year:"));
        formPanel.add(yearField);
        formPanel.add(new JLabel("Shelf:"));
        formPanel.add(shelfDropdown);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        //Contains the action buttons at the bottom of the page.
        //BOTTOM BUTTON PANEL
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Book");
        JButton backButton = new JButton("Back");

        //Validates input, creates a Book object, and attempts to store it in the library and shelf.
        //ADD BUTTON LOGIC
        addButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String genre = genreField.getText().trim();
            String yearText = yearField.getText().trim();
            Shelf selectedShelf = (Shelf) shelfDropdown.getSelectedItem();

            //Check for empty strings before processing.
            if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || yearText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            try {
                int year = Integer.parseInt(yearText);
                Book newBook = new Book(title, author, year, genre);

                //Update the global catalog.
                library.addBook(newBook);

                //Verify capacity before adding the book to a specific shelf.
                if (selectedShelf != null) {
                    if (!selectedShelf.isFull()) {
                        selectedShelf.addBook(newBook);
                    } else {
                        JOptionPane.showMessageDialog(this, "Selected shelf is full!");
                        return;
                    }
                }

                JOptionPane.showMessageDialog(this, "Book added successfully!");
                new LibrarianDashboard(library, currentLibrarian);
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Year must be a valid number.");
            }
        });

        //Returns the librarian to the main dashboard without saving changes.
        //BACK BUTTON LOGIC
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, currentLibrarian);
            dispose();
        });

        //Final assembly of the interface.
        //FINALIZATION
        bottomPanel.add(addButton);
        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }
}