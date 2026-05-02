public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private boolean isAvailable;

    public Book(String title, String author, int publicationYear, String genre) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\nAuthor: " + this.author +
                "\nPublication Year: " + this.publicationYear +
                "\nGenre: " + this.genre + "\nAvailable: " + this.isAvailable +
                "\n___________________________________________________________________________________";
    }

    public void checkOut() {
        this.isAvailable = false;
    }

    public void checkIn() {
        this.isAvailable = true;
    }

    // Getters
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