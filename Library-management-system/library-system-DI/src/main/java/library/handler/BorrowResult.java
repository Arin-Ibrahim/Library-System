package library.handler;

public class BorrowResult {

    private final BorrowStatus status;
    private final String message;

    private BorrowResult(BorrowStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public static BorrowResult success() {
        return new BorrowResult(BorrowStatus.SUCCESS, "Borrow successful.");
    }

    public static BorrowResult fail(BorrowStatus status,
                                    String message) {

        return new BorrowResult(status, message);
    }

    public boolean isSuccess() {
        return status == BorrowStatus.SUCCESS;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}