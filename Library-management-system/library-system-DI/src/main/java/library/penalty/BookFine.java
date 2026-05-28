package library.penalty;

public class BookFine implements Fine {

    @Override
    public int calculateFine(int lateDays) {
        return lateDays * 3;
    }
}