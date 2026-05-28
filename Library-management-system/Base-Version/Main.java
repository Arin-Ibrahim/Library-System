// Made by: Arin Ibrahim ai23019@auis.edu.krd | Zhyar Rebwar zr22159@auis.edu.krd
// the package is just part of clean code design
// package Assignment;

public class Main {
    public static void main(String[] args) {

        LibrarySystem system = new LibrarySystem();

        // Add sample items this way its flexible and we can easily change the items without changing the code of the system
        system.addItem(new LibraryItem("L1", "Java Book", "Programming", 3));
        system.addItem(new LibraryItem("L2", "Python Video", "bro code here...", 1));
        system.addItem(new LibraryItem("L3", "Science Journal", "daily dose of sciense", 3));
        system.addItem(new LibraryItem("L4", "Technical Paper", "tech paper for uni studends idk", 2));

        // Run the terminal - check the LibrarySystem class for more details on how it works - this is so we have a clean main.
        system.run();
    }
}