import java.util.ArrayList;

public class Library_Sim {
    private String name; // [cite: 2]
    private String address; // [cite: 3]
    private ArrayList<Shelf> shelves; // [cite: 5]

    public Library_Sim(String name, String address) { // [cite: 6]
        this.name = name;
        this.address = address;
        this.shelves = new ArrayList<Shelf>();
    }

    public ArrayList<Shelf> getShelves() { // [cite: 7]
        return this.shelves;
    }

    public String getName() { // [cite: 9]
        return this.name;
    }

    public String getAddress() { // [cite: 10]
        return this.address;
    }

    public void addShelf(Shelf shelf) { // 
        this.shelves.add(shelf);
    }

    // Added to allow Librarian to add books to the library
    public void addBook(Book book) {
        for (Shelf shelf : shelves) {
            if (shelf.getGenre().equalsIgnoreCase(book.getGenre()) && !shelf.isFull()) {
                shelf.addBook(book);
                return;
            }
        }
        System.out.println("No available or matching shelf found for genre: " + book.getGenre());
    }

    public void shelfInfo() { // [cite: 12]
        for (Shelf shelf : shelves) {
            System.out.println("Shelf name: " + shelf.getName() +
                    "\nGenre: " + shelf.getGenre() +
                    "\nCapacity: " + shelf.getMaxRows() + "x" + shelf.getMaxColumns());
        }
    }
}