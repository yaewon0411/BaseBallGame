package game.state;

import game.BaseballGame;
import user.User;
import util.CustomDesign;

public class StartState{

    /**
     * 현재 야구 숫자 게임을 진행 중인 유저 객체를 가져와
     * 환영 메시지와
     * 메뉴
     *
     * @param baseballGame 야구 숫자 게임
     * @param user 유저 객체
     */
    public void handle(BaseballGame baseballGame, User user) {
            CustomDesign.printUserWelcomeMessage(user);
            baseballGame.nextStep(MenuState.getInstance());
    }
}
