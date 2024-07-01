package service;

import enums.Speciality;
import exceptions.NoUserFoundException;
import model.Patient;
import strategies.DoctorRankingStrategy.RankingStrategy;

import java.util.List;
import java.util.Map;

public interface DoctorService {

    void registerDoctor(String name, Speciality speciality);

    void addAvailabilitySlots(String docName, List<String> slots);

    void getDoctorsBySpeciality(Speciality speciality, RankingStrategy rankingStrategy);

    void viewAppointments(String doctorName);
}
