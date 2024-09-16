package game.state;

import game.BaseballGame;
import game.record.GameRecord;
import game.state.difficulty.DifficultyMode;
import game.state.difficulty.DifficultyState;
import game.state.menu.MenuState;
import user.User;
import util.CustomDesign;

import java.util.Scanner;

public class RunState implements GameState {

    private final DifficultyState difficultyState;
    public RunState(DifficultyMode difficultyMode){
        this.difficultyState = new DifficultyState(difficultyMode.getLen());
    }
    private static final String INPUT_PROMPT = " 자리 수를 입력해주세요: ";

    @Override
    public void handle(BaseballGame baseballGame, User user, Scanner sc) {

        GameRecord gameRecord = initialize(user);
        playGame(sc, gameRecord);
        finishGame(baseballGame, user, gameRecord);

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
        GameRecord gameRecord = new GameRecord(user.getGameNumber(), difficultyState.getMode());
        difficultyState.generateRandomNumber();
        return gameRecord;
    }

    private boolean processUserInput(Scanner sc){
        System.out.print(CustomDesign.ANSI_PINK + difficultyState.getLen() + INPUT_PROMPT + CustomDesign.ANSI_RESET);
        String input = sc.nextLine();
        return difficultyState.validateAnswer(input);
    }

}
