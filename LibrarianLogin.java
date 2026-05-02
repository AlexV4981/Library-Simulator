import javax.swing.*;
import java.awt.*;

public class LibrarianLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton switchToPatronButton;
    private JButton createLibrarianButton;

    private Library_Sim library;

    public LibrarianLogin(Library_Sim library) {
        this.library = library;

        setTitle("Librarian Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("LIBRARIAN LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Username
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField();
        panel.add(usernameField, gbc);

        // Password
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField();
        panel.add(passwordField, gbc);

        // Login button
        gbc.gridy = 3;
        gbc.gridx = 0;
        loginButton = new JButton("Login");
        panel.add(loginButton, gbc);

        // Create Librarian button
        gbc.gridx = 1;
        createLibrarianButton = new JButton("Create Librarian");
        panel.add(createLibrarianButton, gbc);

        // Switch to Patron Login
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        switchToPatronButton = new JButton("Switch to Patron Login");
        panel.add(switchToPatronButton, gbc);

        add(panel);
        setVisible(true);

        setupActions();
    }

    private void setupActions() {

        // LOGIN ACTION
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

        // CREATE LIBRARIAN ACTION
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

        switchToPatronButton.addActionListener(e -> {
            PatronLogin patron = new PatronLogin(library);
            patron.setVisible(true);
            dispose();

        });
    }
}
