package game.state.ranking;

import game.difficulty.DifficultyMode;
import user.User;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserRanking {

    private final String username;
    private final int score;
    private final int attemptCnt;
    private final DifficultyMode difficultyMode;

    private final LocalDateTime finishedDate;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String getUsername() {
        return username;
    }
    public LocalDateTime getFinishedDate() {
        return finishedDate;
    }
    public String getFormattedFinishedDate() {
       return dateTimeFormatter.format(finishedDate);
    }

    public int getScore() {
        return score;
    }

    public int getAttemptCnt() {
        return attemptCnt;
    }

    public DifficultyMode getDifficultyMode() {
        return difficultyMode;
    }

    public UserRanking(User user, int score, DifficultyMode difficultyMode, int totalAttemptCnt, LocalDateTime finishedDate) {
        this.username = user.getUsername();
        this.score = score;
        this.difficultyMode = difficultyMode;
        this.attemptCnt = totalAttemptCnt;
        this.finishedDate = finishedDate;
    }

}
