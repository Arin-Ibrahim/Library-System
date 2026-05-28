package library.handler;

import library.records.LoanRecord;

public class BorrowLimitHandler extends BorrowHandler {

    private static final int MAX_BORROW = 5;

    public BorrowLimitHandler(BorrowHandler next) {
        super(next);
    }

    @Override
    public BorrowResult handle(BorrowRequest request) {

        int count = 0;

        for (LoanRecord r : request.getRecords()) {
            if (r.getBorrowerId().equals(request.getBorrowerId())) {
                count++;
            }
        }

        if (count >= MAX_BORROW) {
            return BorrowResult.fail(
                    BorrowStatus.LIMIT_REACHED,
                    "Borrow limit reached."
            );
        }

        return passToNext(request);
    }
}
