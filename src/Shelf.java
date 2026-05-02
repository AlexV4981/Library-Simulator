import java.util.ArrayList;

public class Shelf {
    private String name;
    private String genre;
    private ArrayList<ArrayList<Book>> books;
    private int max_rows;
    private int max_columns;

    public Shelf(String name, String genre, int max_rows, int max_columns) {
        this.name = name;
        this.genre = genre;
        this.max_rows = max_rows;
        this.max_columns = max_columns;
        this.books = new ArrayList<ArrayList<Book>>();

        for(int i = 0; i < max_rows; i++) {
            this.books.add(new ArrayList<Book>());
        }
    }

    public boolean isFull() {
        int totalBooks = 0;
        for(int i = 0; i < max_rows; i++) {
            if(this.books.get(i).size() < max_columns) {
                totalBooks += this.books.get(i).size();
            }
        }
        return totalBooks == max_rows * max_columns;
    }

    public void addBook(Book book) {
        for(int i = 0; i < max_rows; i++) {
            if (this.books.get(i).size() < max_columns) {
                this.books.get(i).add(book);
                break;
            }
        }
    }

    public void checkOutBook(String title) {
        Book book = findBook(title);
        if(book != null && book.getIsAvailable()) {
            book.checkOut();
        }
    }

    public void checkInBook(String title) {
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

    public void shelfBookInfo() {
        for(int i = 0; i < max_rows; i++) {
            for(int j = 0; j < this.books.get(i).size(); j++) {
                System.out.println(this.books.get(i).get(j).toString());
            }
        }
    }

    @Override
    public String toString() {
        return "Shelf name: " + this.name + "\nGenre: " + this.genre;
    }

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

    public ArrayList<Book> getBooks() {
        ArrayList<Book> flat = new ArrayList<>();
        for (int row = 0; row < books.size(); row++) {
            ArrayList<Book> rowList = books.get(row);
            for (int col = 0; col < rowList.size(); col++) {
                Book b = rowList.get(col);
                if (b != null) {
                    flat.add(b);
                }
            }
        }

        return flat;
    }


}