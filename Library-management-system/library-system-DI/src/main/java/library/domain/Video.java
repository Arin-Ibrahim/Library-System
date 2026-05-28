package library.domain;

import library.penalty.VideoFine;

public final class Video extends LibraryItem {

    public Video(String id, String title, String description) {
        super(id, title, description, new VideoFine());
    }
}