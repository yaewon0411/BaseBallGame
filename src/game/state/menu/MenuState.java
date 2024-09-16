package game.state;

import game.BaseballGame;
import game.state.difficulty.DifficultyMode;
import user.User;
import util.CustomDesign;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MenuState implements GameState {
    private static MenuState menuState;
    private final Scanner sc;
    private final Map<Integer, BiConsumer<BaseballGame, User>> optionStates;


    private MenuState(){
        sc = new Scanner(System.in);
        optionStates = new HashMap<>();
        optionStates.put(1, this::validateDifficultyModeInput);
        optionStates.put(2, (game, user) -> game.nextStep(new RecordState()));
        optionStates.put(3, (game, user) -> game.nextStep(new ExitState()));
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
        int option = validateMainMenuInput();
        optionStates.getOrDefault(option, (game, u) -> CustomDesign.printExceptionMessage("유효한 옵션 번호를 입력해주세요"))
                        .accept(baseballGame, user);
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
                CustomDesign.printExceptionMessage("유효한 옵션 번호를 입력해주세요");
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
                CustomDesign.printExceptionMessage("유효한 옵션 번호를 입력해주세요");
                continue;
            }
        }
        System.out.println("난이도 선택 완료");
        //난이도에 따른 게임 로직 실행
        baseballGame.nextStep(new RunState(difficultyMode));
    }


    private boolean isMainMenuSelected = false;

    private void printAvailableMenuOptions(){ //현재 상황에서 선택 가능한 메뉴 출력
        if(!isMainMenuSelected){
            CustomDesign.printMainMenu();
        }else{
            CustomDesign.printDifficultyMenu();
        }
    }

    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 3;

    private int validateMainMenuInput(){
        return validateInput(
                option -> {
                    if (option < MIN_OPTION || option > MAX_OPTION) throw new IllegalArgumentException("유효한 옵션 번호를 입력해주세요");
                    return option;
                }
        );
    }

    private void validateDifficultyModeInput(BaseballGame baseballGame, User user){
        DifficultyMode difficultyMode = validateInput(DifficultyMode::findByOption);
        baseballGame.nextStep(new RunState(difficultyMode));
        isMainMenuSelected = false;
    }


    private <T> T validateInput(Function<Integer, T> validator){
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
