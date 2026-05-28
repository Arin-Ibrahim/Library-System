package library.handler;

/**
 * DESIGN PATTERN: Chain of Responsibility
 * Additional pattern (not covered in SE421).
 *
 * Each BorrowHandler validates one rule before a borrow is allowed.
 * Handlers are linked at startup; if a handler passes, it forwards
 * the request to the next link in the chain.
 *
 * This decouples each validation rule from the service layer and makes
 * it trivial to add, remove, or reorder rules without touching
 * LibraryServiceImpl.
 */
public abstract class BorrowHandler {

    protected final BorrowHandler next;

    protected BorrowHandler(BorrowHandler next) {
        this.next = next;
    }

    /**
     * Validate the request.  If the rule passes, forward to the next handler.
     * If the rule fails, return a BorrowResult.fail(...) immediately.
     */
    public abstract BorrowResult handle(BorrowRequest request);

    /** Helper: pass the request to the next handler (or approve if chain ends). */
    protected BorrowResult passToNext(BorrowRequest request) {
        if (next == null) return BorrowResult.ok();
        return next.handle(request);
    }
}