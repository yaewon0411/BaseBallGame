package game.record;

import game.difficulty.DifficultyMode;

public class GameRecord {

    private int attemptCnt; //게임 시도 횟수
    private final DifficultyMode difficultyMode;

    public GameRecord(int gameNumber,  DifficultyMode difficultyMode) {
        this.attemptCnt = 0;
        this.difficultyMode = difficultyMode;
    }

    public void increaseAttemptCnt() {
        this.attemptCnt++;
    }

    public DifficultyMode getDifficultyMode() {
        return difficultyMode;
    }

    public int getAttemptCnt() {
        return attemptCnt;
    }
}
