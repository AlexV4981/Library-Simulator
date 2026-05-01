public class Librarian extends User {
    
    public Librarian(String name) {
        super(name);
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
