public class LibraryDebugger {
    public LibraryDebugger() {

    }

    public void fillShelf(Shelf shelf){
        for(int i = 0; i < shelf.getMaxRows(); i++){
            for(int j = 0; j < shelf.getMaxColumns(); j++){
                shelf.addBook(new Book("Book " + (i+j), "Author", 0, "Genre"));
            }
        }
    }

    public void shelfBookInfo(Library_Sim library){
        for(Shelf shelf : library.getShelves()){
            shelf.shelfBookInfo();
        }
    }


}