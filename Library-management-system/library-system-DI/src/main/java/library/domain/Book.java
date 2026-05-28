package library.domain;

import library.penalty.BookFine;

public final class Book extends LibraryItem {

    public Book(String id, String title, String description) {
        super(id, title, description, new BookFine());
    }
}