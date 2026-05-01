public class Main {
    public static void main(String[] args) {
        //Setup Library
        Library_Sim library = new Library_Sim("City Library", "123 Main St");
        
        //Setup Shelves
        Shelf fantasyShelf = new Shelf("Fantasy Shelf", "Fantasy", 3, 4);
        Shelf scienceShelf = new Shelf("Science Shelf", "Science", 6, 7);
        Shelf miscellaneousShelf = new Shelf("Miscellaneous Shelf", "Miscellaneous", 5, 5);

        //Add Shelves via Librarian
        Librarian headLibrarian = new Librarian("Alice");
        headLibrarian.addShelf(library, fantasyShelf);
        headLibrarian.addShelf(library, scienceShelf);
        headLibrarian.addShelf(library, miscellaneousShelf);

        //Create Books
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy");
        Book book2 = new Book("A Brief History of Time", "Stephen Hawking", 1988, "Science");

        //Librarian adds books to the library (which distributes them to shelves)
        headLibrarian.addBook(library, book1);
        headLibrarian.addBook(library, book2);

        //Print Info
        System.out.println("--- Library Shelves ---");
        library.shelfInfo();
        
        System.out.println("\n--- Fantasy Shelf Contents ---");
        fantasyShelf.shelfBookInfo();
    }
}
