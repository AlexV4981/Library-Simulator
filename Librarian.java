//Extends the User base class to provide administrative functionality for library staff members.
//CLASS DEFINITION
public class Librarian extends User {

    //Credentials specifically used for librarian-level access control.
    //INSTANCE VARIABLES
    private String username;
    private String password;

    //Initializes the librarian with a username and password, passing the username to the parent User class.
    //CONSTRUCTOR
    public Librarian(String username, String password) {
        super(username); // store name in User
        this.username = username;
        this.password = password;
    }

    //Verifies if the provided credentials match the stored librarian data.
    //AUTHENTICATION
    public boolean authenticate(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    //Administrative method to register a new storage shelf within the library simulation.
    //SHELF MANAGEMENT
    public void addShelf(Library_Sim library, Shelf shelf) {
        library.addShelf(shelf);
    }

    //Administrative method to register a new book into the global library catalog.
    //BOOK MANAGEMENT
    public void addBook(Library_Sim library, Book book) {
        library.addBook(book);
    }

    //Allows the librarian to penalize a patron by applying a monetary fine to their record.
    //FINANCIAL MANAGEMENT
    public void fine(Patron patron, double amount) {
        patron.fine(amount);
    }
}