public class Librarian extends User {
    private String username;
    private String password;

    public Librarian(String username, String password) {
        super(username); // store name in User
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    public void addShelf(Library_Sim library, Shelf shelf) {
        library.addShelf(shelf);
    }

    public void addBook(Library_Sim library, Book book) {
        library.addBook(book);
    }

    public void fine(Patron patron, double amount) {
        patron.fine(amount);
    }
}