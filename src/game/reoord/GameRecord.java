package game.reoord;

import user.User;

import java.util.HashMap;
import java.util.Map;

public class GameRecord {
    private User user;
    private int gameNumber; //몇 번째 게임인지
    private int attemptCnt; //게임 시도 횟수

    public GameRecord(User user) {
        this.gameNumber = 0;
        this.attemptCnt = 0;
        this.user = user;
    }
    public void increaseGameNumber() {
        this.gameNumber++;
    }

    public void increaseAttemptCnt(int attemptCnt) {
        this.attemptCnt++;
    }
    public void addToGameRecordList(){
        this.user.getGameRecordList().add(this);
    }
}
