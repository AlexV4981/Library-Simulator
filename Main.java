public class Main {
    public static void main(String[] args) {

        LibraryDebugger debugger = new LibraryDebugger();
        Library_Sim library = new Library_Sim("City Library", "123 Main St");
        Shelf fantasyShelf = new Shelf("Fantasy Shelf", "Fantasy", 3, 4);
        Shelf scienceShelf = new Shelf("Science Shelf", "Science", 6,7);
        Shelf miscellaneousShelf = new Shelf("Miscellaneous Shelf", "Miscellaneous", 5, 5);

        debugger.fillShelf(fantasyShelf);
        debugger.fillShelf(scienceShelf);
        debugger.fillShelf(miscellaneousShelf);



        library.addShelf(fantasyShelf);
        library.addShelf(scienceShelf);
        library.addShelf(miscellaneousShelf);

        debugger.shelfBookInfo(library);
        





    }







}
