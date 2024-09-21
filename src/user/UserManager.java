package user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<User> getUserList(){
        return this.userList;
    }

    public void clearAllUser(){
        this.userList.forEach(User::clearGameRecords);
        this.userList.clear();
    }

    public Optional<User> findByUsername(String username){
        return userList.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public void addUser(User user){
        this.userList.add(user);
    }


}
