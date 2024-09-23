package game.state;

import game.BaseballGame;
import game.record.GameRecord;
import game.state.menu.MenuState;
import user.User;
import util.CustomDesign;

import java.util.List;
import java.util.Scanner;

/**
 * 게임 기록을 표시하는 상태를 관리하는 클래스입니다
 */
public class RecordState implements GameState{

    private static RecordState recordState;
    private RecordState(){}

    /**
     * RecordState의 유일한 인스턴스를 반환합니다
     *
     * @return RecordState 인스턴스
     */
    public static synchronized RecordState getInstance(){
        if(recordState==null){
            recordState = new RecordState();
        }
        return recordState;
    }

    /**
     * 게임 기록을 표시하고 사용자 입력을 처리합니다
     * 현재 사용자의 게임 기록을 표시하고, 사용자가 Enter 키를 누르면 메뉴 상태(MenuState)로 돌아갑니다
     *
     * @param baseballGame 현재 진행 중인 야구 게임 인스턴스
     * @param sc 사용자 입력을 받기 위한 Scanner 객체
     */
    @Override
    public void handle(BaseballGame baseballGame, Scanner sc) {
        User user = baseballGame.getCurrentUser();
        List<GameRecord> records = user.getGameRecordList();

        CustomDesign.printUserRecords(user, records);
        System.out.println(CustomDesign.ANSI_CYAN +  "Enter 키를 누르면 메뉴로 돌아갑니다..." + CustomDesign.ANSI_RESET);
        sc.nextLine();

        //다시 메뉴 옵션 출력
        baseballGame.nextStep(MenuState.getInstance());
    }

}
