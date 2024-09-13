package game.state;

import game.BaseballGame;
import user.User;

public interface GameState {
    void handle(BaseballGame baseballGame);
}
