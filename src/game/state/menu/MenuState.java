package game.state.menu;

import game.BaseballGame;
import game.state.ExitState;
import game.state.GameState;
import game.state.RecordState;
import game.state.RunState;
import game.state.difficulty.DifficultyMode;
import user.User;
import util.CustomDesign;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class MenuState implements GameState {
    private static MenuState menuState;
    private final Map<Integer, TriConsumer<BaseballGame, User, Scanner>> optionStates;
    private MenuStatus currentStatus;

    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 3;


    private MenuState(){
        this.optionStates = new HashMap<>();
        optionStates.put(1, this::validateDifficultyModeInput);
        optionStates.put(2, (game, user, sc) -> game.nextStep(new RecordState()));
        optionStates.put(3, (game, user, sc) -> game.nextStep(new ExitState()));
        currentStatus = MenuStatus.MAIN_MENU;
    }
    public static synchronized MenuState getInstance(){
        if(menuState ==null){
           menuState = new MenuState();
        }
        return menuState;
    }

    /**
     * 사용자 입력을 처리하고 게임 상태를 변경합니다.
     * 이 메서드는 메인 메뉴를 표시하고, 사용자의 선택에 따라 적절한 동작을 수행합니다.
     *
     * @param baseballGame 현재의 야구 게임 인스턴스
     * @param user 현재 사용자
     */
    public void handle(BaseballGame baseballGame, User user, Scanner sc){
        resetToMainMenu();
        int option = validateMainMenuInput(sc);
        optionStates.getOrDefault(option, (game, u, s) -> CustomDesign.printExceptionMessage("유효한 옵션 번호를 입력해주세요"))
                        .accept(baseballGame, user, sc);
    }

    /**
     * 메뉴 상태를 메인 메뉴로 재설정합니다.
     */
    private void resetToMainMenu() {
        currentStatus = MenuStatus.MAIN_MENU;
    }

    /**
     * 현재 메뉴 상태에 따라 적절한 메뉴 옵션을 출력합니다.
     */
    private void printAvailableMenuOptions() {
        switch (currentStatus) {
            case MAIN_MENU:
                CustomDesign.printMainMenu();
                break;
            case DIFFICULTY_SELECTION:
                CustomDesign.printDifficultyMenu();
                break;
        }
    }


    /**
     * 메인 메뉴 입력을 검증합니다.
     *
     * @return 유효한 메인 메뉴 옵션 번호
     */
    private int validateMainMenuInput(Scanner sc){
        return validateInput(
                option -> {
                    if (option < MIN_OPTION || option > MAX_OPTION) throw new IllegalArgumentException("유효한 옵션 번호를 입력해주세요");
                    return option;
                }
                ,sc
        );
    }

    /**
     * 난이도 모드 입력을 검증하고 게임 상태를 변경합니다.
     *
     * @param baseballGame 현재의 야구 게임 인스턴스
     * @param user 현재 사용자
     */
    private void validateDifficultyModeInput(BaseballGame baseballGame, User user, Scanner sc){
        currentStatus = MenuStatus.DIFFICULTY_SELECTION;
        DifficultyMode difficultyMode = validateInput(DifficultyMode::findByOption, sc);
        baseballGame.nextStep(new RunState(difficultyMode));
    }


    /**
     * 사용자 입력을 검증합니다.
     * 다양한 타입의 입력 검증에 사용될 수 있습니다.
     *
     * @param <T> 반환될 값의 타입
     * @param validator 입력을 검증하고 변환하는 함수
     * @return 검증된 입력 값
     */
    private <T> T validateInput(Function<Integer, T> validator, Scanner sc){
        while(true){
            printAvailableMenuOptions();
            try{
                int option = sc.nextInt();
                return validator.apply(option);
            }catch(InputMismatchException | IllegalArgumentException e){
                CustomDesign.printExceptionMessage(e.getMessage());
                sc.nextLine();
            }
        }
    }

}
