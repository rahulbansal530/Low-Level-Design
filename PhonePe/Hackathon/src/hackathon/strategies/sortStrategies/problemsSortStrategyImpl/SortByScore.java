package hackathon.strategies.sortStrategies.problemsSortStrategyImpl;

import hackathon.enums.Difficulty;
import hackathon.enums.Ordering;
import hackathon.model.Problem;
import hackathon.strategies.filterStrategies.ProblemsFilterStrategy;
import hackathon.strategies.sortStrategies.ProblemsSortStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortByScore implements ProblemsSortStrategy {

    @Override
    public void sort(List<Problem> problems, Ordering ordering) {
        if(ordering == Ordering.ASC){
            problems.sort((p1, p2) -> Integer.compare(p1.getScore(), p2.getScore()));
        } else {
            problems.sort((p1, p2) -> -1 * Integer.compare(p1.getScore(), p2.getScore()));
        }
    }
}
