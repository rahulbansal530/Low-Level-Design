package strategies.DoctorRankingStrategy;

import model.Doctor;

import java.util.List;

public interface RankingStrategy {
    void rank(List<Doctor> doctors);
}
