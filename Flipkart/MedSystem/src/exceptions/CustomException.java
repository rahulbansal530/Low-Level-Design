package exceptions;

public class CustomException extends RuntimeException {
    public CustomException(String errMsg) {
        super(errMsg);
    }
}
