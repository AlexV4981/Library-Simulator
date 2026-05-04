//Provides utility methods to automate the testing and data population of the library system.
//CLASS DEFINITION
import java.util.ArrayList;

public class LibraryDebugger {

    //Default constructor for the debugger utility.
    //CONSTRUCTOR
    public LibraryDebugger() {}

    //Programmatically fills a specific shelf to its maximum capacity using nested loops for rows and columns.
    //POPULATE SHELF
    public void fillShelf(Shelf shelf){
        for(int i = 0; i < shelf.getMaxRows(); i++){
            for(int j = 0; j < shelf.getMaxColumns(); j++){
                //Creates generic book objects based on the shelf's genre to test layout and capacity.
                shelf.addBook(new Book(shelf.getGenre()+" " + (i+j), "Author", 0, "Genre"));
            }
        }
    }

    //Iterates through all shelves in the library simulation and triggers their internal data reporting.
    //AUDIT LIBRARY BOOKS
    public void shelfBookInfo(Library_Sim library){
        for(Shelf shelf : library.getShelves()){
            //Calls the reporting method of each individual shelf.
            shelf.shelfBookInfo();
        }
    }
}