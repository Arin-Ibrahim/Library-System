package library.listener;

import library.records.LoanRecord;

import java.util.List;

public class LogListener implements LibraryListener {

    @Override
    public void update(List<LoanRecord> records, int currentDay) {

        System.out.println("\n=> Active Loans <=");

        if (records.isEmpty()) {
            System.out.println("(none)");
            return;
        }

        for (LoanRecord r : records) {
            System.out.printf(
                    "%-10s | %-25s | Due %-3d | Fine $%d%n",
                    r.getBorrowerId(),
                    r.getItem().getTitle(),
                    r.getDueDay(),
                    r.getFine(currentDay)
            );
        }
    }
}