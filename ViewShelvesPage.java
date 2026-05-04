/**
 * this uses the same system as viewing the whole catalog to view just a single shelf of books
 *
 * @author Hung Nguyen
 */

import javax.swing.*;
import java.awt.*;

public class ViewShelvesPage extends JFrame {

    private Library_Sim library;
    private Librarian currentLibrarian;

    public ViewShelvesPage(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        setTitle("Library Shelves");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("LIBRARY SHELVES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (Shelf shelf : library.getShelves()) {

            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel info = new JLabel("<html><b>" + shelf.getName() + "</b><br>"
                    + "Genre: " + shelf.getGenre() + "<br>"
                    + "Capacity: " + shelf.getMaxColumns() * shelf.getMaxRows() + "<br>"
                    + "Books: " + shelf.getBooks().size() + "</html>");

            JButton viewButton = new JButton("View Books");
            viewButton.addActionListener(e -> {
                new BookListPage(library, currentLibrarian, shelf);
                dispose();
            });

            row.add(info);
            row.add(viewButton);
            listPanel.add(row);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, currentLibrarian);
            dispose();
        });

        JPanel bottom = new JPanel(new FlowLayout());
        bottom.add(backButton);

        mainPanel.add(bottom, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
