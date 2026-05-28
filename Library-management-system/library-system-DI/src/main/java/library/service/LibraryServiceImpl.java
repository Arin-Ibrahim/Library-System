package library.service;

import com.google.inject.Inject;
import library.domain.*;
import library.handler.*;
import library.listener.EventManager;
import library.records.LoanRecord;

import java.util.ArrayList;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private final List<LibraryItem> items = new ArrayList<>();
    private final List<LoanRecord> records = new ArrayList<>();

    private int currentDay = 1;

    private final BorrowHandler borrowHandler;
    private final EventManager eventManager;

    @Inject
    public LibraryServiceImpl(BorrowHandler borrowHandler,
                              EventManager eventManager) {

        this.borrowHandler = borrowHandler;
        this.eventManager = eventManager;

        seedItems();
    }

    private void seedItems() {

        items.add(new Book("Book", "Java", "OOP Book"));
        items.add(new Video("Video", "Design Patterns", "Pattern video"));
        items.add(new Journal("Journal", "Software Journal", "Research"));
        items.add(new TechnicalPaper("TechnicalPaper", "AI Paper", "Technical paper"));
    }

    @Override
    public List<LibraryItem> getAllItems() {
        return items;
    }

    @Override
    public List<LoanRecord> getAllRecords() {
        return records;
    }

    @Override
    public List<LoanRecord> getRecordsForBorrower(String borrowerId) {

        List<LoanRecord> result = new ArrayList<>();

        for (LoanRecord r : records) {
            if (r.getBorrowerId().equals(borrowerId)) {
                result.add(r);
            }
        }

        return result;
    }

    @Override
    public String borrowItem(int itemIndex, String borrowerId) {

        BorrowRequest request =
                new BorrowRequest(itemIndex, borrowerId, items, records);

        BorrowResult result = borrowHandler.handle(request);

        if (!result.isSuccess()) {
            return result.getMessage();
        }

        LibraryItem item = items.get(itemIndex);

        LoanRecord record =
                new LoanRecord(borrowerId, item, currentDay, currentDay + 14);

        records.add(record);

        eventManager.notifyListeners(records, currentDay);

        return "Borrow successful.";
    }

    @Override
    public String returnItem(String borrowerId, int recordIndex) {

        List<LoanRecord> borrowerRecords =
                getRecordsForBorrower(borrowerId);

        if (recordIndex < 0 || recordIndex >= borrowerRecords.size()) {
            return "Invalid selection.";
        }

        LoanRecord record = borrowerRecords.get(recordIndex);

        int fine = record.getFine(currentDay);

        records.remove(record);

        eventManager.notifyListeners(records, currentDay);

        return "Returned successfully. Fine: $" + fine;
    }

    @Override
    public void advanceDay(int days) {
        currentDay += days;
        eventManager.notifyListeners(records, currentDay);
    }

    @Override
    public int getCurrentDay() {
        return currentDay;
    }
}