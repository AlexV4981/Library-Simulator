import java.util.ArrayList;

public class Library_Sim {
    private String name;
    private String address;

    public Library_Sim(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }




}

class Shelf {
    private String name;
    private String genre;
    private ArrayList<ArrayList<Book>> books;
    private int max_rows;
    private int max_columns;

    public Shelf(String name, String genre, int max_rows, int max_columns){
        this.name = name;
        this.genre = genre;
        this.max_rows = max_rows;
        this.max_columns = max_columns;
        this.books = new ArrayList<ArrayList<Book>>();
        for(int i = 0; i < max_rows; i++){
            this.books.add(new ArrayList<Book>());
        }

    }


    public boolean isFull(){
        int totalBooks = 0;
        for(int i = 0; i < max_rows; i++){
            if(this.books.get(i).size() < max_columns){
                totalBooks += this.books.get(i).size();
            }

        }

        return totalBooks == max_rows * max_columns;
    }

    public void addBook(Book book){
        for(int i = 0; i < max_rows; i++){
            if (this.books.get(i).size() < max_columns){
                this.books.get(i).add(book);
                break;
            }
        }
    }

    public void checkOutBook(String title){
        Book book = findBook(title);
        if(book != null && book.getIsAvailable()){
            book.checkOut();
        }
    }

    public void checkInBook(String title){
        Book book = findBook(title);
        if(book != null && !book.getIsAvailable()){
            book.checkIn();
        }
    }

    public Book findBook(String title){
        for(int i = 0; i < max_rows; i++){
            for(int j = 0; j < this.books.get(i).size(); j++){
                if(this.books.get(i).get(j).getTitle().equals(title)){
                    return this.books.get(i).get(j);
                }
            }
        }

        return null;
    }


    public String getName(){
        return this.name;
    
    }

    public String getGenre(){
        return this.genre;
    }

}

class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private boolean isAvailable;

    public Book(String title, String author, int publicationYear, String genre){
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.isAvailable = true;
    }

    public void checkOut(){
        this.isAvailable = false;
    }

    public void checkIn(){
        this.isAvailable = true;
    }


    //getters
    public boolean getIsAvailable(){
        return this.isAvailable;
    }

    public String getTitle(){
        return this.title;
    }   

    public String getAuthor(){
        return this.author;
    }

    public int getPublicationYear(){
        return this.publicationYear;
    }

    public String getGenre(){
        return this.genre;
    }



}


