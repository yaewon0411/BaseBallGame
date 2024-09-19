package game.state;

import game.BaseballGame;
import util.CustomDesign;

import java.util.Scanner;

/**
 * 사용자 로그아웃 상태를 관리하는 클래스입니다
 * 로그아웃 프로세스를 처리하고 게임을 시작 상태(StartState)로 전환하는 역할을 합니다
 */
public class LogoutState implements GameState{

    public static LogoutState logoutState;
    private LogoutState(){

    }
    public static synchronized  LogoutState getInstance(){
        if(logoutState==null){
            logoutState = new LogoutState();
        }
        return logoutState;
    }
    /**
     * 로그아웃 프로세스를 처리합니다
     * 현재 사용자를 null로 설정하고, 로그아웃 메시지를 출력한 후 게임을 시작 상태로 전환합니다
     *
     * @param baseballGame 현재 진행 중인 야구 게임 인스턴스
     * @param sc 사용자 입력을 받기 위한 Scanner 객체 (여기서는 사용되지 않음)
     */
    @Override
    public void handle(BaseballGame baseballGame,  Scanner sc) {

        baseballGame.setCurrentUser(null);

        CustomDesign.printLogoutMessage();
        System.out.println("메인 화면으로 돌아갑니다");
        baseballGame.nextStep(StartState.getInstance());
    }
}
