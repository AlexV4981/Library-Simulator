//Imports standard Swing components and AWT layout tools for the login interface.
//IMPORTS
import javax.swing.*;
import java.awt.*;

//Defines the login portal for librarians, allowing for authentication, account creation, and role switching.
//CLASS DEFINITION
public class LibrarianLogin extends JFrame {

    //UI elements for capturing user credentials and navigating the application.
    //UI COMPONENTS
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton switchToPatronButton;
    private JButton createLibrarianButton;

    //Reference to the centralized library system data.
    //INSTANCE VARIABLES
    private Library_Sim library;

    //Initializes the login frame and organizes the visual components.
    //CONSTRUCTOR
    public LibrarianLogin(Library_Sim library) {
        this.library = library;

        //Configures basic window properties and centering.
        //FRAME SETTINGS
        setTitle("Librarian Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        //Uses GridBagLayout to create a flexible, centered grid for the login form.
        //LAYOUT SETUP
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Header title for the login screen.
        //HEADER SECTION
        JLabel titleLabel = new JLabel("LIBRARIAN LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        //Input fields for the librarian's username.
        //USERNAME FIELD
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField();
        panel.add(usernameField, gbc);

        //Input fields for the librarian's password using hidden characters.
        //PASSWORD FIELD
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField();
        panel.add(passwordField, gbc);

        //Primary action buttons for entering the dashboard or creating an account.
        //BUTTON ASSEMBLY
        gbc.gridy = 3;
        gbc.gridx = 0;
        loginButton = new JButton("Login");
        panel.add(loginButton, gbc);

        gbc.gridx = 1;
        createLibrarianButton = new JButton("Create Librarian");
        panel.add(createLibrarianButton, gbc);

        //Provides a way to navigate to the Patron-specific login screen.
        //SWITCH ROLE SECTION
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        switchToPatronButton = new JButton("Switch to Patron Login");
        panel.add(switchToPatronButton, gbc);

        add(panel);
        setVisible(true);

        setupActions();
    }

    //Attaches functional logic to the buttons for login, registration, and switching views.
    //EVENT HANDLING
    private void setupActions() {

        //Iterates through the library's librarian list to verify the entered credentials.
        //LOGIN LOGIC
        loginButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            for (Librarian lib : library.getLibrarians()) {
                if (lib.authenticate(user, pass)) {
                    new LibrarianDashboard(library, lib);
                    dispose();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Invalid login");
        });

        //Opens a dialog to collect details for a new Librarian account and adds them to the system.
        //CREATE LIBRARIAN LOGIC
        createLibrarianButton.addActionListener(e -> {
            JTextField newUser = new JTextField();
            JPasswordField newPass = new JPasswordField();

            Object[] fields = {
                    "New Username:", newUser,
                    "New Password:", newPass
            };

            int result = JOptionPane.showConfirmDialog(
                    this,
                    fields,
                    "Create Librarian",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                String u = newUser.getText();
                String p = new String(newPass.getPassword());

                if (u.isEmpty() || p.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
                    return;
                }

                Librarian newLibn = new Librarian(u, p);
                library.addLibrarian(newLibn);
                JOptionPane.showMessageDialog(this, "Librarian created! You can now log in.");
            }
        });

        //Transitions the application context from a librarian view to a patron view.
        //SWITCH TO PATRON LOGIC
        switchToPatronButton.addActionListener(e -> {
            PatronLogin patron = new PatronLogin(library);
            patron.setVisible(true);
            dispose();
        });
    }
}