package game.state.ranking;

import game.BaseballGame;
import game.difficulty.DifficultyMode;
import game.record.GameRecord;
import game.state.GameState;
import game.state.menu.MenuState;
import user.User;
import util.CustomDesign;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 게임의 랭킹 상태를 관리하는 클래스입니다
 * 사용자들의 게임 기록을 바탕으로 랭킹을 계산하고 표시합니다
 */
public class RankingState implements GameState {

    public static RankingState rankingState;

    private RankingState(){}

    public static synchronized RankingState getInstance(){
        if(rankingState == null){
            rankingState = new RankingState();
        }
        return rankingState;
    }
/*
* 순위     이름      점수   난이도  총 시도횟수   진행 날짜
* 1위  마에다 리쿠   105점   HARD      1회     2024-09-21 19:02:43
*
* */
    /**
     * 사용자 목록을 가져와 랭킹을 계산하고 출력한 후, 메뉴 상태로 전환합니다
     *
     * @param baseballGame 현재의 야구 게임 인스턴스
     * @param sc Scanner 객체
     */
    @Override
    public void handle(BaseballGame baseballGame, Scanner sc) {
        List<User> userList = baseballGame.getUserManager().getUserList();
        List<UserRanking> rankingList = getUserRankingList(userList);
        CustomDesign.printRanking(rankingList);

        baseballGame.nextStep(MenuState.getInstance());
    }

    /**
     * 주어진 사용자 목록으로부터 랭킹 리스트를 생성합니다
     *
     * @param userList 사용자 목록
     * @return 정렬된 UserRanking 객체 리스트
     */
    private List<UserRanking> getUserRankingList(List<User> userList) {
        return userList.stream()
                .map(this::calculateUserRanking)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(Comparator
                        .comparingInt(UserRanking::getScore).reversed()
                        .thenComparingInt(UserRanking::getAttemptCnt)
                        .thenComparing(UserRanking::getFinishedDate)
                )
                .collect(Collectors.toList());
    }

    /**
     * 주어진 사용자의 최고 점수 게임 기록을 찾아 UserRanking 객체를 생성합니다
     *
     * @param user 랭킹을 계산할 사용자
     * @return UserRanking 객체를 감싼 Optional
     */
    private Optional<UserRanking> calculateUserRanking(User user) {
        return user.getGameRecordList().stream()
                .filter(GameRecord::isFinished)
                .max(Comparator.comparingInt(this::calculateGameScore))
                .map(gameRecord -> {
                    UserRanking userRanking = new UserRanking(user, calculateGameScore(gameRecord), gameRecord.getDifficultyMode(), gameRecord.getAttemptCnt(), gameRecord.getFinishedDate());
                    return Optional.of(userRanking);
                })
                .orElse(Optional.empty());
    }

    /**
     * 주어진 게임 기록의 최종 점수를 계산합니다
     *
     * @param gameRecord 점수를 계산할 게임 기록
     * @return 계산된 게임 점수
     */
    private int calculateGameScore(GameRecord gameRecord) {
        int score = 100;
        score += getDifficultyScore(gameRecord.getDifficultyMode());
        score -= gameRecord.getAttemptCnt();
        score += getBonusScore(gameRecord.getAttemptCnt());
        return score;
    }

    /**
     * 시도 횟수에 따른 보너스 점수를 계산합니다
     *
     * @param attemptCount 시도 횟수
     * @return 보너스 점수
     */
    private int getBonusScore(int attemptCount) {
        if (attemptCount <= 3) return 10;
        if (attemptCount <= 5) return 5;
        return 0;
    }

    /**
     * 게임 난이도에 따른 가산점을 반환합니다
     *
     * @param difficultyMode 게임 난이도
     * @return 난이도에 따른 가산점
     */
    private int getDifficultyScore(DifficultyMode difficultyMode){
        return switch(difficultyMode){
            case EASY -> 1;
            case MEDIUM -> 3;
            case HARD -> 5;
        };
    }
}
