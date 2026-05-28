package library.domain;

import library.penalty.TechnicalPaperFine;

public final class TechnicalPaper extends LibraryItem {

    public TechnicalPaper(String id, String title, String description) {
        super(id, title, description, new TechnicalPaperFine());
    }
}