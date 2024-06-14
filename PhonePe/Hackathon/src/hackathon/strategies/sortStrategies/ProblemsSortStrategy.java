package hackathon.strategies.sortStrategies;

import hackathon.enums.Ordering;
import hackathon.model.Problem;

import java.util.List;

public interface ProblemsSortStrategy {
    void sort(List<Problem> problem, Ordering ordering);
}
