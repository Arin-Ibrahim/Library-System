//Zhyar Rebwar zr22159@auis.edu.krd | Arin Ibrahim ai23019@auis.edu.krd 

package FactoryDesign.system;

import FactoryDesign.item.LibraryItem;
import FactoryDesign.listener.LibraryEvent;
import FactoryDesign.listener.LibraryListener;
import FactoryDesign.loan.LoanRecord;

public class LibrarySystem {

    private LibraryListener[] listeners = new LibraryListener[10];
    private int listenerCount = 0;

    public void addListener(LibraryListener listener) {
        if (listenerCount == listeners.length) {
            LibraryListener[] bigger = new LibraryListener[listeners.length + 10];
            System.arraycopy(listeners, 0, bigger, 0, listeners.length);
            listeners = bigger;
        }
        listeners[listenerCount++] = listener;
    }

    public void notifyListeners(LibraryEvent event, LoanRecord triggerRecord) {
        LoanRecord[] snapshot = new LoanRecord[recordCount];
        System.arraycopy(records, 0, snapshot, 0, recordCount);

        for (int i = 0; i < listenerCount; i++) {
            listeners[i].onLibraryChange(event, triggerRecord, snapshot, recordCount, currentDay);
        }
    }


    private LibraryItem[] items = new LibraryItem[100];
    private LoanRecord[] records = new LoanRecord[100];

    private int itemCount = 0;
    private int recordCount = 0;
    private int currentDay = 1;

    public void passDays(int days) {
        currentDay += days;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public LibraryItem getItem(int index) {
        return items[index];
    }

    public LoanRecord getRecord(int index) {
        return records[index];
    }

    public void removeRecord(int index) {
        for (int i = index; i < recordCount - 1; i++) {
            records[i] = records[i + 1];
        }
        records[--recordCount] = null;
    }

    private void resizeItems() {
        if (itemCount == items.length) {
            LibraryItem[] larger = new LibraryItem[items.length + 50];
            System.arraycopy(items, 0, larger, 0, items.length);
            items = larger;
        }
    }

    private void resizeRecords() {
        if (recordCount == records.length) {
            LoanRecord[] larger = new LoanRecord[records.length + 50];
            System.arraycopy(records, 0, larger, 0, records.length);
            records = larger;
        }
    }

    public void addItem(LibraryItem item) {
        resizeItems();
        items[itemCount++] = item;
        System.out.println("New item added: " + item.getItemType() + " - " + item.getTitle());
    }

    public boolean isCurrentlyBorrowed(String itemId) {
        for (int i = 0; i < recordCount; i++) {
            if (records[i].getItem().getId().equals(itemId)) return true;
        }
        return false;
    }

    public int countBorrowed(String borrowerId) {
        int count = 0;
        for (int i = 0; i < recordCount; i++) {
            if (records[i].getBorrowerId().equals(borrowerId)) count++;
        }
        return count;
    }

    public void addRecord(LoanRecord record) {
        resizeRecords();
        records[recordCount++] = record;
    }
}
