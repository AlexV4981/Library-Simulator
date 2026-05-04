//Defines the core data structure representing a single book in the library system.
//CLASS DEFINITION
public class Book {

    //Attributes that store the book's metadata and current rental status.
    //INSTANCE VARIABLES
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private boolean isAvailable;

    //Initializes a new Book instance with provided details and sets default availability to true.
    //CONSTRUCTOR
    public Book(String title, String author, int publicationYear, String genre) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.isAvailable = true;
    }

    //Provides a formatted string representation of the book's details for console display or UI components.
    //STRING REPRESENTATION
    @Override
    public String toString() {
        return "Title: " + this.title + "\nAuthor: " + this.author +
                "\nPublication Year: " + this.publicationYear +
                "\nGenre: " + this.genre + "\nAvailable: " + this.isAvailable +
                "\n___________________________________________________________________________________";
    }

    //Updates the status to unavailable when a user borrows the book.
    //CHECK OUT METHOD
    public void checkOut() {
        this.isAvailable = false;
    }

    //Updates the status to available when the book is returned to the library.
    //CHECK IN METHOD
    public void checkIn() {
        this.isAvailable = true;
    }

    //Standard accessor methods to retrieve private book information from other classes.
    //GETTERS
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public String getGenre() {
        return this.genre;
    }
}