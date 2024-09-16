package game.state.difficulty;

import java.util.Arrays;

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
                       IllegalArgumentException::new
               );
    }
    public static DifficultyMode findByLen(int len){
        return Arrays.stream(values())
                .filter(v -> v.len == len)
                .findFirst()
                .get();
    }
}
