import java.util.ArrayList;


public class Library {
    private String name;
    private String address;
    private ArrayList<Shelf> shelves = new ArrayList<>();
    private int totalBooksInLibrary = 0;

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
    }






    public void viewFreeShelfSlots(Shelf shelf) {
        System.out.println("Shelf: " + shelf.getName());
        System.out.println("Free slots: " + shelf.getCurrentFreeSlots());
    }

    public void checkOutBookFromShelf(Shelf shelf, int column, int row) {
        if (shelves.contains(shelf)) {
            shelf.checkOutBook(column, row);
        } else {
            System.out.println("Shelf not found in the library.");
        }
    }

    public void addBookToShelf(Book book, Shelf shelf) {
        if (shelves.contains(shelf)) {
            shelf.addBook(book);
        } else {
            System.out.println("Shelf not found in the library.");

        }
    }

    public void printShelf(Shelf shelf) {
        if (shelves.contains(shelf)) {
            System.out.println("Books in " + shelf.getName() + " shelf:");

            if(shelf.getCurrentBookCount() == 0) {
                System.out.println("No books in this shelf.");
            } else {
                shelf.viewBooks();
            }


        } else {
            System.out.println("Shelf not found in the library.");
        }
    }

    public void addShelf(Shelf shelf) {
        shelves.add(shelf);
    }

    public void removeShelf(Shelf shelf) {
        shelves.remove(shelf);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void getTotalBooksInLibrary() {
        totalBooksInLibrary = 0;
        for (Shelf shelf : shelves) {
            totalBooksInLibrary += shelf.getCurrentBookCount();
        }
        System.out.println("Total books in the library: " + totalBooksInLibrary);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}


class Book {
    private String title;
    private String author;
    private int publicationYear;
    private boolean isAvailable = true;

    private static int totalBooks = 0;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        totalBooks += 1;

    }

    //getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    //setters

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setPublicationYear(int publicationYear){
        this.publicationYear = publicationYear;
    }

    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    //toString method
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }



}


class Shelf{
    private String name;
    private Book[][] books = new Book[MAX_COLUMNS][MAX_BOOKS_PER_COLUMN];
    private static final int MAX_BOOKS_PER_COLUMN = 15;
    private static final int MAX_COLUMNS = 4;
    private static final int MAX_BOOKS = MAX_BOOKS_PER_COLUMN * MAX_COLUMNS;
    private int currentBookCount = 0;
    private ArrayList<Slot> freeSlots = new ArrayList<>();


    public Shelf(String name){
        this.name = name;
        for (int i = 0; i < MAX_COLUMNS; i++) {
            for (int j = 0; j < MAX_BOOKS_PER_COLUMN; j++) {
                freeSlots.add(new Slot(i, j));
            }
        }

    }

    public void addBook(Book book) {
        if(currentBookCount < MAX_BOOKS) {
            int column = currentBookCount / MAX_BOOKS_PER_COLUMN;
            int row = currentBookCount % MAX_BOOKS_PER_COLUMN;
            books[column][row] = book;
            currentBookCount++;
                freeSlots.remove(0); // Remove the first free slot as it's now occupied

        } else {
            System.out.println("Shelf is full. Cannot add more books. Select another shelf.");
        }
    }

    public void removeBook(int column, int row) {
        if (column >= 0 && column < MAX_COLUMNS && row >= 0 && row < MAX_BOOKS_PER_COLUMN) {

            if (books[column][row] != null) {
                books[column][row] = null;
                freeSlots.add(new Slot(column, row)); // Add the slot back to free slots
                currentBookCount--;
            } else {
                System.out.println("No book found at the specified location.");
            }
        } else {
            System.out.println("Invalid column or row index.");
        }

    }

    public void checkOutBook(int column, int row) {
        if (column >= 0 && column < MAX_COLUMNS && row >= 0 && row < MAX_BOOKS_PER_COLUMN) {
            if (books[column][row] != null && books[column][row].isAvailable()) {
                books[column][row].setIsAvailable(false);
                System.out.println("You have checked out: " + books[column][row].toString());
                removeBook(column, row); // Remove the book from the shelf after checkout


            } else {
                System.out.println("Book is not available for checkout.");
            }
        }
    }

    public int getCurrentBookCount() {
        return currentBookCount;
    }

    public void viewBooks() {
        for (int i = 0; i < MAX_COLUMNS; i++) {
            for (int j = 0; j < MAX_BOOKS_PER_COLUMN; j++) {
                if (books[i][j] != null) {
                    System.out.println("Column: " + i + ", Row: " + j + " - " + books[i][j].toString());
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book[][] getBooks() {
        return books;
    }

    public static int getMaxBooksPerColumn() {
        return MAX_BOOKS_PER_COLUMN;
    }

    public static int getMaxColumns() {
        return MAX_COLUMNS;
    }

    public static int getMaxBooks() {
        return MAX_BOOKS;
    }
    
    public int getCurrentFreeSlots() {
        return freeSlots.size();
    }


}

class Slot {
    private int row;
    private int column;

    public Slot(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}