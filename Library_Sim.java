import java.util.ArrayList;

public class Library_Sim {
    private String name;
    private String address;
    private ArrayList<Shelf> shelves;

    public Library_Sim(String name, String address) {
        this.name = name;
        this.address = address;
        this.shelves = new ArrayList<Shelf>();
    }

    public ArrayList<Shelf> getShelves() {
        return this.shelves;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public void addShelf(Shelf shelf) {
        this.shelves.add(shelf);
    }

    //Added to allow Librarian to add books to the library
    public void addBook(Book book) {
        for (Shelf shelf : shelves) {
            if (shelf.getGenre().equalsIgnoreCase(book.getGenre()) && !shelf.isFull()) {
                shelf.addBook(book);
                return;
            }
        }
        System.out.println("No available or matching shelf found for genre: " + book.getGenre());
    }

    public void shelfInfo() {
        for (Shelf shelf : shelves) {
            System.out.println("Shelf name: " + shelf.getName() + 
                               "\nGenre: " + shelf.getGenre() + 
                               "\nCapacity: " + shelf.getMaxRows() + "x" + shelf.getMaxColumns());
        }
    }
}
