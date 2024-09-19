package game.state;

import ex.InvalidInputException;
import game.BaseballGame;
import game.record.GameRecord;
import game.difficulty.DifficultyMode;
import game.logic.NumberBaseballLogic;
import game.state.menu.MenuState;
import user.User;
import util.CustomDesign;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 숫자 야구 게임의 실행 상태를 관리하는 클래스입니다
 * NumberBaseballLogic을 사용해 게임의 실제 플레이 로직을 처리합니다
 */
public class RunState implements GameState {

    private final NumberBaseballLogic numberBaseballLogic;
    private static final String INPUT_PROMPT = " 자리 수를 입력해주세요: ";

    /**
     * RunState의 생성자입니다
     *
     * @param difficultyMode 게임의 난이도 모드
     */
    public RunState(DifficultyMode difficultyMode){
        this.numberBaseballLogic = new NumberBaseballLogic(difficultyMode.getLen());
    }

    /**
     * 게임을 실행하고 처리합니다
     * 게임을 초기화, 플레이, 종료 과정을 관리합니다
     *
     * @param baseballGame 숫자 야구 게임 객체
     * @param sc Scanner 객체
     */
    @Override
    public void handle(BaseballGame baseballGame, Scanner sc) {
        GameRecord gameRecord = initialize(baseballGame.getCurrentUser());
        playGame(sc, gameRecord);
        finishGame(baseballGame, baseballGame.getCurrentUser(), gameRecord);
    }

    /**
     * 실제 게임 플레이를 처리합니다
     * 사용자 입력을 받고 정답을 맞출 때까지 반복합니다
     *
     * @param sc Scanner 객체
     * @param gameRecord 현재 게임의 기록 객체
     */
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

    /**
     * 게임을 종료하고 결과를 처리합니다
     *
     * @param baseballGame 숫자 야구 게임 객체
     * @param user 현재 사용자
     * @param gameRecord 완료된 게임의 기록 객체
     */
    private void finishGame(BaseballGame baseballGame,  User user, GameRecord gameRecord){
        user.addToGameRecordList(gameRecord);
        //다시 메뉴로 전환
        baseballGame.nextStep(MenuState.getInstance());
    }

    /**
     * 새로운 GameRecord 객체를 생성하고 랜덤 숫자를 생성합니다
     *
     * @param user 현재 사용자
     * @return 초기화된 GameRecord 객체
     */
    private GameRecord initialize(User user){
        try {
            GameRecord gameRecord = new GameRecord(user.getGameNumber(), numberBaseballLogic.getMode());
            numberBaseballLogic.generateRandomNumber();
            return gameRecord;
        }catch(NoSuchElementException e){
            CustomDesign.printExceptionMessage(e.getMessage());
            return null;
        }
    }


    /**
     * 사용자 입력을 처리하고 검증합니다
     *
     * @param sc Scanner 객체
     * @return 정답을 맞췄으면 true, 그렇지 않으면 false
     */
    private boolean processUserInput(Scanner sc){
        System.out.print(CustomDesign.ANSI_PINK + numberBaseballLogic.getLen() + INPUT_PROMPT + CustomDesign.ANSI_RESET);
        String input = sc.nextLine();
        return numberBaseballLogic.validateAnswer(input);
    }

}
