package game.difficulty;

import ex.InvalidInputException;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 숫자 야구 게임의 난이도를 정의합니다
 * 각 난이도는 메뉴 옵션 번호와 생성할 숫자의 길이를 가집니다
 */
public enum DifficultyMode {

    /** 쉬운 난이도: 메뉴 옵션 1, 3자리 숫자 */
    EASY(1, 3),

    /** 중간 난이도: 메뉴 옵션 2, 4자리 숫자 */
    MEDIUM(2, 4),

    /** 어려운 난이도: 메뉴 옵션 3, 5자리 숫자 */
    HARD(3, 5);

    private final int option;
    private final int len;

    DifficultyMode(int option, int len) {
        this.option = option;
        this.len = len;
    }

    /**
     * 해당 난이도에서 생성할 숫자의 길이를 반환합니다
     *
     * @return 생성할 숫자의 길이
     */
    public int getLen() {
        return len;
    }

    /**
     * 주어진 메뉴 옵션 번호에 해당하는 DifficultyMode를 찾아 반환합니다
     *
     * @param option 찾고자 하는 메뉴 옵션 번호
     * @return 해당하는 DifficultyMode
     * @throws InvalidInputException 주어진 옵션 번호에 해당하는 난이도가 없을 경우 발생
     */
    public static DifficultyMode findByOption(int option){
       return Arrays.stream(values())
               .filter(v -> v.option == option)
               .findFirst()
               .orElseThrow(
                       () -> new InvalidInputException("제공하지 않는 난이도 옵션입니다")
               );
    }

    /**
     * 주어진 숫자 길이에 해당하는 DifficultyMode를 찾아 반환합니다
     *
     * @param len 찾고자 하는 숫자의 길이
     * @return 해당하는 DifficultyMode
     * @throws NoSuchElementException 주어진 길이에 해당하는 난이도가 없을 경우 발생
     */
    public static DifficultyMode findByLen(int len){
        return Arrays.stream(values())
                .filter(v -> v.len == len)
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("제공하지 않는 난이도 길이입니다")
                );
    }
}
