import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatronPayment extends JFrame {

    private JPanel contentPane;
    private JLabel pay;
    private JFormattedTextField enterAmountToPayFormattedTextField;
    private JButton payButton;
    private JButton backButton;
    private JLabel fees;

    private Patron patron;
    private Library_Sim library;

    public PatronPayment(Patron patron, Library_Sim library) {
        this.patron = patron;
        this.library = library;
        this.setContentPane(contentPane);
        this.setSize(600, 500);
        this.setTitle("Patron Menu-"+whoami());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        fees.setText("$" + patron.getFines());

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatronMenu menu = new PatronMenu(patron,library);
                menu.setVisible(true);
                dispose();
            }
        });

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Convert the String from the text field into a number
                    String input = enterAmountToPayFormattedTextField.getText();
                    double amount = Double.parseDouble(input);
                    patron.pay(amount);

                    fees.setText(String.valueOf(patron.getFines()));

                    enterAmountToPayFormattedTextField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Please enter a valid number!");
                }
            }
        });
    }


    public String whoami(){
        return patron.getName();
    }

}
