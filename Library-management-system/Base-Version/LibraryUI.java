package library.ui;

import com.google.inject.Inject;
import library.records.LoanRecord;
import library.service.LibraryService;

import java.util.List;
import java.util.Scanner;

/**
 * Terminal UI.  LibraryService is injected by Guice — this class
 * knows nothing about how items are stored or validated.
 */
public class LibraryUI {

    private final LibraryService service;

    @Inject
    public LibraryUI(LibraryService service) {
        this.service = service;
    }

    // -------------------------------------------------------------------------
    // Main loop
    // -------------------------------------------------------------------------

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Day " + service.getCurrentDay() + " ---");
            System.out.println("1. Show Items");
            System.out.println("2. Borrow");
            System.out.println("3. Return");
            System.out.println("4. Pass Day");
            System.out.println("0. Exit");
            System.out.print("> ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> showAllItems();
                case 2 -> borrowFlow(sc);
                case 3 -> returnFlow(sc);
                case 4 -> passDay(sc);
                case 0 -> {
                    showAllRecords();
                    System.out.println("Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // -------------------------------------------------------------------------
    // Flows
    // -------------------------------------------------------------------------

    private void showAllItems() {
        List<?> items = service.getAllItems();
        System.out.println("\nAvailable Items:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    private void borrowFlow(Scanner sc) {
        showAllItems();

        System.out.print("Choose item number: ");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Borrower ID: ");
        String borrowerId = sc.nextLine();

        // Service handles all validation; UI just prints the result
        String result = service.borrowItem(choice - 1, borrowerId);
        System.out.println(result);

        showAllRecords();
    }

    private void returnFlow(Scanner sc) {
        System.out.print("Enter Borrower ID: ");
        String borrowerId = sc.nextLine();

        List<LoanRecord> borrowerRecords = service.getRecordsForBorrower(borrowerId);
        if (borrowerRecords.isEmpty()) {
            System.out.println("No borrowed items for that ID.");
            return;
        }

        int day = service.getCurrentDay();
        System.out.println("\nItems borrowed by " + borrowerId + ":");
        for (int i = 0; i < borrowerRecords.size(); i++) {
            LoanRecord r = borrowerRecords.get(i);
            System.out.printf("  %d. %-30s | Due day %-3d | Late %dd | Fine $%d%n",
                    i + 1,
                    r.getItem().getTitle(),
                    r.getDueDay(),
                    r.getLateDays(day),
                    r.getFine(day));
        }

        System.out.print("Choose item to return: ");
        int choice = sc.nextInt();
        sc.nextLine();

        String result = service.returnItem(borrowerId, choice - 1);
        System.out.println(result);

        showAllRecords();
    }

    private void passDay(Scanner sc) {
        System.out.print("How many days to advance? ");
        int days = sc.nextInt();
        sc.nextLine();
        service.advanceDay(days);
        showAllRecords();
    }

    // -------------------------------------------------------------------------
    // Display helpers
    // -------------------------------------------------------------------------

    private void showAllRecords() {
        int day = service.getCurrentDay();
        List<LoanRecord> all = service.getAllRecords();

        System.out.println("\n[Day " + day + "] Active loans:");
        if (all.isEmpty()) {
            System.out.println("  (none)");
            return;
        }

        for (LoanRecord r : all) {
            System.out.printf("  %-10s | %-30s | Due %-3d | Late %dd | $%d%n",
                    r.getBorrowerId(),
                    r.getItem().getTitle(),
                    r.getDueDay(),
                    r.getLateDays(day),
                    r.getFine(day));
        }
    }
}