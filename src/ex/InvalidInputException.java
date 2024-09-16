package ex;

/**
 * 입력 값 예외에 대한 에러
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String msg) {
        super(msg);
    }
}
