package game.state.difficulty;

import java.util.Arrays;

public enum DifficultyMode {
    EASY(1),
    MEDIUM(2),
    HARD(3);

    private final int option;

    DifficultyMode(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }
    public static DifficultyMode getDifficultyMode(int option){
       return Arrays.stream(values())
               .filter(v -> v.option == option)
               .findFirst()
               .orElseThrow(
                       IllegalArgumentException::new
               );
    }
}
