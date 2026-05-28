package library.handler;

public abstract class BorrowHandler {

    protected final BorrowHandler next;

    protected BorrowHandler(BorrowHandler next) {
        this.next = next;
    }

    public abstract BorrowResult handle(BorrowRequest request);

    protected BorrowResult passToNext(BorrowRequest request) {
        if (next == null) {
            return BorrowResult.success();
        }

        return next.handle(request);
    }
}