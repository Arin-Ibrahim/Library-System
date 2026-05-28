package library.listener;

import library.records.LoanRecord;

import java.util.List;

public class FineAlertListener implements LibraryListener {

    private final int threshold;

    public FineAlertListener(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void update(List<LoanRecord> records, int currentDay) {

        for (LoanRecord r : records) {

            if (r.getFine(currentDay) > threshold) {
                System.out.println(
                        "ALERT: " +
                        r.getBorrowerId() +
                        " exceeded fine threshold."
                );
            }
        }
    }
}