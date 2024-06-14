package hackathon.exceptions;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String errMsg){
        super(errMsg);
    }
}
