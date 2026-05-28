package library.records;

import library.domain.LibraryItem;

public final class LoanRecord {

    private final String borrowerId;
    private final LibraryItem item;
    private final int borrowDay;
    private final int dueDay;

    public LoanRecord(String borrowerId,
                      LibraryItem item,
                      int borrowDay,
                      int dueDay) {

        this.borrowerId = borrowerId;
        this.item = item;
        this.borrowDay = borrowDay;
        this.dueDay = dueDay;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public LibraryItem getItem() {
        return item;
    }

    public int getBorrowDay() {
        return borrowDay;
    }

    public int getDueDay() {
        return dueDay;
    }

    public int getLateDays(int currentDay) {
        return Math.max(0, currentDay - dueDay);
    }

    public int getFine(int currentDay) {
        return item.calculateFine(getLateDays(currentDay));
    }
}