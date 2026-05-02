import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatronLogin extends JFrame
{

    private JPanel contentPane;
    private JLabel pageTitle;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JButton switchToLibrarianButton;
    private JButton registerButton;
    private JButton loginButton;

    private Patron patron;
    private boolean isdefualt = true;
    private Library_Sim library;

    public PatronLogin(Library_Sim sim){
        this.setContentPane(contentPane);
        this.setSize(600,500);
        this.setTitle("Patron Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.library = sim;
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password1 = textField2.getText();
                patron = new Patron(username,password1,3,67.67);
                System.out.println("REGISTER WORKS");
                isdefualt = false;


            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password1 = textField2.getText();

                if(username.equals("") && password1.equals("")){
                    JOptionPane.showMessageDialog(null,"Enter username and password");
                } else {
                    try{
                        if (username.equals(patron.getName()) && password1.equals(patron.getPassword())){
                            PatronMenu menu = new PatronMenu(patron,library);
                            menu.setVisible(true);
                            System.out.println("LOGIN WORKS");
                            dispose();
                        }
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(null,"Hit register before login");
                    }
                }



            }
        });
        switchToLibrarianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibrarianLogin libLogin = new LibrarianLogin(library);
                libLogin.setVisible(true);
                dispose();
            }
        });
    }


    public Patron whoami(){
        return patron;
    }


}
