//Zhyar Rebwar zr22159@auis.edu.krd | Arin Ibrahim ai23019@auis.edu.krd 

package FactoryDesign.listener;

import FactoryDesign.loan.LoanRecord;

public class LoanTablePrinter implements LibraryListener {

    @Override
    public void onLibraryChange(LibraryEvent event, LoanRecord record, LoanRecord[] allRecords, int recordCount, int currentDay) {

        System.out.println();
        if (event == LibraryEvent.ITEM_BORROWED) {
        	
            System.out.println("Item borrowed: \"" + record.getItem().getTitle() + "\" by " + record.getBorrowerId() + " - due on day " + record.getDueDay());
            
        } else if (event == LibraryEvent.ITEM_RETURNED) {
        	
            int fine = record.getFine(currentDay);
            System.out.println("Returned: \"" + record.getItem().getTitle() + "\"");
            
            if (fine > 0) {
                System.out.println("Overdue fine: $" + fine + " (" + record.getLateDays(currentDay) + " days late x $" + record.getItem().getFinePerDay() + "/day)");
            } else {
                System.out.println("No fine - returned on time.");
            }
        }

        printTable(allRecords, recordCount, currentDay);
    }

    private void printTable(LoanRecord[] allRecords, int recordCount, int currentDay) {
        System.out.println("\nCurrent Loans - Day " + currentDay);

        if (recordCount == 0) {
            System.out.println("No items currently on loan.");
            return;
        }

        System.out.println("Borrower ID  | Type    | Title       | Due Day | Late Days | Fine");


        for (int i = 0; i < recordCount; i++) {
            LoanRecord rec = allRecords[i];
            int late = rec.getLateDays(currentDay);
            int fine = rec.getFine(currentDay);
            String lateStr = late > 0 ? late + " day" : "On time";

            System.out.println(rec.getBorrowerId() + "  | " + rec.getItem().getItemType() + "  | " + rec.getItem().getTitle() + "  | " + rec.getDueDay() + "  | " + lateStr + "  | $" + fine);
        }
    }
}
