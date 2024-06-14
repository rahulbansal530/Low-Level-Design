package hackathon.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String departmentName;
    private Set<Problem> solvedProblems;
    private float score;

    public User(String name, String departmentName) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.departmentName = departmentName;
        solvedProblems = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Problem> getSolvedProblems() {
        return solvedProblems;
    }

    public void setSolvedProblems(Set<Problem> solvedProblems) {
        this.solvedProblems = solvedProblems;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    public void addScore(float score) {
        this.score = this.score + score;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", solvedProblems=" + solvedProblems +
                ", score=" + score +
                '}';
    }
}
