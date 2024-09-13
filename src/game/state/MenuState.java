package game.state;

import game.BaseballGame;
import util.CustomDesign;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuState implements GameState {
    private static MenuState menuState;
    private Scanner sc;

    public MenuState(){
    }
    public static synchronized MenuState getInstance(){
        if(menuState ==null){
           menuState = new MenuState();
        }
        return menuState;
    }

    /**
     * 옵션 입력 검증 및 상태 변경을 관리합니다
     * @param baseballGame
     */
    public void handle(BaseballGame baseballGame){
        int option = validInput();
        switchStatus(option, baseballGame);
    }


    /**
     * 사용자 입력 값 유효성을 검증합니다
     * @return 유효한 옵션 값
     */
    public int validInput(){
        while(true) {
            CustomDesign.printMainMenu();
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

    /**
     * 옵션에 따라 야구 숫자 게임의 상태를 변환합니다
     *
     * case 1 : 게임 시작하기
     * case 2 : 게임 기록 보기
     * case 3 : 게임 종료
     *
     * @param option 유효한 옵션 번호
     * @param baseballGame 야구 숫자 게임
     */
    public void switchStatus(int option, BaseballGame baseballGame){
        switch(option){
            case 1: baseballGame.nextStep(new RunState());
            case 2: baseballGame.nextStep(new RecordState());
            case 3: baseballGame.nextStep(new ExitState());
        }
    }



}
