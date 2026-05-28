package library.listener;

import library.records.LoanRecord;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private final List<LibraryListener> listeners = new ArrayList<>();

    public void addListener(LibraryListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners(List<LoanRecord> records, int currentDay) {

        for (LibraryListener listener : listeners) {
            listener.update(records, currentDay);
        }
    }
}