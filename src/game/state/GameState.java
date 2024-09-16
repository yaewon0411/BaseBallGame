package game.state;

import game.BaseballGame;
import user.User;

import java.util.Scanner;
/**
 * GameState 인터페이스는 게임의 각 상태를 표현합니다
 * 각 상태는 이 인터페이스를 구현하여 고유 동작을 정의합니다
 */
public interface GameState {
    /**
     * 현재 게임 상태에 대한 처리를 수행합니다
     *
     * @param baseballGame 현재의 야구 게임 인스턴스
     * @param sc 사용자 입력을 받기 위한 Scanner 객체
     */
    void handle(BaseballGame baseballGame, Scanner sc);
}
