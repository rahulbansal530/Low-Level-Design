package hackathon.model;

import hackathon.enums.Difficulty;
import hackathon.enums.Tag;

import java.util.UUID;

public class Problem {
    private String id;
    private String title;
    private String description;
    private int score;
    private Tag tag;
    private Difficulty difficulty;
    private int numSolevdUsers;
    private float averageTime;

    private int likes;

    public Problem(String title, String description, int score, Tag tag, Difficulty difficulty) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.score = score;
        this.tag = tag;
        this.difficulty = difficulty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getNumSolevdUsers() {
        return numSolevdUsers;
    }

    public void setNumSolevdUsers(int numSolevdUsers) {
        this.numSolevdUsers = numSolevdUsers;
    }

    public void addNumSolvedUsers() {
        this.numSolevdUsers = this.numSolevdUsers + 1;
    }

    public float getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(float averageTime) {
        this.averageTime = averageTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", score=" + score +
                ", tag=" + tag +
                ", difficulty=" + difficulty +
                ", numSolevdUsers=" + numSolevdUsers +
                ", averageTime=" + averageTime +
                '}';
    }
}
