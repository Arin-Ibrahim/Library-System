package library.domain;

import library.penalty.JournalFine;

public final class Journal extends LibraryItem {

    public Journal(String id, String title, String description) {
        super(id, title, description, new JournalFine());
    }
}