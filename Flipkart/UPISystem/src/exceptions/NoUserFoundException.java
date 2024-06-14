package Flipkart.UPISystem.src.exceptions;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String errMsg){
        super(errMsg);
    }
}
