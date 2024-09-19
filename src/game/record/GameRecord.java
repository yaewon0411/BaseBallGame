package game.record;

import game.difficulty.DifficultyMode;

/**
 * 사용자의 게임 기록을 관리하는 클래스입니다
 * 게임 시도 횟수와 게임 난이도를 저장합니다
 * User 클래스에서 리스트를 통해 관리되며,
 * RecordState클래스와 RunState 클래스에서 사용됩니다
 */
public class GameRecord {

    private int attemptCnt; //게임 시도 횟수
    private final DifficultyMode difficultyMode; //게임 난이도

    public GameRecord(int gameNumber,  DifficultyMode difficultyMode) {
        this.attemptCnt = 0;
        this.difficultyMode = difficultyMode;
    }

    /**
     * 게임 시도 횟수를 증가합니다
     */
    public void increaseAttemptCnt() {
        this.attemptCnt++;
    }


    /**
     * 해당 게임 기록에 저장된 난이도를 반환합니다
     * @return 게임 난이도
     */
    public DifficultyMode getDifficultyMode() {
        return difficultyMode;
    }


    /**
     * 해당 게임 기록에 저장된 게임 시도 횟수를 반환합니다
     * @return 게임 시도 횟수
     */
    public int getAttemptCnt() {
        return attemptCnt;
    }
}
