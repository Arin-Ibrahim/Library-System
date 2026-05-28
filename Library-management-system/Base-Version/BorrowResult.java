package library.handler;

/**
 * The outcome of running a BorrowRequest through the handler chain.
 */
public class BorrowResult {

    private final boolean success;
    private final String  message;

    private BorrowResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static BorrowResult ok() {
        return new BorrowResult(true, "OK");
    }

    public static BorrowResult fail(String reason) {
        return new BorrowResult(false, reason);
    }

    public boolean isSuccess() { return success; }
    public String  getMessage() { return message; }
}