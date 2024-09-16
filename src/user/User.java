package user;

import game.record.GameRecord;

import java.util.*;

/*
* 유저 관리
*
* */
public class User {
    private String username;

    public User(String username) {
        this.username = username;
    }
    public void clearGameRecords(){
        this.gameRecordList.clear();
    }

    private List<GameRecord> gameRecordList = new ArrayList<>();
    public void setUsername(String username){
        this.username = username;
    }

    public List<GameRecord> getGameRecordList() {
        return gameRecordList;
    }

    public int getGameNumber(){
        return this.gameRecordList.size()+1;
    }

    public void addToGameRecordList(GameRecord gameRecord){
        this.gameRecordList.add(gameRecord);
    }

    public String getUsername() {
        return username;
    }
}
