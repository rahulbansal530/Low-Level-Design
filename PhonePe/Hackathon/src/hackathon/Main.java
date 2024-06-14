package hackathon;

import hackathon.dataStore.ProblemData;
import hackathon.dataStore.UserData;
import hackathon.enums.Difficulty;
import hackathon.enums.Tag;
import hackathon.model.Problem;
import hackathon.model.User;
import hackathon.service.ProblemService;
import hackathon.service.UserService;
import hackathon.service.impl.ProblemServiceImpl;
import hackathon.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        UserData userData = new UserData();
        ProblemData problemData = new ProblemData();


        UserService userService = new UserServiceImpl(userData);
        ProblemService problemService = new ProblemServiceImpl(problemData, userService);

        Problem problem1 = problemService.addProblem("title1", "desc1", 1, Tag.ARRAYS, Difficulty.EASY);
        Problem problem2 = problemService.addProblem("title2", "desc2", 1, Tag.LINKED_LIST, Difficulty.MEDIUM);
        Problem problem3 = problemService.addProblem("title1", "desc3", 1, Tag.TREES, Difficulty.HARD);

        User user1 = userService.addUser("user1", "dep1");
        User user2 = userService.addUser("user2", "dep2");
        User user3 = userService.addUser("user3", "dep3");


        List<Problem> problems = problemService.fetchProblems(null, null, null, null);

        problemService.solve(user1.getId(), problem1);

        System.out.println(user1);

        problemService.fetchProblems(Arrays.asList(Tag.LINKED_LIST), Difficulty.EASY, null, null);

//        System.out.println();
    }
}