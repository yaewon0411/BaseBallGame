package ex;

/**
 * 게임 중 발생할 수 있는 입력 값 예외를 처리하는 클래스
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String msg) {
        super(msg);
    }
}
