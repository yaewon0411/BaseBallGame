package game.state;

import game.BaseballGame;
import game.state.difficulty.DifficultyMode;
import user.User;
import util.CustomDesign;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuState implements GameState {
    private static MenuState menuState;
    private Scanner sc;


    private MenuState(){
    }
    public static synchronized MenuState getInstance(){
        if(menuState ==null){
           menuState = new MenuState();
        }
        return menuState;
    }

    /**
     * 옵션 입력 검증 및 상태 변경을 관리합니다
     * 유효한 입력에 대해 야구 숫자 게임 상태를 변환합니다
     *
     * @param baseballGame 야구 숫자 게임
     */
    public void handle(BaseballGame baseballGame, User user){
        sc = new Scanner(System.in);
        int option = validMainMenuInput();
        switchStatus(option, baseballGame, user);
    }


    /**
     * 사용자 입력 값 유효성을 검증합니다
     * @return 유효한 옵션 값
     */
    public int validMainMenuInput(){
        int option = 0;
        while(true) {
            CustomDesign.printMainMenu();
            try {
                //옵션 입력
                option = sc.nextInt();

                //유효한 옵션이 아닌 경우
                if(option != 1 && option!=2 && option!=3) throw new InputMismatchException();

               break;

            } catch (InputMismatchException e) { //정수로 변환할 수 없는 경우
                System.out.println(CustomDesign.ANSI_RED + "유효한 옵션 번호를 입력해주세요" + CustomDesign.ANSI_RESET);
                continue;
            }
        }
        return option;
    }

    public void validDifficultyModeInput(BaseballGame baseballGame, User user){
        DifficultyMode difficultyMode;
        while(true) {
            CustomDesign.printDifficultyMenu();
            try {
                //옵션 입력
                int option = sc.nextInt();

                //유효한 옵션인지 확인
                difficultyMode = DifficultyMode.findByOption(option);

                break;

            } catch (InputMismatchException | IllegalArgumentException e) { //정수로 변환할 수 없는 경우
                System.out.println(CustomDesign.ANSI_RED + "유효한 옵션 번호를 입력해주세요" + CustomDesign.ANSI_RESET);
                continue;
            }
        }
        System.out.println("난이도 선택 완료");
        //난이도에 따른 게임 로직 실행
        baseballGame.nextStep(new RunState(difficultyMode));
    }

    /**
     * 옵션에 따라 야구 숫자 게임의 상태를 전환 합니다
     *
     * case 1 : 게임 시작하기 -> 난이도 설정 상태로 전환
     * case 2 : 게임 기록 보기 -> 기록 보기 상태로 전환
     * case 3 : 게임 종료 -> 종료 상태로 전환
     *
     * @param option 유효한 옵션 번호
     * @param baseballGame 야구 숫자 게임
     */
    public void switchStatus(int option, BaseballGame baseballGame, User user){
        switch(option){
            case 1: validDifficultyModeInput(baseballGame, user);break;
            case 2: baseballGame.nextStep(new RecordState()); break;
            case 3: baseballGame.nextStep(new ExitState());
        }
    }



}
