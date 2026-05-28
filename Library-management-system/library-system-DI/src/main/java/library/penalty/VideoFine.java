package library.penalty;

public class VideoFine implements Fine {

    @Override
    public int calculateFine(int lateDays) {
        return lateDays * 1;
    }
}