package hackathon.service.impl;

import hackathon.dataStore.UserData;
import hackathon.exceptions.NoUserFoundException;
import hackathon.model.Problem;
import hackathon.model.User;
import hackathon.service.ProblemService;
import hackathon.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private UserData userData;
    private ProblemService problemService;

    public UserServiceImpl(UserData userData, ProblemService problemService){
        this.userData = userData;
        this.problemService = problemService;
    }

    public UserServiceImpl(UserData userData){
        this.userData = userData;
//        this.problemService = problemService;
    }

    public User addUser(String name, String department) {
        User user = new User(name, department);
        userData.addUser(user);
        return user;
    }

    public List<Problem> getSolvedProblems(String userId) {
        User user = userData.getUserById(userId);
        if (Objects.isNull(user)) throw new NoUserFoundException("No user found with Id - " + userId);
        return new ArrayList<>(user.getSolvedProblems());
    }

    public void solve(Problem problem, String userId) {
        User user = userData.getUserById(userId);
        if (Objects.isNull(user)) throw new NoUserFoundException("No user found with Id - " + userId);
        user.getSolvedProblems().add(problem);
        user.addScore(problem.getScore());
    }

}
