package hackathon.dataStore;

import hackathon.exceptions.NoUserFoundException;
import hackathon.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserData {
    Map<String, User> userIdToUser = new HashMap<>();

    public User getUserById(String id){
        User user = userIdToUser.get(id);
        if(Objects.isNull(id)) throw new NoUserFoundException("No user found with Id: " + id);
        return user;
    }

    public void addUser(User user) {
        userIdToUser.put(user.getId(), user);
    }
}
