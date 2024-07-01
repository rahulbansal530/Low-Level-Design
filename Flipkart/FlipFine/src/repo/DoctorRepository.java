package repo;

import enums.Speciality;
import model.Doctor;

import java.util.List;

public interface DoctorRepository {
    void addDoctor(Doctor doctor);

    Doctor getDoctor(String name);

    List<Doctor> getDoctorBySpeciality(Speciality speciality);
}
