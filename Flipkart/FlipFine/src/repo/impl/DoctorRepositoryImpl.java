package repo.impl;

import enums.Speciality;
import model.Doctor;
import repo.DoctorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepositoryImpl implements DoctorRepository {
    private Map<String, Doctor> doctors;

    public DoctorRepositoryImpl() {
        this.doctors = new HashMap<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.put(doctor.getName(), doctor);
    }

    public Doctor getDoctor(String name) {
        return doctors.get(name);
    }

    public List<Doctor> getDoctorBySpeciality(Speciality speciality) {
        List<Doctor> doctorList = new ArrayList<>();
        for (Doctor doctor : doctors.values()) {
            if (doctor.getSpeciality().equals(speciality)) {
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }


}
