package user;

import game.reoord.GameRecord;
import game.state.RecordState;

import java.util.*;

/*
* 유저 관리
*
* */
public class User {
    private String username;

    private List<GameRecord> gameRecordList = new ArrayList<>();
    public void setUsername(String username){
        this.username = username;
    }

    public List<GameRecord> getGameRecordList() {
        return gameRecordList;
    }

    public String getUsername() {
        return username;
    }
}
