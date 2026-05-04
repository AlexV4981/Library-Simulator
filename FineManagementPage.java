/**
 * this allows the librarian to look at patron accounts and add fines to their accounts.
 *
 * @author Hung Nguyen
 */

import javax.swing.*;
import java.awt.*;

public class FineManagementPage extends JFrame {

    private Library_Sim library;
    private Librarian currentLibrarian;

    public FineManagementPage(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        // Screen Title
        setTitle("Fine Management");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("FINE MANAGEMENT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Patron list
        DefaultListModel<Patron> patronModel = new DefaultListModel<>();
        for (Patron p : library.getPatrons()) {
            patronModel.addElement(p);
        }

        JList<Patron> patronList = new JList<>(patronModel);
        patronList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(patronList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for fine input + buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());

        JTextField fineField = new JTextField(10);
        JButton applyFineButton = new JButton("Apply Fine");
        JButton backButton = new JButton("Back");

        applyFineButton.addActionListener(e -> {
            Patron selected = patronList.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a patron.");
                return;
            }

            try {
                double amount = Double.parseDouble(fineField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Fine must be positive.");
                    return;
                }

                selected.fine(amount);
                JOptionPane.showMessageDialog(this,
                        "Fine of $" + amount + " applied to " + selected.getName());

                fineField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number.");
            }
        });

        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, currentLibrarian);
            dispose();
        });

        bottomPanel.add(new JLabel("Fine Amount:"));
        bottomPanel.add(fineField);
        bottomPanel.add(applyFineButton);
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
