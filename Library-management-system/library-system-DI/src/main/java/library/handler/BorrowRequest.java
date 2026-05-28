package library.handler;

import library.domain.LibraryItem;
import library.records.LoanRecord;

import java.util.List;

public class BorrowRequest {

    private final int itemIndex;
    private final String borrowerId;
    private final List<LibraryItem> items;
    private final List<LoanRecord> records;

    public BorrowRequest(int itemIndex,
                         String borrowerId,
                         List<LibraryItem> items,
                         List<LoanRecord> records) {

        this.itemIndex = itemIndex;
        this.borrowerId = borrowerId;
        this.items = items;
        this.records = records;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public List<LibraryItem> getItems() {
        return items;
    }

    public List<LoanRecord> getRecords() {
        return records;
    }
}