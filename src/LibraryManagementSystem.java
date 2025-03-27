import java.util.*;

class Book {
    int id;
    String title, author, category;

    // Constructor
    public Book(int id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    // Display book details
    public void display() {
        System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", Category: " + category);
    }
}

public class LibraryManagementSystem {
    private static final String DEFAULT_PASSWORD = "admin123"; // Default password
    private static String password = DEFAULT_PASSWORD; // Changeable password
    private static ArrayList<Book> books = new ArrayList<>(); // List to store books
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        // Password authentication
        System.out.print("Enter Password: ");
        String inputPassword = scanner.nextLine();
        if (!inputPassword.equals(password)) {
            System.out.println("Incorrect Password. Exiting...");
            return;
        }

        while (true) {
            // Display main menu
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Search Book");
            System.out.println("4. View All Books");
            System.out.println("5. Change Password");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    viewBooks();
                    break;
                case 5:
                    changePassword();
                    break;
                case 6:
                    System.out.println("Exiting in 3 seconds...");
                    try {
                        Thread.sleep(3000); // Wait for 3 seconds (3000 milliseconds)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Program exited successfully!");
                    System.exit(0); // Exit the program
                    break;


            }
        }
    }

    // Function to add a book
    public static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        books.add(new Book(id, title, author, category));
        System.out.println("Book added successfully!");
    }

    // Function to delete a book
    public static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.id == id) {
                iterator.remove();
                found = true;
                System.out.println("Book deleted successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found!");
        }
    }

    // Function to search for books
    public static void searchBook() {
        System.out.print("Enter search keyword (title/author/category): ");
        String keyword = scanner.nextLine();

        HashSet<Book> searchResults = new HashSet<>();
        for (Book book : books) {
            if (book.title.contains(keyword) || book.author.contains(keyword) || book.category.contains(keyword)) {
                searchResults.add(book);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No books found!");
        } else {
            System.out.println("Search Results:");
            for (Book book : searchResults) {
                book.display();
            }
        }
    }

    // Function to view all books
    public static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found!");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                book.display();
            }
        }
    }

    // Function to change the password
    public static void changePassword() {
        System.out.print("Enter old password: ");
        String oldPassword = scanner.nextLine();

        if (!oldPassword.equals(password)) {
            System.out.println("Incorrect old password!");
            return;
        }

        System.out.print("Enter new password: ");
        password = scanner.nextLine();
        System.out.println("Password changed successfully!");
    }
}
