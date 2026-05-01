import javax.swing.*;
import java.awt.*;

public class CheckInPage extends JFrame {

    private Library_Sim library;
    private User currentUser;

    public CheckInPage(Library_Sim library, User currentUser) {
        this.library = library;
        this.currentUser = currentUser;

        setTitle("Return Books");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("BOOKS YOU HAVE CHECKED OUT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        // Loop through all checked-out books
        for (Book b : currentUser.getBooks()) {

            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel info = new JLabel("<html><b>" + b.getTitle() + "</b><br>"
                    + b.getAuthor() + "<br>"
                    + "Genre: " + b.getGenre() + "</html>");

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

        JScrollPane scrollPane = new JScrollPane(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, (Librarian) currentUser);
            dispose();
        });

        bottomPanel.add(backButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
