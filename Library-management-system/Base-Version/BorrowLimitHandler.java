package library.handler;

/**
 * Chain link 2: ensures the borrower has not exceeded the 5-item limit.
 */
public class BorrowLimitHandler extends BorrowHandler {

    private static final int MAX_BORROW = 5;

    public BorrowLimitHandler(BorrowHandler next) {
        super(next);
    }

    @Override
    public BorrowResult handle(BorrowRequest request) {
        long currentlyBorrowed = request.getRecords().stream()
                .filter(r -> r.getBorrowerId().equals(request.getBorrowerId()))
                .count();

        if (currentlyBorrowed >= MAX_BORROW) {
            return BorrowResult.fail("Borrow limit reached. Max " + MAX_BORROW + " items at once.");
        }
        return passToNext(request);
    }
}