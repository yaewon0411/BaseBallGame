package game.state;

import game.BaseballGame;
import user.User;
import util.CustomDesign;

import java.util.Scanner;

public class ExitState implements GameState{

    public static ExitState exitState;
    private ExitState(){}

    public static ExitState getInstance(){
        if(exitState==null){
            exitState = new ExitState();
        }
        return exitState;
    }
    @Override
    public void handle(BaseballGame baseballGame, Scanner sc) {
        CustomDesign.printExitMessage();
        //기록 다 지우기
        baseballGame.clearHistory();
        //스캐너 닫기
        sc.close();
        //종료
        baseballGame.exit();
    }
}
