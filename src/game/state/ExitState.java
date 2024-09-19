package game.state;

import game.BaseballGame;
import user.User;
import util.CustomDesign;

import java.util.Scanner;

/**
 * 게임 종료 상태를 관리하는 클래스입니다
 */
public class ExitState implements GameState{

    public static ExitState exitState;
    private ExitState(){}

    public static ExitState getInstance(){
        if(exitState==null){
            exitState = new ExitState();
        }
        return exitState;
    }

    /**
     * 게임 종료 상태를 처리합니다
     * 종료 메시지를 출력하고,
     * 야구 게임 인스턴스에 저장된 모든 기록을 삭제한 후 종료 상태로 전환합니다
     * @param baseballGame 현재의 야구 게임 인스턴스
     * @param sc 사용자 입력을 받기 위한 Scanner 객체
     */
    @Override
    public void handle(BaseballGame baseballGame, Scanner sc) {
        CustomDesign.printExitMessage();
        //기록 다 지우기
        baseballGame.clearHistory();
        //종료
        baseballGame.exit();
    }
}
