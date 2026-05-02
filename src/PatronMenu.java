import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatronMenu extends JFrame {


    private JList list1;
    private JButton checkInButton;
    private JButton checkOutButton;
    private JButton payFinesButton;
    private JTextField titleTextField;
    private JButton searchButton;
    private JPanel contentPane;

    private Patron patron;
    private Library_Sim library;
    private DefaultListModel<String> listModel = new DefaultListModel<>();

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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //user input and convert to lowercase for case-insensitive matching
                String query = titleTextField.getText().toLowerCase().trim();

                //Clear the current list view
                listModel.clear();

                //Search through the library data
                for (Shelf shelf : library.getShelves()) {
                    for (Book book : shelf.getBooks()) {
                        // Check if the title (lowered) contains the query
                        String title = book.getTitle().toLowerCase();

                        if (title.contains(query)) {
                            //Update the list with the closest matches
                            listModel.addElement(book.getTitle());
                        }
                    }
                }

                //box is empty, just repopulate the whole list
                if (query.isEmpty()) {
                    populateJlist();
                }
            }
        });


        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTitle = (String) list1.getSelectedValue();

                if (selectedTitle == null) {
                    JOptionPane.showMessageDialog(contentPane, "Please select a book from your list first.");
                    return;
                }

                //Find the book in the PATRON'S list (since they are checking it in)
                Book bookToReturn = null;
                for (Book b : patron.getBooks()) { //Accesses the User's books list
                    if (b.getTitle().equals(selectedTitle)) {
                        bookToReturn = b;
                        break;
                    }
                }

                if (bookToReturn != null) {
                    //Update the Book and the Patron
                    bookToReturn.checkIn(); //Sets isAvailable = true
                    patron.checkIn(bookToReturn, selectedTitle); //Removes from patron's list

                    JOptionPane.showMessageDialog(contentPane, "Checked in: " + selectedTitle);

                    //Refresh the UI
                    populateJlist();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "You don't appear to have this book checked out.");
                }
            }
        });


        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTitle = (String) list1.getSelectedValue();
                selectionValid(selectedTitle);

                //find the book in the library shelves
                for (Shelf shelf : library.getShelves()) {
                    Book book = shelf.findBook(selectedTitle);

                    if (book != null) {
                        if (book.getIsAvailable()) {
                            book.checkOut(); //sets isAvailable to false

                            if(patron.getBooks().size() < patron.getMaxCheckOutCount()){

                                JOptionPane.showMessageDialog(contentPane, "Successfully checked out: " + selectedTitle);
                                patron.checkOut(book);


                            } else {
                                JOptionPane.showMessageDialog(contentPane, "Check in some books first");
                            }

                            //refresh the List to show it's gone (or updated)
                            populateJlist();
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "This book is already checked out.");
                        }
                        return; //exit after finding the book
                    }
                }
            }
        });




        payFinesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatronPayment payment = new PatronPayment(patron,library);
                payment.setVisible(true);
                dispose();

            }
        });
    }


    public String whoami(){
        return patron.getName();
    }

    void populateJlist(){
        listModel.clear();
        for(Shelf shelf : library.getShelves()){
            for(Book book : shelf.getBooks()){
                listModel.addElement(book.getTitle());
            }
        }

    }

    void selectionValid(String Selected){
        if (Selected == null){
            JOptionPane.showMessageDialog(contentPane, "Please Select a book first");
        }
    }



}
