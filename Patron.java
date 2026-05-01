public class Patron extends User { // [cite: 65, 68]
    private int maxCheckOutCount; // [cite: 58]
    private double fines; // [cite: 60]
    
    public Patron(String name, int maxCheckOutCount, double fines) {
        super(name); // Required since User constructor takes a String
        this.maxCheckOutCount = maxCheckOutCount;
        this.fines = fines;
    }

    public void fine(double amount) {
        fines += amount;
    }
    
    public void pay(double amount) { // [cite: 62]
        if (fines - amount < 0) {
            System.out.println("You have overpaid.");
            System.out.println("You only have to pay $" + fines);
            System.out.println("You have been refunded. Try again.");
        } else if (fines - amount > 0) {
            fines -= amount;
            System.out.println("You have $" + fines + " remaining to pay.");
        } else {
            fines = 0;
            System.out.println("You have fully paid your fines.");
            System.out.println("Thank You!");
        }
    }
}