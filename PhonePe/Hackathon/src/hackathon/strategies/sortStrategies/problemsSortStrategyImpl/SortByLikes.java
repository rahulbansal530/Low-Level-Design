package hackathon.strategies.sortStrategies.problemsSortStrategyImpl;

import hackathon.enums.Ordering;
import hackathon.model.Problem;
import hackathon.strategies.sortStrategies.ProblemsSortStrategy;

import java.util.List;

public class SortByLikes implements ProblemsSortStrategy {

    @Override
    public void sort(List<Problem> problems, Ordering ordering) {
        if(ordering == Ordering.ASC){
            problems.sort((p1, p2) -> Integer.compare(p1.getLikes(), p2.getLikes()));
        } else {
            problems.sort((p1, p2) -> -1 * Integer.compare(p1.getLikes(), p2.getLikes()));
        }
    }
}
