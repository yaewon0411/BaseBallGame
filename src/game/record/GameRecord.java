package game.record;

import game.difficulty.DifficultyMode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 사용자의 게임 기록을 관리하는 클래스입니다
 * 게임 시도 횟수와 게임 난이도를 저장합니다
 * User 클래스에서 리스트를 통해 관리되며,
 * RecordState클래스와 RunState 클래스에서 사용됩니다
 */
public class GameRecord {

    private int attemptCnt; //게임 시도 횟수
    private final DifficultyMode difficultyMode; //게임 난이도
    private boolean isFinished; //게임 성공 여부

    private LocalDateTime finishedDate; //게임 성공 일자

    public GameRecord(int gameNumber,  DifficultyMode difficultyMode) {
        this.attemptCnt = 0;
        this.difficultyMode = difficultyMode;
        this.isFinished = false;
    }

    public void setFinishedDate(LocalDateTime finishedDate) {
        this.finishedDate = finishedDate;
    }

    public LocalDateTime getFinishedDate() {
        return finishedDate;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isFinished() {
        return isFinished;
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
