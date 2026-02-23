public class App {
    public static void main(String[] args) throws Exception {
        Library library = new Library("City Library", "123 Main St");
        Shelf fictionShelf = new Shelf("Fiction");
        Shelf nonFictionShelf = new Shelf("Non-Fiction");

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
        Book book3 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 2011);

        fictionShelf.addBook(book1);
        fictionShelf.addBook(book2);
        nonFictionShelf.addBook(book3);

        library.addShelf(nonFictionShelf);

        library.printShelf(fictionShelf);
        library.printShelf(nonFictionShelf);

        library.checkOutBookFromShelf(nonFictionShelf, 0, 0);
        library.printShelf(nonFictionShelf);


    }
}
