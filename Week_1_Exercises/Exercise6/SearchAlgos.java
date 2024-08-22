import java.util.Comparator;

public class SearchAlgos {

    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;

    public static Book binarySearch(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);
            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "Harry Potter and The Prisenors of Askaban", "J.K. Rowling"),
            new Book(2, "Hunger Games", "Suzanne Collins"),
            new Book(3, "Pride and Prejudice", "Jane Austen"),
            new Book(4, "To Kill a Mockingbird", "Harper Lee"),
        };


        Book foundBookLinear = linearSearch(books, "Hunger Games");
        System.out.println("Linear Search Result: " + foundBookLinear);

        Book foundBookBinary = binarySearch(books, "Hunger Games");
        System.out.println("Binary Search Result: " + foundBookBinary);
    }
}
