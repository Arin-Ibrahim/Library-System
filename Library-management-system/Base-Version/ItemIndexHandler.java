package library.handler;

/**
 * Chain link 1: ensures the requested item index is valid.
 */
public class ItemIndexHandler extends BorrowHandler {

    public ItemIndexHandler(BorrowHandler next) {
        super(next);
    }

    @Override
    public BorrowResult handle(BorrowRequest request) {
        int index = request.getItemIndex();
        if (index < 0 || index >= request.getItems().size()) {
            return BorrowResult.fail("Invalid item selection.");
        }
        return passToNext(request);
    }
}