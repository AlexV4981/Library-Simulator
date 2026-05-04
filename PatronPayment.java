//Imports Swing components for the UI and AWT event listeners for handling user input.
//IMPORTS
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Defines a window for patrons to view their outstanding balance and submit payments to reduce their fines.
//CLASS DEFINITION
public class PatronPayment extends JFrame {

    //UI components for displaying the balance, entering payment amounts, and navigation.
    //UI COMPONENTS
    private JPanel contentPane;
    private JLabel pay;
    private JFormattedTextField enterAmountToPayFormattedTextField;
    private JButton payButton;
    private JButton backButton;
    private JLabel fees;

    //Contextual data for the specific patron and the shared library state.
    //INSTANCE VARIABLES
    private Patron patron;
    private Library_Sim library;

    //Initializes the payment interface and sets up logic for financial transactions.
    //CONSTRUCTOR
    public PatronPayment(Patron patron, Library_Sim library) {
        this.patron = patron;
        this.library = library;
        this.setContentPane(contentPane);
        this.setSize(600, 500);
        this.setTitle("Patron Menu-" + whoami());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Displays the patron's current total fine amount on the screen.
        //INITIAL BALANCE DISPLAY
        fees.setText("$" + patron.getFines());

        //Returns the user to the main navigation menu without processing a payment.
        //BACK BUTTON LOGIC
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatronMenu menu = new PatronMenu(patron, library);
                menu.setVisible(true);
                dispose();
            }
        });

        //Validates the numerical input and updates the patron's debt balance.
        //PAY BUTTON LOGIC
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Retrieves and parses the payment amount from the text field.
                    //INPUT PROCESSING
                    String input = enterAmountToPayFormattedTextField.getText();
                    double amount = Double.parseDouble(input);

                    //Calls the logic within the Patron class to subtract the amount from total fines.
                    patron.pay(amount);

                    //Updates the UI to reflect the new remaining balance.
                    //UI REFRESH
                    fees.setText(String.valueOf(patron.getFines()));
                    enterAmountToPayFormattedTextField.setText("");

                } catch (NumberFormatException ex) {
                    //Handles cases where the user enters non-numeric text.
                    //ERROR HANDLING
                    JOptionPane.showMessageDialog(contentPane, "Please enter a valid number!");
                }
            }
        });
    }

    //Helper method to retrieve the current patron's name for window title consistency.
    //USER IDENTITY
    public String whoami() {
        return patron.getName();
    }
}