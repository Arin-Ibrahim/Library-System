package library.handler;

public class ItemIndexHandler extends BorrowHandler {

    public ItemIndexHandler(BorrowHandler next) {
        super(next);
    }

    @Override
    public BorrowResult handle(BorrowRequest request) {

        int index = request.getItemIndex();

        if (index < 0 || index >= request.getItems().size()) {
            return BorrowResult.fail(
                    BorrowStatus.INVALID_ITEM,
                    "Invalid item selection."
            );
        }

        return passToNext(request);
    }
}