public class Patron extends User {
    private int maxCheckOutCount;
    private double fines;
    private String password;
    
    public Patron(String name, int maxCheckOutCount, double fines) {
        super(name);
        this.maxCheckOutCount = maxCheckOutCount;
        this.fines = fines;
    }
    public Patron(String name,String password, int maxCheckOutCount, double fines) {
        super(name);
        this.maxCheckOutCount = maxCheckOutCount;
        this.fines = fines;
        this.password = password;
    }

    public void fine(double amount) {
        fines += amount;
    }
    
    public void pay(double amount) {
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

    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }
    public int getMaxCheckOutCount(){ return this.maxCheckOutCount; }
    public void setMaxCheckOutCount(int count){ this.maxCheckOutCount = count; }
    public double getFines(){ return this.fines; }
    public void setFines(double fines){ this.fines = fines; }
    public String getPassword(){ return this.password; }
    public void setPassword(String password){ this.password = password; }

    @Override
    public String toString() {
        return "Patron " + name;
    }

}
