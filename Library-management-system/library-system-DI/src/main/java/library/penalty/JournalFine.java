package library.penalty;

public class JournalFine implements Fine {

    @Override
    public int calculateFine(int lateDays) {
        return lateDays * 3;
    }
}