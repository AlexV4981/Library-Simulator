import javax.swing.*;

public static void main(String[] args) {
    // Use invokeLater to ensure the GUI runs on the Event Dispatch Thread
    //Defualt data generation
    Library_Sim library = new Library_Sim("City Library", "Triple T Str");

    // Setup Shelves
    Shelf fantasyShelf = new Shelf("Fantasy Shelf", "Fantasy", 3, 4);
    Shelf scienceShelf = new Shelf("Science Shelf", "Science", 6, 7);
    Shelf miscellaneousShelf = new Shelf("Miscellaneous Shelf", "Miscellaneous", 5, 5);

    Librarian headLibrarian = new Librarian("John Library","123");
    headLibrarian.addShelf(library, fantasyShelf);
    headLibrarian.addShelf(library, scienceShelf);
    headLibrarian.addShelf(library, miscellaneousShelf);

    LibraryDebugger debugger = new LibraryDebugger();
    debugger.fillShelf(fantasyShelf);
    debugger.fillShelf(scienceShelf);
    debugger.fillShelf(miscellaneousShelf);

    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            //Basic Stuffs

            // Create the frame
            PatronLogin frame = new PatronLogin(library);

            // Make it visible!
            frame.setVisible(true);
        }
    });
}