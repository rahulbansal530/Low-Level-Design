package hackathon.service.impl;

import hackathon.dataStore.ProblemData;
import hackathon.enums.Difficulty;
import hackathon.enums.OrderBy;
import hackathon.enums.Ordering;
import hackathon.enums.Tag;
import hackathon.model.Problem;
import hackathon.service.ProblemService;
import hackathon.service.UserService;
import hackathon.strategies.filterStrategies.ProblemsFilterStrategy;
import hackathon.strategies.filterStrategies.problemsFilterStrategyImpl.FilterByDifficulty;
import hackathon.strategies.filterStrategies.problemsFilterStrategyImpl.FilterByTags;
import hackathon.strategies.sortStrategies.ProblemsSortStrategy;
import hackathon.strategies.sortStrategies.problemsSortStrategyImpl.SortByScore;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProblemServiceImpl implements ProblemService {
    private ProblemData problemData;
    private UserService userService;

    public ProblemServiceImpl(ProblemData problemData, UserService userService) {
        this.problemData = problemData;
        this.userService = userService;
    }

    public Problem addProblem(String title, String description, int score, Tag tag, Difficulty difficulty) {
        Problem problem = new Problem(title, description, score, tag, difficulty);
        problemData.addProblem(problem);
        return problem;
    }

    public List<Problem> fetchProblems(List<Tag> tags, Difficulty difficulty, OrderBy orderBy, Ordering ordering) {
        List<Problem> problems = problemData.getAllProblems();

        if (Objects.nonNull(tags)) {
            ProblemsFilterStrategy problemsFilterStrategy = new FilterByTags(tags);
            problems = problemsFilterStrategy.filter(problems);
        }
        if (Objects.nonNull(difficulty)){
            ProblemsFilterStrategy problemsFilterStrategy = new FilterByDifficulty(difficulty);
            problems = problemsFilterStrategy.filter(problems);
        }

        ProblemsSortStrategy problemsSortStrategy = new SortByScore();
        problemsSortStrategy.sort(problems, ordering);

        System.out.println("Problems -");
        for (Problem problem : problems) {
            System.out.println(problem);
        }

        return problems;
    }

    public void solve(String userId, Problem problem) {
        userService.solve(problem, userId);
        problem.addNumSolvedUsers();
        float averageTime = problem.getAverageTime();
        problem.setAverageTime(averageTime + 1); // Adding 1 to avg time on each submission for now.
    }

    public List<Problem> fetchSolvedProblems(String userId) {
        return userService.getSolvedProblems(userId);
    }

    public List<Problem> getTopNProblems(Tag tag, int n) {
        List<Problem> problems = problemData.getAllProblems();

        ProblemsFilterStrategy problemsFilterStrategy = new FilterByTags(Collections.singletonList(tag));
        problems = problemsFilterStrategy.filter(problems);

        ProblemsSortStrategy problemsSortStrategy = new SortByScore();
        problemsSortStrategy.sort(problems, Ordering.DESC);

        return problems.stream().limit(n).collect(Collectors.toList());
    }
}
