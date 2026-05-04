//Represents a physical storage unit in the library using a 2D-like structure (list of lists) to manage book placement.
//CLASS DEFINITION
import java.util.ArrayList;

public class Shelf {

    //Attributes defining the shelf's identity, category, physical capacity, and contents.
    //INSTANCE VARIABLES
    private String name;
    private String genre;
    private ArrayList<ArrayList<Book>> books;
    private int max_rows;
    private int max_columns;

    //Initializes the shelf and creates the internal row structures based on the specified maximum rows.
    //CONSTRUCTOR
    public Shelf(String name, String genre, int max_rows, int max_columns) {
        this.name = name;
        this.genre = genre;
        this.max_rows = max_rows;
        this.max_columns = max_columns;
        this.books = new ArrayList<ArrayList<Book>>();

        //Pre-populates the top-level list with sub-lists representing individual rows.
        for(int i = 0; i < max_rows; i++) {
            this.books.add(new ArrayList<Book>());
        }
    }

    //Determines if the shelf has reached its total capacity by checking the size of each row against the column limit.
    //CAPACITY CHECK
    public boolean isFull() {
        int totalBooks = 0;
        for(int i = 0; i < max_rows; i++) {
            totalBooks += this.books.get(i).size();
        }
        return totalBooks >= (max_rows * max_columns);
    }

    //Adds a book to the first available slot by scanning rows from top to bottom.
    //ADD BOOK
    public void addBook(Book book) {
        for(int i = 0; i < max_rows; i++) {
            //Verify the current row has not exceeded the horizontal column limit.
            if (this.books.get(i).size() < max_columns) {
                this.books.get(i).add(book);
                break;
            }
        }
    }

    //Locates a book by its title and marks it as unavailable for borrowing.
    //CHECK OUT LOGIC
    public void checkOutBook(String title) {
        Book book = findBook(title);
        if(book != null && book.getIsAvailable()) {
            book.checkOut();
        }
    }

    //Locates a book by its title and marks it as available for other users.
    //CHECK IN LOGIC
    public void checkInBook(String title) {
        Book book = findBook(title);
        if(book != null && !book.getIsAvailable()) {
            book.checkIn();
        }
    }

    //Performs a linear search through the 2D list structure to find a book with a matching title.
    //SEARCH METHOD
    public Book findBook(String title) {
        for(int i = 0; i < max_rows; i++) {
            for(int j = 0; j < this.books.get(i).size(); j++) {
                if(this.books.get(i).get(j).getTitle().equals(title)) {
                    return this.books.get(i).get(j);
                }
            }
        }
        return null;
    }

    //Prints the details of every book currently stored on this shelf to the console.
    //SHELF AUDIT
    public void shelfBookInfo() {
        for(int i = 0; i < max_rows; i++) {
            for(int j = 0; j < this.books.get(i).size(); j++) {
                System.out.println(this.books.get(i).get(j).toString());
            }
        }
    }

    //Returns a brief summary of the shelf's name and genre.
    //STRING REPRESENTATION
    @Override
    public String toString() {
        return "Shelf name: " + this.name + "\nGenre: " + this.genre;
    }

    //Standard accessor methods for shelf properties.
    //GETTERS
    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getMaxRows() {
        return this.max_rows;
    }

    public int getMaxColumns() {
        return this.max_columns;
    }

    //Flattens the 2D row/column structure into a single 1D list for easier display in GUI components.
    //FLATTEN BOOKS LIST
    public ArrayList<Book> getBooks() {
        ArrayList<Book> flat = new ArrayList<>();
        for (ArrayList<Book> rowList : books) {
            for (Book b : rowList) {
                if (b != null) {
                    flat.add(b);
                }
            }
        }
        return flat;
    }
}