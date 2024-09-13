package game;


import game.state.GameState;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class BaseballGame {
    private GameState gameState;
    private List<User> userList = new ArrayList<>();


    public void nextStep(GameState gameState){
        this.gameState = gameState;
    }

    public void addToUserList(User user){

    }

    public List<User> getUserList() {
        return userList;
    }
}
