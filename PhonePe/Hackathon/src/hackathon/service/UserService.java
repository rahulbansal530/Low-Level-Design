package hackathon.service;

import hackathon.model.Problem;
import hackathon.model.User;

import java.util.List;

public interface UserService {

    public User addUser(String name, String department);
    public List<Problem> getSolvedProblems(String userId);

    public void solve(Problem problem, String userId);
}
