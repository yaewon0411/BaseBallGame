package game.state.difficulty;

import game.BaseballGame;
import game.state.GameState;
import util.CustomDesign;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DifficultyState implements GameState {

    private static DifficultyState difficultyState;

    private DifficultyState(){

    }
    public static synchronized DifficultyState getInstance(){
        if(difficultyState== null){
            difficultyState = new DifficultyState();
        }
        return difficultyState;
    }

    private Scanner sc;
    @Override
    public void handle(BaseballGame baseballGame) {
        sc = new Scanner(System.in);

    }
    /**
     * 사용자 입력 값 유효성을 검증합니다
     * @return 유효한 옵션 값
     */
    public int validInput(){
        while(true) {
            CustomDesign.printDifficultyMenu();
            try {
                //옵션 입력
                int option = sc.nextInt();

                //유효한 옵션이 아닌 경우
                if(option != 1 && option!=2 && option!=3) throw new InputMismatchException();

                return option;

            } catch (InputMismatchException e) { //정수로 변환할 수 없는 경우
                System.out.println(CustomDesign.ANSI_RED + "유효한 옵션 번호를 입력해주세요" + CustomDesign.ANSI_RESET);
                continue;
            }
        }
    }
}
