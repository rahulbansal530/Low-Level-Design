package hackathon.strategies.filterStrategies.problemsFilterStrategyImpl;

import hackathon.enums.Difficulty;
import hackathon.model.Problem;
import hackathon.strategies.filterStrategies.ProblemsFilterStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByDifficulty implements ProblemsFilterStrategy {
    private Difficulty difficulty;

    public FilterByDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public List<Problem> filter(List<Problem> problems) {
        return problems.stream().filter(this::helper).collect(Collectors.toList());
    }

    private boolean helper(Problem problem) {
        return difficulty.equals(problem.getDifficulty());
    }
}
