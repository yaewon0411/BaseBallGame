package ex;

/**
 * 게임 중 발생할 수 있는 초기화 관련 예외를 처리하는 클래스
 */
public class GameInitializationException extends RuntimeException{
    public GameInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
