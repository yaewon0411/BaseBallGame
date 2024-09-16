package game.state;

import game.BaseballGame;
import game.record.GameRecord;
import game.difficulty.DifficultyMode;
import game.logic.NumberBaseballLogic;
import game.state.menu.MenuState;
import user.User;
import util.CustomDesign;

import java.util.Scanner;

public class RunState implements GameState {

    private final NumberBaseballLogic numberBaseballLogic;
    private static final String INPUT_PROMPT = " 자리 수를 입력해주세요: ";


    public RunState(DifficultyMode difficultyMode){
        this.numberBaseballLogic = new NumberBaseballLogic(difficultyMode.getLen());
    }

    @Override
    public void handle(BaseballGame baseballGame, Scanner sc) {
        GameRecord gameRecord = initialize(baseballGame.getCurrentUser());
        playGame(sc, gameRecord);
        finishGame(baseballGame, baseballGame.getCurrentUser(), gameRecord);
    }

    private void playGame(Scanner sc, GameRecord gameRecord){
        while(true){
            //입력 실패하면 재시작
            gameRecord.increaseAttemptCnt();
            if (!processUserInput(sc)) {
                continue;
            }
            //성공하면 반복문 종료
            break;
        }
    }

    private void finishGame(BaseballGame baseballGame,  User user, GameRecord gameRecord){
        user.addToGameRecordList(gameRecord);
        //다시 메뉴로 전환
        baseballGame.nextStep(MenuState.getInstance());
    }


    private GameRecord initialize(User user){
        GameRecord gameRecord = new GameRecord(user.getGameNumber(), numberBaseballLogic.getMode());
        numberBaseballLogic.generateRandomNumber();
        return gameRecord;
    }

    private boolean processUserInput(Scanner sc){
        System.out.print(CustomDesign.ANSI_PINK + numberBaseballLogic.getLen() + INPUT_PROMPT + CustomDesign.ANSI_RESET);
        String input = sc.nextLine();
        return numberBaseballLogic.validateAnswer(input);
    }

}
