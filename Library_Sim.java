//Acts as the central database and engine for the entire simulation, managing lists of books, shelves, and users.
//CLASS DEFINITION
import java.util.ArrayList;

public class Library_Sim {

    //Stores the physical details of the library and collections of all entities within the system.
    //INSTANCE VARIABLES
    private String name;
    private String address;
    private ArrayList<Shelf> shelves;
    private ArrayList<Book> catalog;
    private ArrayList<Librarian> librarians;
    private ArrayList<Patron> patrons;

    //Constructs a new library instance and initializes empty lists for all categories of data.
    //CONSTRUCTOR
    public Library_Sim(String name, String address){
        this.name = name;
        this.address = address;
        this.shelves = new ArrayList<Shelf>();
        this.catalog = new ArrayList<Book>();
        this.librarians = new ArrayList<Librarian>();
        this.patrons = new ArrayList<Patron>();
    }

    //Provides access to the collection of physical shelves.
    //GET SHELVES
    public ArrayList<Shelf> getShelves(){
        return this.shelves;
    }

    //Provides access to the master list of every book registered in the system.
    //GET CATALOG
    public ArrayList<Book> getCatalog() {
        return this.catalog;
    }

    //Returns the name of the library.
    //GET NAME
    public String getName(){
        return this.name;
    }

    //Returns the physical location of the library.
    //GET ADDRESS
    public String getAddress(){
        return this.address;
    }

    //Registers a new shelf object into the library's storage collection.
    //ADD SHELF
    public void addShelf(Shelf shelf){
        this.shelves.add(shelf);
    }

    //Adds a new book record to the global catalog.
    //ADD BOOK
    public void addBook(Book book) {
        this.catalog.add(book);
    }

    //Adds a librarian to the authorized personnel list.
    //ADD LIBRARIAN
    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    //Registers a new customer/patron in the system.
    //ADD PATRON
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    //Retrieves the list of all registered librarians.
    //GET LIBRARIANS
    public ArrayList<Librarian> getLibrarians() {
        return librarians;
    }

    //Retrieves the list of all registered patrons.
    //GET PATRONS
    public ArrayList<Patron> getPatrons() {
        return patrons;
    }

    //Iterates through all shelves and prints their specifications and genre to the console.
    //DISPLAY SHELF INFO
    public void shelfInfo(){
        for (Shelf shelf : shelves){
            System.out.println("Shelf name: " + shelf.getName() + "\nGenre: " + shelf.getGenre() + "\n"+shelf.getMaxRows() +"x" + shelf.getMaxColumns());
        }
    }
}