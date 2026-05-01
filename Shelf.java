import java.util.ArrayList;

public class Shelf {
    private String name; // [cite: 15]
    private String genre; // [cite: 17]
    private ArrayList<ArrayList<Book>> books; // [cite: 19]
    private int max_rows; // [cite: 20]
    private int max_columns; // [cite: 21]

    public Shelf(String name, String genre, int max_rows, int max_columns) { // [cite: 22, 29]
        this.name = name;
        this.genre = genre;
        this.max_rows = max_rows;
        this.max_columns = max_columns;
        this.books = new ArrayList<ArrayList<Book>>();
        
        for(int i = 0; i < max_rows; i++) {
            this.books.add(new ArrayList<Book>());
        }
    }

    public boolean isFull() { // [cite: 31]
        int totalBooks = 0;
        for(int i = 0; i < max_rows; i++) {
            if(this.books.get(i).size() < max_columns) {
                totalBooks += this.books.get(i).size();
            }
        }
        return totalBooks == max_rows * max_columns;
    }

    public void addBook(Book book) { // [cite: 33]
        for(int i = 0; i < max_rows; i++) {
            if (this.books.get(i).size() < max_columns) {
                this.books.get(i).add(book);
                break;
            }
        }
    }

    public void checkOutBook(String title) { // [cite: 35]
        Book book = findBook(title);
        if(book != null && book.getIsAvailable()) {
            book.checkOut();
        }
    }

    public void checkInBook(String title) { // [cite: 37]
        Book book = findBook(title);
        if(book != null && !book.getIsAvailable()) {
            book.checkIn();
        }
    }

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

    public void shelfBookInfo() { // [cite: 41]
        for(int i = 0; i < max_rows; i++) {
            for(int j = 0; j < this.books.get(i).size(); j++) {
                System.out.println(this.books.get(i).get(j).toString());
            }
        }
    }

    @Override
    public String toString() { // [cite: 32]
        return "Shelf name: " + this.name + "\nGenre: " + this.genre;
    }

    public String getName() { // [cite: 45]
        return this.name;
    }

    public String getGenre() { // [cite: 46]
        return this.genre;
    }

    public int getMaxRows() { // [cite: 48]
        return this.max_rows;
    }

    public int getMaxColumns() { // [cite: 49]
        return this.max_columns;
    }
}