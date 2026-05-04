//Extends the User class to represent a library customer with borrowing limits and financial records.
//CLASS DEFINITION
import javax.swing.*;

public class Patron extends User {

    //Tracks the patron's specific borrowing constraints, outstanding debt, and login credentials.
    //INSTANCE VARIABLES
    private int maxCheckOutCount;
    private double fines;
    private String password;

    //Initializes a patron with basic details; used for quick system generation or legacy data.
    //CONSTRUCTOR (BASIC)
    public Patron(String name, int maxCheckOutCount, double fines) {
        super(name);
        this.maxCheckOutCount = maxCheckOutCount;
        this.fines = fines;
    }

    //Comprehensive constructor including password for full account authentication.
    //CONSTRUCTOR (FULL)
    public Patron(String name, String password, int maxCheckOutCount, double fines) {
        super(name);
        this.maxCheckOutCount = maxCheckOutCount;
        this.fines = fines;
        this.password = password;
    }

    //Increases the patron's total debt when a fine is issued by a librarian.
    //FINE APPLICATION
    public void fine(double amount) {
        fines += amount;
    }

    //Handles the logic for reducing debt, including validation to prevent overpayment.
    //PAYMENT PROCESSING
    public void pay(double amount) {
        if (fines - amount < 0) {
            //Logic to handle payments exceeding the current balance.
            JOptionPane.showMessageDialog(null,"You overpaid and were refunded try again. You only owe $"+fines);
        } else if (fines - amount > 0) {
            //Updates balance for partial payments.
            fines -= amount;
            JOptionPane.showMessageDialog(null,"You have $" + fines + " remaining to pay.");
        } else {
            //Clears the fine balance entirely.
            fines = 0;
            JOptionPane.showMessageDialog(null,"Full payment successful");
        }
    }

    //Standard accessor and mutator methods for managing private patron data.
    //GETTERS AND SETTERS
    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }
    public int getMaxCheckOutCount(){ return this.maxCheckOutCount; }
    public void setMaxCheckOutCount(int count){ this.maxCheckOutCount = count; }
    public double getFines(){ return this.fines; }
    public void setFines(double fines){ this.fines = fines; }
    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password = password; }

    //Returns a simplified string identifier for use in lists and UI components.
    //STRING REPRESENTATION
    @Override
    public String toString() {
        return "Patron " + name;
    }

}