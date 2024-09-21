package user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 사용자 관리를 담당하는 클래스입니다
 * 사용자 목록을 유지하고, 사용자 검색, 추가, 삭제 기능을 제공합니다
 */
public class UserManager {
    public static UserManager userManager;

    private List<User> userList;
    private UserManager(){
        userList = new ArrayList<>();
    }

    public static synchronized UserManager getInstance(){
        if(userManager == null){
            userManager = new UserManager();
        }
        return userManager;
    }

    /**
     *사용자 목록을 반환합니다
     *
     * @return 사용자 목록
     */
    public List<User> getUserList(){
        return this.userList;
    }

    /**
     * 모든 사용자의 게임 기록을 삭제하고, 사용자 목록을 초기화핮니다
     */
    public void clearAllUser(){
        this.userList.forEach(User::clearGameRecords);
        this.userList.clear();
    }

    /**
     * 주어진 사용자 이름으로 사용자를 검색합니다
     *
     * @param username 검색할 사용자의 이름
     * @return 찾은 사용자를 포함한 Optional 객체
     */
    public Optional<User> findByUsername(String username){
        return userList.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    /**
     * 새로운 사용자를 목록에 추가합니다
     *
     * @param user 추가할 사용자 객체
     */
    public void addUser(User user){
        this.userList.add(user);
    }


}
