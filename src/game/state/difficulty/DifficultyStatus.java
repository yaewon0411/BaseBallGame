package game.state.difficulty;

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
        System.out.println("랜덤 넘버 생성 완료 ✨ : ");
        for (Long answer : answers) {
            System.out.print(answer);
        }
        System.out.println();
    }

    /**
     * 입력한 숫자 유효성 검증과
     * 정답 판별 기능을 수행한 후
     * 정답과 비교한 상태를 출력
     *
     *
     * @param input 사용자가 입력한 숫자
     * @return 정답이라면 true, 아니라면 false
     */
    public boolean validateAnswer(String input){

        if (input.length() != LEN) {
            System.out.println(LEN + " 자리수만큼 입력해야 합니다");
            return false;
        }

        //String -> Long 파싱 및 길이 검증, 숫자 검증
        Set<Long> inputSet = new HashSet<>();
        for (char c : input.toCharArray()) {
            // 0 입력 검증
            if(c == '0'){
                System.out.println("0은 입력하시면 안됩니다!!!!");
                return false;
            }
            try{
                long num = Long.parseLong(String.valueOf(c));
                if (!inputSet.add(num)) {
                    System.out.println("숫자 중복해서 입력하면 안됨");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해야 함");
                return false;
            }
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


        //스트라이크는 몇이고 볼은 몇이고 아웃은 몇입니다. 어쩌구저쩌구 출력
        System.out.println("스트라이크 개수 : " + strikeCnt);
        System.out.println("볼 개수 : "+ballCnt);
        System.out.println("아웃 개수 : "+outCnt);

        return strikeCnt == LEN;
    }

}
