package hackathon.strategies.filterStrategies.problemsFilterStrategyImpl;

import hackathon.enums.Tag;
import hackathon.model.Problem;
import hackathon.strategies.filterStrategies.ProblemsFilterStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByTags implements ProblemsFilterStrategy {
    private List<Tag> tags;

    public FilterByTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public List<Problem> filter(List<Problem> problems) {
        return problems.stream().filter(this::helper).collect(Collectors.toList());
    }

    private boolean helper(Problem problem) {
        return tags.contains(problem.getTag());
    }
}
