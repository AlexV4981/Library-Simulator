public class Main {
    public static void main(String[] args) {
        // 1. Initialize Library and Shelves
        Library_Sim library = new Library_Sim("City Library", "123 Main St");

        Shelf fantasyShelf = new Shelf("Fantasy Shelf", "Fantasy", 3, 4);
        Shelf scienceShelf = new Shelf("Science Shelf", "Science", 6, 7);
        Shelf miscellaneousShelf = new Shelf("Miscellaneous Shelf", "Miscellaneous", 5, 5);

        // 2. Setup Librarian and Add Shelves
        Librarian headLibrarian = new Librarian("Alice", "Wonderland");
        library.addLibrarian(headLibrarian);
        headLibrarian.addShelf(library, fantasyShelf);
        headLibrarian.addShelf(library, scienceShelf);
        headLibrarian.addShelf(library, miscellaneousShelf);

        // 3. Create and Add Books
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy");
        Book book2 = new Book("A Brief History of Time", "Stephen Hawking", 1988, "Science");
        Book book3 = new Book("Jujutsu Kaisen Vol 1", "Gege Akutami", 2018, "Shounen");
        Book book4 = new Book("Mickey7", "Edward Ashton", 2022, "Sci-Fi");

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

        // 4. Setup initial Patron
        Patron firstPatron = new Patron("John", "Doe", 5, 0.0);
        library.addPatron(firstPatron);

        // 5. Console Debug Info (Optional)
        System.out.println("--- Library Initialized ---");
        library.shelfInfo();

        // 6. Launch Patron Login Screen First
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Changed from LibrarianLogin to PatronLogin
            PatronLogin patron = new PatronLogin(library);
            library.addPatron(patron.getPatron());
            patron.updateLibrary(library);
            patron.setVisible(true);
        });
    }
}
