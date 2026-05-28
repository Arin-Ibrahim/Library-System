//Zhyar Rebwar zr22159@auis.edu.krd | Arin Ibrahim ai23019@auis.edu.krd 

package FactoryDesign.loan;

import FactoryDesign.item.LibraryItem;

public class LoanRecord {

    private static final int LOAN_PERIOD = 14;

    private final String borrowerId;
    private final LibraryItem item;         
    private final int dueDay;

    public LoanRecord(String borrowerId, LibraryItem item, int currentDay) {
        this.borrowerId = borrowerId;
        this.item = item;
        this.dueDay = currentDay + LOAN_PERIOD;
    }

    public String getBorrowerId(){
    	return borrowerId; 
    	}
    
    public LibraryItem getItem(){
    	return item; 
    	}
    
    public int getDueDay(){
    	return dueDay; 
    	}

    public int getLateDays(int today) {
        if (today > dueDay) {
            return today - dueDay;
        } else {
            return 0;
        }
    }

    public int getFine(int today) {
        return getLateDays(today) * item.getFinePerDay();
    }
}
