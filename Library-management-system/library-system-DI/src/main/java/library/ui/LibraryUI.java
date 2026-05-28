package library.ui;

import com.google.inject.Inject;
import library.records.LoanRecord;
import library.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class LibraryUI {

    private final LibraryService service;

    @Inject
    public LibraryUI(LibraryService service) {
        this.service = service;
    }

    public void run() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n-> Day " + service.getCurrentDay() + " <-");
            System.out.println("1. Show Items");
            System.out.println("2. Borrow");
            System.out.println("3. Return");
            System.out.println("4. Pass Day");
            System.out.println("0. Exit");
            System.out.print("> ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                showAllItems();

            } else if (choice == 2) {
                borrowFlow(sc);

            } else if (choice == 3) {
                returnFlow(sc);

            } else if (choice == 4) {
                passDay(sc);

            } else if (choice == 0) {
                System.out.println("Goodbye.");
                return;

            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private void showAllItems() {

        List<?> items = service.getAllItems();

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

        String result = service.borrowItem(choice - 1, borrowerId);
        System.out.println(result);
    }

    private void returnFlow(Scanner sc) {

        System.out.print("Enter Borrower ID: ");
        String borrowerId = sc.nextLine();

        List<LoanRecord> borrowerRecords = service.getRecordsForBorrower(borrowerId);

        if (borrowerRecords.isEmpty()) {
            System.out.println("No borrowed items.");
            return;
        }

        for (int i = 0; i < borrowerRecords.size(); i++) {

            LoanRecord r = borrowerRecords.get(i);

            System.out.printf(
                    "%d. %s | Due %d | Fine $%d%n",
                    i + 1,
                    r.getItem().getTitle(),
                    r.getDueDay(),
                    r.getFine(service.getCurrentDay())
            );
        }

        System.out.print("Choose item to return: ");
        int choice = sc.nextInt();
        sc.nextLine();

        String result = service.returnItem(borrowerId, choice - 1);
        System.out.println(result);
    }

    private void passDay(Scanner sc) {

        System.out.print("Advance how many days? ");

        int days = sc.nextInt();
        sc.nextLine();

        service.advanceDay(days);
    }
}
