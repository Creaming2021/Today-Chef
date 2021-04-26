package creaming.exception;

public class RedirectException extends RuntimeException {

    private final RedirectCode redirectCode;

    public RedirectException(RedirectCode redirectCode) {
        this.redirectCode = redirectCode;
    }

    public RedirectException(final String message, RedirectCode redirectCode) {
        super(message);
        this.redirectCode = redirectCode;
    }

    public RedirectCode getRedirectCode() {
        return redirectCode;
    }
}
