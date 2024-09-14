package game.state.difficulty;

import ex.InvalidInputException;
import util.CustomDesign;

import java.util.*;

public class DifficultyStatus {
    private final int LEN;
    private Set<Long> answers = new HashSet<>();

    private Random random;

    /**
     * 생성자
     * @param LEN 생성할 랜덤 넘버 길이
     */
    public DifficultyStatus(int LEN) {
        this.LEN = LEN;
        random = new Random();
    }

    /**
     * LEN == 3 -> EASY 모드 반환
     * LEN == 4 -> MEDIUM 모드 반환
     * LEN == 5 -> HARD 모드 반환
     *
     * @return 랜덤 넘버 길이에 따른 난이도 반환
     */
    public DifficultyMode getMode(){
        return DifficultyMode.findByLen(LEN);
    }

    /**
     *
     * @return 생성한 랜덤 넘버 길이
     */
    public int getLen(){
        return this.LEN;
    }

    /**
     * 난이도에 따른 랜덤 넘버 생성
     */
    public void generateRandomNumber(){
        while(answers.size() < LEN){
            answers.add(random.nextLong(1L, 10L));
        }
        System.out.println("랜덤 넘버 생성 완료 ✨");
    }

    /**
     * 입력한 숫자 유효성 검증과
     *
     * @param input 사용자가 입력한 숫자
     */
    public Set<Long> validateInput(String input) throws InvalidInputException {

        if (input.length() != LEN) {
            throw new InvalidInputException(LEN+" 자리수만큼 입력해야 합니다");
        }

        //String -> Long 파싱 및 길이 검증, 숫자 검증
        Set<Long> inputSet = new HashSet<>();
        for (char c : input.toCharArray()) {
            // 0 입력 검증
            if(c == '0'){
                throw new InvalidInputException("1부터 9까지의 정수만 입력해야 합니다.");
            }
            try{
                long num = Long.parseLong(String.valueOf(c));
                if (!inputSet.add(num)) {
                    throw new InvalidInputException("중복되지 않는 숫자를 입력해야 합니다.");
                }
            } catch (NumberFormatException e) {
                throw new InvalidInputException("유효하지 않은 입력입니다. 1부터 9까지의 정수만 입력해야 합니다.");
            }
        }

        return inputSet;
    }

    public boolean validateAnswer(String input){

        Set<Long> inputSet;
        //입력 값 검증
        try {
             inputSet = validateInput(input);
        }catch(Exception e){
            CustomDesign.printExceptionMessage(e.getMessage());
            return false;
        }

        //정답과 비교
        Iterator<Long> inputIter = inputSet.iterator();
        Iterator<Long> answerIter = answers.iterator();

        int strikeCnt = 0, ballCnt = 0, outCnt;

        for(int i = 0;i<LEN;i++){
            Long inputNum = inputIter.next();
            Long answerNum = answerIter.next();
            if(inputNum.equals(answerNum)) strikeCnt++;
            else if(answers.contains(inputNum)) ballCnt++;
        }

        outCnt = LEN - strikeCnt - ballCnt;

        //결과 메시지 출력
        CustomDesign.printResult(strikeCnt, ballCnt, outCnt);

        return strikeCnt == LEN;
    }

}
