//Zhyar Rebwar zr22159@auis.edu.krd | Arin Ibrahim ai23019@auis.edu.krd 

package FactoryDesign.listener;

import FactoryDesign.loan.LoanRecord;

public interface LibraryListener {

   public void onLibraryChange(LibraryEvent event, LoanRecord record, LoanRecord[] allRecords, int recordCount, int currentDay);
}
