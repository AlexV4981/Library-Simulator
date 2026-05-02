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
    private Patron defaultPatron = new Patron("Defualt","123",3,67.67);
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
                patron = new Patron(username,password1,10,0.0);
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
                    System.out.println("USER NAME AND PASSWORD IS EMPTY USING DEFAULT");
                    PatronMenu menu = new PatronMenu(defaultPatron,library);
                    menu.setVisible(true);
                    dispose();
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
                System.out.print("WORKS IMPLEMENT LATER");
            }
        });
    }


    public Patron whoami(){
        return (isdefualt)?patron:defaultPatron;
    }


}
