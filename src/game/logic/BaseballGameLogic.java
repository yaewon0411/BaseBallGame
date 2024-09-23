package game.logic;

import ex.InvalidInputException;
import game.difficulty.DifficultyMode;
import util.CustomDesign;

import java.util.*;
/**
 * 숫자 야구 게임의 핵심 로직을 구현한 클래스입니다
 * 덤 숫자 생성, 사용자 입력 검증, 결과 계산 기능을 수행합니다
 */
public class BaseballGameLogic {
    private final int LEN;
    private final Set<Long> answers;

    private static final Random random = new Random();

    private static final long MIN_NUMBER = 1L;
    private static final long MAX_NUMBER = 10L;

    /**
     * 생성자입니다
     *
     * @param LEN 생성할 랜덤 넘버의 길이 -> 이 값에 따라 게임의 난이도가 결정됩니다
     */
    public BaseballGameLogic(int LEN) {
        this.LEN = LEN;
        answers  = new LinkedHashSet<>();
    }

    /**
     * 현재 게임의 난이도 모드를 반환합니다
     *
     * @return 랜덤 넘버 길이에 따른 난이도 (EASY, MEDIUM, HARD)
     * @throws InvalidInputException 현재 설정된 숫자 길이에 해당하는 난이도가 없을 경우 발생
     */
    public DifficultyMode getMode() throws NoSuchElementException{
        return DifficultyMode.findByLen(LEN);
    }

    /**
     *현재 게임에서 사용되는 랜덤 넘버 길이를 반환합니다
     *
     * @return 생성한 랜덤 넘버 길이
     */
    public int getLen(){
        return this.LEN;
    }

    /**
     * 게임에서 사용될 랜덤 넘버를 생성합니다
     * 생성된 숫자는 1부터 9까지의 중복되지 않는 숫자들로 구성됩니다
     */
    public void generateRandomNumber(){
        while(answers.size() < LEN){
            answers.add(random.nextLong(MIN_NUMBER, MAX_NUMBER));
        }
        System.out.println("랜덤 넘버 생성 완료 ✨");

        //디버깅 용도 랜덤 넘버 출력
//        for (Long answer : answers) {
//            System.out.print(answer +" ");
//        }
//        System.out.println();
    }

    /**
     * 사용자가 입력한 숫자 유효성을 검증합니다
     *
     * @param input 사용자가 입력한 숫자 문자열
     * @return 유효성 검증을 통과한 입력 숫자들의 Set
     * @throws InvalidInputException 입력이 유효하지 않을 경우 발생
     */
    public Set<Long> validateInput(String input) throws InvalidInputException {

        if (input.length() != LEN) {
            throw new InvalidInputException(LEN+" 자리수만큼 입력해야 합니다");
        }

        //String -> Long 파싱 및 길이 검증, 숫자 검증
        Set<Long> inputSet = new LinkedHashSet<>();
        for (char c : input.toCharArray()) {
            // 0 입력 검증
            if(c == '0'){
                throw new InvalidInputException("유효하지 않은 입력입니다. 1부터 9까지의 정수만 입력해야 합니다.");
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

    /**
     * 사용자의 입력을 검증하고 답과 일치하는지 여부를 반환합니다
     *
     * @param input 사용자가 입력한 숫자 문자열
     * @return 사용자의 입력이 정답과 일치하면 true, 그렇지 않으면 false
     */
    public boolean validateAnswer(String input){

        Set<Long> inputSet;
        //입력 값 검증
        try {
             inputSet = validateInput(input);
        }catch(InvalidInputException e){
            CustomDesign.printExceptionMessage(e.getMessage());
            return false;
        }

        ResultCount resultCount = calculateResult(inputSet);
        resultCount.printResult();

        return resultCount.getStrikeCnt() == LEN;
    }

    /**
     * 사용자 입력과 정답을 비교하여 결과를 계산합니다
     *
     * @param inputSet 사용자가 입력한 숫자 집합
     * @return 스트라이크, 볼, 아웃 개수를 포함한 ResultCount 객체
     */
    private ResultCount calculateResult(Set<Long> inputSet){
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

        return new ResultCount(strikeCnt, ballCnt, outCnt);
    }



}
