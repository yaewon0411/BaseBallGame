package game.state;

import game.BaseballGame;
import user.User;
import util.CustomDesign;

import java.util.Scanner;

public class LogoutState implements GameState{

    public static LogoutState logoutState;
    private final static String INPUT_EXCEPTION_MESSAGE = "Y 또는 N을 입력해주세요";
    private LogoutState(){

    }
    public static synchronized  LogoutState getInstance(){
        if(logoutState==null){
            logoutState = new LogoutState();
        }
        return logoutState;
    }
    @Override
    public void handle(BaseballGame baseballGame,  Scanner sc) {

        baseballGame.setCurrentUser(null);

        CustomDesign.printLogoutMessage();
        System.out.println("메인 화면으로 돌아갑니다");
        baseballGame.nextStep(StartState.getInstance());
    }
}
