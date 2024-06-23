package repo;

import enums.Speciality;
import model.Doctor;

import java.util.List;

public interface DoctorRepository {
    public void addDoctor(Doctor doctor);

    public Doctor getDoctor(String name);

    public List<Doctor> getDoctorBySpeciality(Speciality speciality);
}
