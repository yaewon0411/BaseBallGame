package game.state;

import game.BaseballGame;
import game.state.menu.MenuState;
import user.User;
import util.CustomDesign;

import javax.print.attribute.standard.OutputDeviceAssigned;
import java.util.Optional;
import java.util.Scanner;

public class StartState implements GameState{

    public static StartState startState;

    private StartState(){
    }
    public static synchronized StartState getInstance(){
        if(startState==null) {
            startState = new StartState();
        }
        return startState;
    }

    /**
     * 현재 야구 숫자 게임을 진행 중인 유저 객체를 가져와
     * 환영 메시지와 메뉴를 출력합니다
     *
     * @param baseballGame 야구 숫자 게임
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

    private User login(BaseballGame baseballGame, String username){
        User currentUser = baseballGame.getUserList().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseGet(() -> {
                    User user = new User(username);
                    baseballGame.getUserList().add(user);
                    return user;
                });
        baseballGame.setCurrentUser(currentUser);
        return currentUser;
    }




}
