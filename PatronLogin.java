//Imports standard Swing components and event listeners for the patron authentication interface.
//IMPORTS
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Defines a window for patrons to register new accounts, log in to existing ones, or switch to the librarian portal.
//CLASS DEFINITION
public class PatronLogin extends JFrame {

    //UI components for user interaction and text entry.
    //UI COMPONENTS
    private JPanel contentPane;
    private JLabel pageTitle;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JButton switchToLibrarianButton;
    private JButton registerButton;
    private JButton loginButton;

    //State variables for the current session and the shared library simulation.
    //INSTANCE VARIABLES
    private Patron patron;
    private boolean isdefualt = true;
    private Library_Sim library;

    //Initializes the login frame and attaches event listeners to the buttons.
    //CONSTRUCTOR
    public PatronLogin(Library_Sim sim){
        this.setContentPane(contentPane);
        this.setSize(600,500);
        this.setTitle("Patron Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.library = sim;

        //Captures input fields to create a new Patron object and store it in the system.
        //REGISTER BUTTON LOGIC
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password1 = textField2.getText();

                //Prevents submission if fields are blank.
                //EMPTY FIELD CHECK
                if (username.isEmpty() || password1.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"You must enter a username and password");
                    return;
                }

                //Prevents multiple accounts with the same credentials.
                //DUPLICATE PATRON CHECK
                if(patronExists(username, password1) != null){
                    JOptionPane.showMessageDialog(null,"This patron already exists, please press login");
                    return;
                }

                //Instantiates the patron and registers them with the library data.
                //SAVE NEW PATRON
                patron = new Patron(username, password1, 3, 67.67);
                library.getPatrons().add(patron);

                JOptionPane.showMessageDialog(null,"Patron Created");
                isdefualt = false;
            }
        });

        //Verifies credentials against the library's patron list to grant access to the menu.
        //LOGIN BUTTON LOGIC
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password1 = textField2.getText();

                //Basic validation for the text fields.
                //EMPTY FIELD CHECK
                if(username.isEmpty() || password1.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Enter username and password");
                    return;
                }

                //Searches for a match in the library's patron records.
                //AUTHENTICATE PATRON
                Patron existing = patronExists(username, password1);

                //Handles failed login attempts.
                //MISSING PATRON CHECK
                if(existing == null){
                    JOptionPane.showMessageDialog(null,"Patron not found. Complete registration first");
                    return;
                }

                //Transitions the user to the main patron navigation menu.
                //SUCCESSFUL LOGIN
                PatronMenu menu = new PatronMenu(existing, library);
                menu.setVisible(true);
                dispose();
            }
        });

        //Closes this window and opens the librarian-specific login portal.
        //SWITCH TO LIBRARIAN LOGIC
        switchToLibrarianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibrarianLogin libLogin = new LibrarianLogin(library);
                libLogin.setVisible(true);
                dispose();
            }
        });
    }

    //Accessor methods to retrieve the patron context of this login session.
    //GETTERS
    public Patron whoami(){
        return patron;
    }

    public Patron getPatron(){
        return this.patron;
    }

    //Updates the local library reference to stay in sync with system changes.
    //LIBRARY SYNC
    public void updateLibrary(Library_Sim library){
        this.library = library;
    }

    //Helper method that iterates through the system's patrons to find credentials matching the input.
    //AUTHENTICATION HELPER
    public Patron patronExists(String username, String password){
        ArrayList<Patron> patrons = library.getPatrons();
        Patron foundPatron = null;

        //Prevents errors if no patrons have been registered yet.
        //LIST VALIDATION
        if(patrons == null || patrons.isEmpty()){
            return foundPatron;
        }

        //Linear search for name and password match.
        //SEARCH LOOP
        for(Patron p : patrons){
            //Safeguard against null entries in the patron list.
            //NULL OBJECT CHECK
            if (p != null) {
                if(p.getName().equals(username) && p.getPassword().equals(password)){
                    foundPatron = p;
                    break;
                }
            }
        }
        return foundPatron;
    }
}