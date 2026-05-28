//Zhyar Rebwar zr22159@auis.edu.krd | Arin Ibrahim ai23019@auis.edu.krd 

package FactoryDesign.factory;

import FactoryDesign.item.*;

public class LibraryItemFactory {

    private LibraryItemFactory() {}

    public static LibraryItem create(String type, String id, String title, String description) {
//Reference: 
// (Trim and lower-case), so minor typos don't crash the system, asked google and read about on w3Schools website

        String t = type.trim().toLowerCase();

        if (t.equals("book")) {
            return new Book(id, title, description);

        } else if (t.equals("journal")) {
            return new Journal(id, title, description);

        } else if (t.equals("video")) {
            return new Video(id, title, description);

        } else if (t.equals("technicalpaper") || t.equals("technical paper")) {
            return new TechnicalPaper(id, title, description);

        } else {
            throw new IllegalArgumentException(
                "Unknown library item type: \"" + type + "\". " + "Valid types are: Book, Journal, Video, TechnicalPaper.");
        }
    }

}
