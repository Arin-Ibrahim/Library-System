package library.penalty;

public class TechnicalPaperFine implements Fine {

    @Override
    public int calculateFine(int lateDays) {
        return lateDays * 2;
    }
}