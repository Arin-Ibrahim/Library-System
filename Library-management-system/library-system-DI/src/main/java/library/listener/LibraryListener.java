package library.listener;

import library.records.LoanRecord;

import java.util.List;

public interface LibraryListener {

    void update(List<LoanRecord> records, int currentDay);
}