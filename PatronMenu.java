//Imports necessary Swing components and event handling classes for the patron menu.
//IMPORTS
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Defines the main interaction screen for library patrons to search, borrow, and return books.
//CLASS DEFINITION
public class PatronMenu extends JFrame {

    //UI components for list display, search input, and borrowing actions.
    //UI COMPONENTS
    private JList list1;
    private JButton checkInButton;
    private JButton checkOutButton;
    private JButton payFinesButton;
    private JTextField titleTextField;
    private JButton searchButton;
    private JPanel contentPane;
    private JButton backButton;

    //Contextual data for the logged-in patron and the current library state.
    //INSTANCE VARIABLES
    private Patron patron;
    private Library_Sim library;
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    //Initializes the frame and configures action listeners for book management.
    //CONSTRUCTOR
    public PatronMenu(Patron patron, Library_Sim library){

        this.patron = patron;
        this.library = library;
        this.setContentPane(contentPane);
        this.setSize(600, 500);
        this.setTitle("Patron Menu-"+whoami());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        list1.setModel(listModel);
        populateJlist();

        //Filters the displayed book titles based on user input for a case-insensitive match.
        //SEARCH ACTION
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = titleTextField.getText().toLowerCase().trim();
                listModel.clear();

                for (Shelf shelf : library.getShelves()) {
                    for (Book book : shelf.getBooks()) {
                        String title = book.getTitle().toLowerCase();
                        if (title.contains(query)) {
                            listModel.addElement(book.getTitle());
                        }
                    }
                }

                if (query.isEmpty()) {
                    populateJlist();
                }
            }
        });

        //Handles returning a book currently in the patron's possession to the library.
        //CHECK IN ACTION
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTitle = (String) list1.getSelectedValue();

                if (selectedTitle == null) {
                    JOptionPane.showMessageDialog(contentPane, "Please select a book from your list first.");
                    return;
                }

                Book bookToReturn = null;
                for (Book b : patron.getBooks()) {
                    if (b.getTitle().equals(selectedTitle)) {
                        bookToReturn = b;
                        break;
                    }
                }

                if (bookToReturn != null) {
                    bookToReturn.checkIn();
                    patron.checkIn(bookToReturn, selectedTitle);
                    JOptionPane.showMessageDialog(contentPane, "Checked in: " + selectedTitle);
                    populateJlist();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "You don't appear to have this book checked out.");
                }
            }
        });

        //Attempts to check out a selected book if it's available and the patron hasn't hit their limit.
        //CHECK OUT ACTION
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTitle = (String) list1.getSelectedValue();
                selectionValid(selectedTitle);

                for (Shelf shelf : library.getShelves()) {
                    Book book = shelf.findBook(selectedTitle);

                    if (book != null) {
                        if (book.getIsAvailable()) {
                            if(patron.getBooks().size() < patron.getMaxCheckOutCount()){
                                JOptionPane.showMessageDialog(contentPane, "Successfully checked out: " + selectedTitle);
                                patron.checkOut(book);
                            } else {
                                JOptionPane.showMessageDialog(contentPane, "Check in some books first");
                            }
                            populateJlist();
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "This book is already checked out.");
                        }
                        return;
                    }
                }
            }
        });

        //Navigates to the payment screen to settle outstanding account fines.
        //FINANCIAL ACTION
        payFinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatronPayment payment = new PatronPayment(patron,library);
                payment.setVisible(true);
                dispose();
            }
        });

        //Returns the user to the initial login screen.
        //NAVIGATION ACTION
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatronLogin patronLogin = new PatronLogin(library);
                patronLogin.setVisible(true);
                dispose();
            }
        });
    }

    //Helper method to retrieve the current patron's name for UI labeling.
    //USER IDENTITY
    public String whoami(){
        return patron.getName();
    }

    //Iterates through all shelves to display every book title available in the system.
    //LIST REFRESH
    void populateJlist(){
        listModel.clear();
        for(Shelf shelf : library.getShelves()){
            for(Book book : shelf.getBooks()){
                listModel.addElement(book.getTitle());
            }
        }
    }

    //Utility to ensure a selection was made before attempting an operation.
    //VALIDATION HELPER
    void selectionValid(String Selected){
        if (Selected == null){
            JOptionPane.showMessageDialog(contentPane, "Please Select a book first");
        }
    }
}