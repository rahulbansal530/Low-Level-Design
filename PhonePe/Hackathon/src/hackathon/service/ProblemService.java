package hackathon.service;

import hackathon.enums.Difficulty;
import hackathon.enums.OrderBy;
import hackathon.enums.Ordering;
import hackathon.enums.Tag;
import hackathon.model.Problem;

import java.util.List;

public interface ProblemService {
    public Problem addProblem(String title, String description, int score, Tag tag, Difficulty difficulty);
    public List<Problem> fetchProblems(List<Tag> tags, Difficulty difficulty, OrderBy orderBy, Ordering ordering);
    public void solve(String userId, Problem problem);
    public List<Problem> fetchSolvedProblems(String userId);
    public List<Problem> getTopNProblems(Tag tag, int n);
}
