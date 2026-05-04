//Imports the Swing components for user interface elements and AWT for layout and fonts.
//IMPORTS
import javax.swing.*;
import java.awt.*;

//Defines a window for librarians to view a list of patrons and apply monetary fines to their accounts.
//CLASS DEFINITION
public class FineManagementPage extends JFrame {

    //References to the library simulation data and the librarian performing the management tasks.
    //INSTANCE VARIABLES
    private Library_Sim library;
    private Librarian currentLibrarian;

    //Initializes the frame and constructs the user interface for patron selection and fine entry.
    //CONSTRUCTOR
    public FineManagementPage(Library_Sim library, Librarian librarian) {
        this.library = library;
        this.currentLibrarian = librarian;

        //Configures the basic window properties such as title, size, and screen centering.
        //FRAME SETTINGS
        setTitle("Fine Management");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //The root container using BorderLayout to organize the header, list, and control panel.
        //MAIN LAYOUT
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Section header label placed at the top of the window.
        //HEADER SECTION
        JLabel titleLabel = new JLabel("FINE MANAGEMENT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        //Populates a list model with all registered patrons from the library system.
        //PATRON DATA MODEL
        DefaultListModel<Patron> patronModel = new DefaultListModel<>();
        for (Patron p : library.getPatrons()) {
            patronModel.addElement(p);
        }

        //Creates a selection list that allows the librarian to pick one patron at a time.
        //LIST SELECTION
        JList<Patron> patronList = new JList<>(patronModel);
        patronList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Provides a scrollable view for the patron list to handle larger datasets.
        //SCROLLABLE VIEW
        JScrollPane scrollPane = new JScrollPane(patronList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        //Container for the fine amount input field and the action buttons.
        //CONTROL PANEL
        JPanel bottomPanel = new JPanel(new FlowLayout());

        //Components for inputting the fine value and submitting or canceling the action.
        //INPUT COMPONENTS
        JTextField fineField = new JTextField(10);
        JButton applyFineButton = new JButton("Apply Fine");
        JButton backButton = new JButton("Back");

        //Validates the selected patron and input amount before updating the patron's fine balance.
        //APPLY FINE LOGIC
        applyFineButton.addActionListener(e -> {
            Patron selected = patronList.getSelectedValue();

            //Ensure a recipient is selected from the list.
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a patron.");
                return;
            }

            try {
                double amount = Double.parseDouble(fineField.getText());

                //Logic check to ensure the librarian isn't entering zero or negative values.
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Fine must be positive.");
                    return;
                }

                //Updates the patron's financial record.
                selected.fine(amount);
                JOptionPane.showMessageDialog(this,
                        "Fine of $" + amount + " applied to " + selected.getName());

                fineField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number.");
            }
        });

        //Closes the fine management screen and returns the user to the Librarian Dashboard.
        //BACK BUTTON LOGIC
        backButton.addActionListener(e -> {
            new LibrarianDashboard(library, currentLibrarian);
            dispose();
        });

        //Assembly of the fine input tools and navigation buttons into the footer.
        //FINAL ASSEMBLY
        bottomPanel.add(new JLabel("Fine Amount:"));
        bottomPanel.add(fineField);
        bottomPanel.add(applyFineButton);
        bottomPanel.add(backButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        //Finalizes the JFrame by adding the main panel and making the window visible.
        //FINALIZATION
        add(mainPanel);
        setVisible(true);
    }
}