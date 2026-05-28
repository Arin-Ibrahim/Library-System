//Zhyar Rebwar zr22159@auis.edu.krd | Arin Ibrahim ai23019@auis.edu.krd 

package FactoryDesign.system;

import java.util.Scanner;

import FactoryDesign.factory.LibraryItemFactory;
import FactoryDesign.item.LibraryItem;
import FactoryDesign.listener.LibraryEvent;
import FactoryDesign.listener.LoanTablePrinter;
import FactoryDesign.loan.LoanRecord;

public class LibraryProcesses {

    private final LibrarySystem system;

    public LibraryProcesses() {
        this.system = new LibrarySystem();
        system.addListener(new LoanTablePrinter());
        loadItems();
    }

    private void loadItems() {
        system.addItem(LibraryItemFactory.create("Book", "L001", "Coding", "chapter-2"));
        system.addItem(LibraryItemFactory.create("Video", "L002", "Java", "Youtube video"));
        system.addItem(LibraryItemFactory.create("Journal", "L003", "Life science", "Human cells"));
        system.addItem(LibraryItemFactory.create("TechnicalPaper", "L004", "AI", "Tech paper on AI"));
        system.addItem(LibraryItemFactory.create("Book", "L005", "Design Patterns", "Factory Design Pattern"));
        system.addItem(LibraryItemFactory.create("Journal", "L006", "Software", "Software engineering research"));
    }

    private void showAllItems() {
        System.out.println("# | ID   | Type  | Title       | Status\n");
        

        for (int i = 0; i < system.getItemCount(); i++) {
            LibraryItem item = system.getItem(i);
            String status = system.isCurrentlyBorrowed(item.getId()) ? "Borrowed" : "Available";
            System.out.println((i + 1) + " | " + item.getId() + " | " + item.getItemType() + " | " + item.getTitle() + " | " + status);
        }
    }

    private void borrowFlow(Scanner sc) {
        showAllItems();
        System.out.print("\nChoose item number to borrow: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > system.getItemCount()) {
            System.out.println("Invalid choice.");
            return;
        }

        LibraryItem item = system.getItem(choice - 1);

        if (system.isCurrentlyBorrowed(item.getId())) {
            System.out.println("Sorry - \"" + item.getTitle() + "\" is currently on loan.");
            return;
        }

        System.out.print("Enter AUIS borrower ID: ");
        String borrowerId = sc.nextLine().trim();

        if (system.countBorrowed(borrowerId) >= 5) {
            System.out.println("Limit reached: " + borrowerId + " already borrowed 5 items.");
            return;
        }

        LoanRecord newRecord = new LoanRecord(borrowerId, item, system.getCurrentDay());
        system.addRecord(newRecord);

        system.notifyListeners(LibraryEvent.ITEM_BORROWED, newRecord);
    }

    private void returnFlow(Scanner sc) {
        System.out.print("Enter AUIS Borrower ID: ");
        String borrowerId = sc.nextLine().trim();

        int[] indexes = new int[system.getRecordCount()];
        int count = 0;
        for (int i = 0; i < system.getRecordCount(); i++) {
            if (system.getRecord(i).getBorrowerId().equals(borrowerId)) {
                indexes[count++] = i;
            }
        }

        if (count == 0) {
            System.out.println("No active loans found for ID: " + borrowerId);
            return;
        }

        System.out.println();
        System.out.println("# | Type   | Title      | Due Day | Fine");


        for (int i = 0; i < count; i++) {
            LoanRecord rec = system.getRecord(indexes[i]);
            System.out.println((i + 1) + " | " + rec.getItem().getItemType() + " | " + rec.getItem().getTitle() + " | " + rec.getDueDay() + " | $" + rec.getFine(system.getCurrentDay()));
        }

        System.out.print("\nChoose item to return: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > count) {
            System.out.println("Invalid choice.");
            return;
        }

        int realIndex = indexes[choice - 1];
        LoanRecord rec = system.getRecord(realIndex);

        system.removeRecord(realIndex);

        system.notifyListeners(LibraryEvent.ITEM_RETURNED, rec);
    }

    public void run() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Show items");
            System.out.println("2. Borrow");
            System.out.println("3. Return");
            System.out.println("4. Pass day");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                showAllItems();

            } else if (choice == 2) {
                borrowFlow(input);

            } else if (choice == 3) {
                returnFlow(input);

            } else if (choice == 4) {
                System.out.print("How many days do you want to pass? ");
                int days = input.nextInt();
                input.nextLine();
                system.passDays(days);

            } else if (choice == 0) {
                System.out.println("Goodbye.");
                return;

            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
