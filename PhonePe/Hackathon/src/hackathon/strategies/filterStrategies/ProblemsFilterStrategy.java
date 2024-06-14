package hackathon.strategies.filterStrategies;

import hackathon.model.Problem;

import java.util.List;

public interface ProblemsFilterStrategy {
    List<Problem> filter(List<Problem> problema);
}
