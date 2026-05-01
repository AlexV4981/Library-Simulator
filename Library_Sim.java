import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;
public class Library_Sim {
    private String name;
    private String address;
    //private Map<String, ArrayList<Shelf>> shelvesMap;
    private ArrayList<Shelf> shelves;
    private ArrayList<Book> catalog;
    private ArrayList<Librarian> librarians;
    private ArrayList<Patron> patrons;



    public Library_Sim(String name, String address){
        this.name = name;
        this.address = address;
        this.shelves = new ArrayList<Shelf>();
        this.catalog = new ArrayList<Book>();
        this.librarians = new ArrayList<Librarian>();
        this.patrons = new ArrayList<Patron>();

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

    public void addBook(Book book) {
        this.catalog.add(book);
    }

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public ArrayList<Librarian> getLibrarians() {
        return librarians;
    }

    public void shelfInfo(){
        for (Shelf shelf : shelves){
            System.out.println("Shelf name: " + shelf.getName() + "\nGenre: " + shelf.getGenre() + "\n"+shelf.getMaxRows() +"x" + shelf.getMaxColumns());
        }
    }
}
        
