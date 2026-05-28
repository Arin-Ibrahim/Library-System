package library.domain;

import library.penalty.Fine;

public abstract class LibraryItem {

    private final String libraryId;
    private final String title;
    private final String description;
    private final Fine fine;

    protected LibraryItem(String libraryId,
                          String title,
                          String description,
                          Fine fine) {

        this.libraryId = libraryId;
        this.title = title;
        this.description = description;
        this.fine = fine;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int calculateFine(int lateDays) {
        return fine.calculateFine(lateDays);
    }

    @Override
    public String toString() {
        return libraryId + " | " + title;
    }
}