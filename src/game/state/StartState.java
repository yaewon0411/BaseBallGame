package game.state;

import game.BaseballGame;
import game.state.menu.MenuState;
import user.User;
import user.UserManager;
import util.CustomDesign;

import javax.print.attribute.standard.OutputDeviceAssigned;
import java.util.Optional;
import java.util.Scanner;

/**
 * 숫자 야구 게임의 시작 상태를 관리하는 클래스입니다
 * 게임 시작 시 사용자 로그인 및 초기 메뉴 표시를 담당합니다
 */
public class StartState implements GameState{

    public static StartState startState;

    private StartState(){
    }

    /**
     * StartState의 유일한 인스턴스를 반환합니다
     *
     * @return StartState의 인스턴스
     */
    public static synchronized StartState getInstance(){
        if(startState==null) {
            startState = new StartState();
        }
        return startState;
    }

    /**
     * 야구 숫자 게임을 시작하고, 사용자 로그인 과정을 처리합니다
     * 환영 메시지를 표시하고, 사용자 입력을 처리한 후 적절한 다음 상태로 전환합니다
     *
     * @param baseballGame 야구 숫자 게임 객체
     * @param sc Scanner 객체
     */
    public void handle(BaseballGame baseballGame,  Scanner sc) {

        CustomDesign.printStartMessage();

        String username = processUserInput(baseballGame, sc);
        if (username != null) {
            User currentUser = login(baseballGame, username);
            CustomDesign.printUserWelcomeMessage(currentUser);
            baseballGame.nextStep(MenuState.getInstance());
        }
    }

    /**
     * 사용자 입력을 처리합니다
     * 사용자가 닉네임을 입력하거나 'exit'를 입력할 때까지 반복합니다
     *
     * @param baseballGame 야구 숫자 게임 객체
     * @param sc Scanner 객체
     * @return 입력된 사용자 닉네임, 또는 'exit' 입력 시 null
     */
    private String processUserInput(BaseballGame baseballGame, Scanner sc){
        while (true) {
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                CustomDesign.printExceptionMessage("닉네임 또는 'exit'을 입력해주세요.");
                System.out.print(CustomDesign.ANSI_GREEN + "✏️  다시 입력: " + CustomDesign.ANSI_RESET);
                continue;
            }

            if (input.equalsIgnoreCase("exit")) {
                baseballGame.nextStep(ExitState.getInstance());
                return null;
            }

            return input;
        }
    }

    /**
     * 사용자 로그인을 처리합니다
     * 입력된 username으로 기존 사용자를 찾거나 새 사용자를 생성합니다
     *
     * @param baseballGame 야구 숫자 게임 객체
     * @param username 입력된 사용자 닉네임
     * @return 로그인된 User 객체
     */
    private User login(BaseballGame baseballGame, String username){
        UserManager userManager = baseballGame.getUserManager();
        User currentUser = userManager.findByUsername(username)
                .orElseGet(
                        () -> {
                            User newUser = new User(username);
                            userManager.addUser(newUser);
                            return newUser;
                        }
                );
        baseballGame.setCurrentUser(currentUser);
        return currentUser;
    }


}
