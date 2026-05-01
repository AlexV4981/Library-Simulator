import javax.swing.*;
import java.awt.*;

public class LibrarianDashboard extends JFrame {

    private JButton viewBooksButton;
    private JButton checkOutButton;
    private JButton checkInButton;
    private JButton finePageButton;
    private Library_Sim library;

    public LibrarianDashboard(Library_Sim library) {
        this.library = library;
        setTitle("Librarian Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("LIBRARIAN PORTAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // View Books
        gbc.gridy = 1;
        viewBooksButton = new JButton("View Books");
        panel.add(viewBooksButton, gbc);

        // Check Out Books
        gbc.gridy = 2;
        checkOutButton = new JButton("Check Out Books");
        panel.add(checkOutButton, gbc);

        // Check In Books
        gbc.gridy = 3;
        checkInButton = new JButton("Check In Books");
        panel.add(checkInButton, gbc);

        // Fine Management Page
        gbc.gridy = 4;
        finePageButton = new JButton("Fine Management");
        panel.add(finePageButton, gbc);

        add(panel);
        setVisible(true);
    }
}
