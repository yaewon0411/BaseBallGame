package game.state.difficulty;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DifficultyStatus {
    private final int LEN;
    private Set<Long> answers = new HashSet<>();

    public DifficultyStatus(int LEN) {
        this.LEN = LEN;
    }
    public DifficultyMode getMode(){
        return DifficultyMode.findByLen(LEN);
    }
    public int getLen(){
        return this.LEN;
    }
    public void generateRandomNumber(){
        Random random = new Random();
        while(answers.size() < LEN){
            answers.add(random.nextLong(1L, 9L));
        }
        System.out.println("랜덤 넘버 생성 완료 ✨ : ");
        for (Long answer : answers) {
            System.out.print(answer);
        }
        System.out.println();
    }
    public boolean validateAnswer(String input){
        if(input.length() != LEN) throw new IllegalStateException(LEN+" 자리수만큼 입력해야 합니다");

        //String -> Long 파싱 및 길이 검증, 숫자 검증
        Set<Long> inputSet = new HashSet<>();
        for(int i = 0; i<LEN; i++){
            String subStr = input.substring(i, i+1);

            if(subStr.equals("0")){
                System.out.println("0은 입력하시면 안됩니다!!!!");
                return false;
            }

            try {
                Long subAnswer = Long.parseLong(subStr);

                if(inputSet.contains(subAnswer)) {
                    //throw new IllegalStateException("숫자 중복해서 입력하면 안됨");
                    System.out.println("숫자 중복해서 입력하면 안됨");
                    return false;
                }
                inputSet.add(subAnswer);
            }catch (NumberFormatException e){
                System.out.println("숫자 입력해야 한다는 오류 메시지 출력");
                return false;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        //정답과 비교
        Object[] inputAry = inputSet.toArray();
        Object[] answerAry = answers.toArray();

//        System.out.println("===니가 입력한 거===");
//        for (Object o : inputAry) {
//            System.out.print(o + " ");
//        }


        int strikeCnt = 0;
        int ballCnt = 0;
        int outCnt = 0;

        for(int i = 0;i<LEN;i++){
            if(((Long) inputAry[i]).equals((Long) answerAry[i])){
                strikeCnt++;
            }
            else{
                boolean isContain = answers.contains((Long) inputAry[i]);
                if(isContain){
                    ballCnt++;
                } else{
                    outCnt++;
                }
            }
        }


        if(strikeCnt==LEN) return true;

        //스트라이크는 몇이고 볼은 몇이고 아웃은 몇입니다. 어쩌구저쩌구 출력
        System.out.println("스트라이크 개수 : " + strikeCnt);
        System.out.println("볼 개수 : "+ballCnt);
        System.out.println("아웃 개수 : "+outCnt);


        return false;
    }

}
