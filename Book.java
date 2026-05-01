public class Book {
    private String title; // [cite: 16]
    private String author; // [cite: 18]
    private int publicationYear; // [cite: 25]
    private String genre; // [cite: 26]
    private boolean isAvailable; // [cite: 27]

    public Book(String title, String author, int publicationYear, String genre) { // [cite: 28, 30]
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.isAvailable = true;
    }

    @Override
    public String toString() { // [cite: 43]
        return "Title: " + this.title + "\nAuthor: " + this.author + 
               "\nPublication Year: " + this.publicationYear + 
               "\nGenre: " + this.genre + "\nAvailable: " + this.isAvailable + 
               "\n___________________________________________________________________________________";
    }

    public void checkOut() { // [cite: 34]
        this.isAvailable = false;
    }

    public void checkIn() { // [cite: 36]
        this.isAvailable = true;
    }

    // Getters
    public boolean getIsAvailable() { // [cite: 38]
        return this.isAvailable;
    }

    public String getTitle() { // [cite: 40]
        return this.title;
    }   

    public String getAuthor() { // [cite: 42]
        return this.author;
    }

    public int getPublicationYear() { // [cite: 44]
        return this.publicationYear;
    }

    public String getGenre() { // [cite: 47]
        return this.genre;
    }
}