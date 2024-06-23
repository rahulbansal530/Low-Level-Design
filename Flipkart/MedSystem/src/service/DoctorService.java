package service;

import enums.Speciality;
import model.Doctor;

import java.util.List;

public interface DoctorService {

    public void registerDoctor(String name, Speciality speciality);

    public void addAvailabilitySlots(String docName, List<String> slots);

    public List<Doctor> getDoctorsBySpeciality(Speciality speciality);
}
