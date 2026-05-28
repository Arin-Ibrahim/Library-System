package library.service;

import library.domain.LibraryItem;
import library.records.LoanRecord;

import java.util.List;

public interface LibraryService {

    List<LibraryItem> getAllItems();

    List<LoanRecord> getAllRecords();

    List<LoanRecord> getRecordsForBorrower(String borrowerId);

    String borrowItem(int itemIndex, String borrowerId);

    String returnItem(String borrowerId, int recordIndex);

    void advanceDay(int days);

    int getCurrentDay();
}