package user;

import game.record.GameRecord;

import java.util.*;

/**
 * 사용자 정보와 게임 기록을 관리하는 클래스입니다
 * 사용자의 이름과 게임 기록 리스트를 포함하며, 게임 기록 관리와 조회를 위한 메소드를 제공합니다
 */
public class User {
    private String username;
    private List<GameRecord> gameRecordList = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    /**
     * 사용자의 모든 게임 기록을 삭제합니다
     */
    public void clearGameRecords(){
        this.gameRecordList.clear();
    }

    /**
     * 사용자 이름을 설정합니다
     *
     * @param username 설정할 사용자 이름
     */
    public void setUsername(String username){
        this.username = username;
    }


    /**
     * 사용자의 게임 기록 리스트를 반환합니다
     *
     * @return 사용자의 게임 기록 리스트
     */
    public List<GameRecord> getGameRecordList() {
        return gameRecordList;
    }

    /**
     * 다음 게임의 시도 번호를 반환합니다
     * 이 번호는 현재 게임 기록 리스트의 크기에 1을 더한 값입니다
     *
     * @return 다음 게임의 번호
     */
    public int getGameNumber(){
        return this.gameRecordList.size()+1;
    }


    /**
     * 새로운 게임 기록을 사용자의 게임 기록 리스트에 추가합니다
     *
     * @param gameRecord 추가할 게임 기록
     */
    public void addToGameRecordList(GameRecord gameRecord){
        this.gameRecordList.add(gameRecord);
    }

    /**
     * 사용자 이름을 반환합니다
     *
     * @return 사용자의 이름
     */
    public String getUsername() {
        return username;
    }
}
