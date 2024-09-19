package game.difficulty;

import ex.InvalidInputException;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum DifficultyMode {
    EASY(1, 3),
    MEDIUM(2, 4),
    HARD(3, 5);

    private final int option;
    private final int len;

    DifficultyMode(int option, int len) {
        this.option = option;
        this.len = len;
    }

    public int getOption() {
        return option;
    }

    public int getLen() {
        return len;
    }

    public static DifficultyMode findByOption(int option){
       return Arrays.stream(values())
               .filter(v -> v.option == option)
               .findFirst()
               .orElseThrow(
                       () -> new InvalidInputException("제공하지 않는 난이도 옵션입니다")
               );
    }
    public static DifficultyMode findByLen(int len){
        return Arrays.stream(values())
                .filter(v -> v.len == len)
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("제공하지 않는 난이도 길이입니다")
                );
    }
}
