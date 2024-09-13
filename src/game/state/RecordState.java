package game.state;

import game.BaseballGame;
import game.reoord.GameRecord;
import user.User;

/*
* < 게임 기록 보기 >
1번째 게임 : 시도 횟수 - 14
2번째 게임 : 시도 횟수 - 9
3번째 게임 : 시도 횟수 - 12
* */
public class RecordState implements GameState{

    @Override
    public void handle(BaseballGame baseballGame, User user) {
        System.out.println(user.getUsername()+"님의 게임 기록은 다음과 같습니다");

        if(user.getGameRecordList().isEmpty())
            System.out.println("======게임을 진행한 이력이 없습니다=======");

        else {
            int i = 1;
            for (GameRecord record : user.getGameRecordList()) {
                System.out.println(i++ + "번째 게임 [" + record.getDifficultyMode() + "] 시도 횟수 : " + record.getAttemptCnt());
            }
        }

        //다시 메뉴 옵션 출력
        baseballGame.nextStep(MenuState.getInstance());
    }
}
