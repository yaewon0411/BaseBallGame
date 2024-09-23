package game;


import game.state.GameState;
import game.state.StartState;
import user.User;
import user.UserManager;

import java.util.*;

/**
 * 숫자 야구 게임 전체 로직을 담당하는 메인 클래스입니다
 * 게임 상태, 사용자 관리, 게임 진행을 관리합니다
 */

public class BaseballGame {
    /** 현재 게임의 상태를 나타내는 객체 */
    private GameState gameState;
    /** 현재 게임의 상태를 나타내는 객체 */
    private final Scanner sc;
    /** 게임 실행 여부를 나타내는 플래그 */
    private boolean isRunning;
    /** 현재 게임을 진행 중인 사용자 */
    private User currentUser;
    /** 게임에 참여한 모든 사용자를 관리하는 객체 */
    private final UserManager userManager;


    /**
     * BaseballGame 클래스의 생성자입니다
     * Scanner를 초기화하고 게임 실행 상태를 true로 설정합니다
     */
    public BaseballGame() {
        sc = new Scanner(System.in);
        isRunning = true;
        userManager = UserManager.getInstance();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * 게임을 종료하고 Scanner 객체를 닫습니다.
     */
    public void exit() {
        isRunning = false;
        sc.close();
    }

    /**
     * 현재 게임 중인 사용자를 반환합니다
     * @return 현재 사용자
     */
    public User getCurrentUser() {
        return currentUser;
    }


    /**
     * 모든 사용자의 게임 기록을 삭제하고
     * 사용자 목록을 초기화합니다
     */
    public void clearHistory(){
        userManager.clearAllUser();
    }

    /**
     * 게임의 다음 상태로 전환합니다
     * @param gameState 다음 게임 상태
     */
    public void nextStep(GameState gameState){
        this.gameState = gameState;
    }


    /**
     * 현재 게임을 진행 중인 사용자를 설정합니다
     * @param user 설정할 사용자
     */
    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    /**
     * 게임을 시작하고 게임 루프를 실행합니다
     * 게임이 종료될 때까지 현재 상태에 따라 게임을 진행합니다
     */
    public void start(){
        gameState = StartState.getInstance();
        while(isRunning) {
            gameState.handle(this, sc);
        }
    }

    public static void main(String[] args) {
        BaseballGame game = new BaseballGame();
        game.start();
    }

}
