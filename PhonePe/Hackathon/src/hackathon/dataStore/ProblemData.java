package hackathon.dataStore;

import hackathon.exceptions.NoUserFoundException;
import hackathon.model.Problem;
import hackathon.model.User;

import java.util.*;

public class ProblemData {
    Map<String, Problem> problemIdToProblem = new HashMap<>();

    public Problem getProblemById(String id){
        Problem problem = problemIdToProblem.get(id);
        if(Objects.isNull(id)) throw new NoUserFoundException("No problem found with Id: " + id);
        return problem;
    }

    public void addProblem(Problem problem) {
        problemIdToProblem.put(problem.getId(), problem);
    }

    public List<Problem> getAllProblems() {
        return new ArrayList<>(problemIdToProblem.values());
    }
}
