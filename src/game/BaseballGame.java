package game;


import game.state.GameState;
import game.state.StartState;
import user.User;

import java.util.*;

public class BaseballGame {
    private GameState gameState;
    private final Scanner sc;
    private boolean isRunning;
    private User currentUser;

    public BaseballGame() {
        sc = new Scanner(System.in);
        isRunning = true;
    }

    public void exit() {
        isRunning = false;
    }

    private List<User> userList = new ArrayList<>();

    public List<User> getUserList(){
        return this.userList;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    public void clearHistory(){
        this.userList.forEach(User::clearGameRecords);
        this.userList.clear();
    }

    public void nextStep(GameState gameState){
        this.gameState = gameState;
    }


    public void setCurrentUser(User user){
        this.currentUser = user;
    }

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
