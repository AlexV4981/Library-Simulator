import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;
public class Library_Sim {
    private String name;
    private String address;
    //private Map<String, ArrayList<Shelf>> shelvesMap;
    private ArrayList<Shelf> shelves;


    public Library_Sim(String name, String address){
        this.name = name;
        this.address = address;
        this.shelves = new ArrayList<Shelf>();
    }

    public ArrayList<Shelf> getShelves(){
        return this.shelves;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public void addShelf(Shelf shelf){
        this.shelves.add(shelf);
    }

    public void shelfInfo(){
        for (Shelf shelf : shelves){
            System.out.println("Shelf name: " + shelf.getName() + "\nGenre: " + shelf.getGenre() + "\n"+shelf.getMaxRows() +"x" + shelf.getMaxColumns());
        }
    }




}

class Shelf {
    private String name;
    private String genre;
    private ArrayList<ArrayList<Book>> books;
    private int max_rows;
    private int max_columns;

    public Shelf(String name, String genre, int max_rows, int max_columns){
        this.name = name;
        this.genre = genre;
        this.max_rows = max_rows;
        this.max_columns = max_columns;
        this.books = new ArrayList<ArrayList<Book>>();
        for(int i = 0; i < max_rows; i++){
            this.books.add(new ArrayList<Book>());
        }

    }


    public boolean isFull(){
        int totalBooks = 0;
        for(int i = 0; i < max_rows; i++){
            if(this.books.get(i).size() < max_columns){
                totalBooks += this.books.get(i).size();
            }

        }

        return totalBooks == max_rows * max_columns;
    }

    public void addBook(Book book){
        for(int i = 0; i < max_rows; i++){
            if (this.books.get(i).size() < max_columns){
                this.books.get(i).add(book);
                break;
            }
        }
    }

    public void checkOutBook(String title){
        Book book = findBook(title);
        if(book != null && book.getIsAvailable()){
            book.checkOut();
        }
    }

    public void checkInBook(String title){
        Book book = findBook(title);
        if(book != null && !book.getIsAvailable()){
            book.checkIn();
        }
    }

    public Book findBook(String title){
        for(int i = 0; i < max_rows; i++){
            for(int j = 0; j < this.books.get(i).size(); j++){
                if(this.books.get(i).get(j).getTitle().equals(title)){
                    return this.books.get(i).get(j);
                }
            }
        }

        return null;
    }

    public void shelfBookInfo(){
        for(int i = 0; i < max_rows; i++){
            for(int j = 0; j < this.books.get(i).size(); j++){
                System.out.println(this.books.get(i).get(j).toString());
            }
        }
    }

    @Override
    public String toString(){
        return "Shelf name: " + this.name + "\nGenre: " + this.genre;
    }

    public String getName(){
        return this.name;
    
    }

    public String getGenre(){
        return this.genre;
    }

    public int getMaxRows(){
        return this.max_rows;
    }

    public int getMaxColumns(){
        return this.max_columns;
    }

}

class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private boolean isAvailable;

    public Book(String title, String author, int publicationYear, String genre){
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.isAvailable = true;
    }

    @Override
    public String toString(){
        return "Title: " + this.title + "\nAuthor: " + this.author + "\nPublication Year: " + this.publicationYear + "\nGenre: " + this.genre + "\nAvailable: " + this.isAvailable+"\n___________________________________________________________________________________";
    }

    public void checkOut(){
        this.isAvailable = false;
    }

    public void checkIn(){
        this.isAvailable = true;
    }


    //getters
    public boolean getIsAvailable(){
        return this.isAvailable;
    }

    public String getTitle(){
        return this.title;
    }   

    public String getAuthor(){
        return this.author;
    }

    public int getPublicationYear(){
        return this.publicationYear;
    }

    public String getGenre(){
        return this.genre;
    }



}

class User {
    private String name;
    private int ID;
    private arrayList<Book> books;

    public User(String name) {
        this.name = name;
    }

    public void checkOut(Book book) {
        book.checkOut();
        books.add(book);
    }

    public void checkIn(Book book) {
        book.checkIn();
        books.remove(book);
    }
}

class Patron extends User {
    private int maxCheckOutCount;
    private double fines;
    
    pubilc Patron(int maxCheckOutCount, double fines) {
        super();
        this.maxCheckOutCount = maxCheckOutCount;
        this.fines = fines;
    }

    // there has to be a function to call to make the patron gain fines
    // even though it's public, that doesn't necessarily mean the patron has
    // accesss to this function
    public void fine(double amount) {
        fines += amount;
    }
    
    public void pay(double amount) {
        if (fines - amount < 0) {
            System.out.println("You have overpaid");
            System.out.println("You only have to pay " + fines);
            System.out.println("You have been refunded. Try again.");
        }
        else if (fines - amount > 0) {
            fines -= amount;
            System.out.println("You have $" + fines + " remaining to pay");
        }
        else {
            System.out.println("You have fully paid your fines.");
            System.out.println("Thank You!");
        }
        
    }
}

class Librarian extends User {
    public Librarian() {
        super();
    }

    public void addShelf(Library_Sim library, Shelf shelf) {
        library.addShelf(shelf);
    }

    public void addBook(Library_Sim library, Book book) {
        library.addBook(book);
    }

    // i didn't realize this while making the class diagram, but someone has to be able to 
    // give the Patrons fines
    public void fine(Patron patron, double fines) {
        patron.fine(fines);
    }
    
}

        
