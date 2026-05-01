public class Main {
    public static void main(String[] args) {
        Library_Sim library = new Library_Sim("City Library", "123 Main St");

        Shelf fantasyShelf = new Shelf("Fantasy Shelf", "Fantasy", 3, 4);
        Shelf scienceShelf = new Shelf("Science Shelf", "Science", 6,7);
        Shelf miscellaneousShelf = new Shelf("Miscellaneous Shelf", "Miscellaneous", 5, 5);

        //Add Shelves via Librarian
        Librarian headLibrarian = new Librarian("Alice", "Wonderland");
        library.addLibrarian(headLibrarian);
        headLibrarian.addShelf(library, fantasyShelf);
        headLibrarian.addShelf(library, scienceShelf);
        headLibrarian.addShelf(library, miscellaneousShelf);

        //Create Books
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy");
        Book book2 = new Book("A Brief History of Time", "Stephen Hawking", 1988, "Science");
        Book book3 = new Book("Jujutsu Kaisen Vol 1", "Gege Akutami", 2018, "Shounen");
        Book book4 = new Book("Mickey7", "Edward Ashton", 2022, "Sci-Fi");

        //Librarian adds books to the library (which distributes them to shelves)
        headLibrarian.addBook(library, book1);
        headLibrarian.addBook(library, book2);
        headLibrarian.addBook(library, book3);
        headLibrarian.addBook(library, book4);

        fantasyShelf.addBook(book1);
        scienceShelf.addBook(book2);
        miscellaneousShelf.addBook(book3);
        miscellaneousShelf.addBook(book4);

        //Print Info
        System.out.println("--- Library Shelves ---");
        library.shelfInfo();

        System.out.println("\n--- Fantasy Shelf Contents ---");
        fantasyShelf.shelfBookInfo();

        Patron firstPatron = new Patron("John", 5, 0.0);
        library.addPatron(firstPatron);
        // starts login screen
        // switch this to start this on the patron screen.
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LibrarianLogin(library);
        });
    }
}
