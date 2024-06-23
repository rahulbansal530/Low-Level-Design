package repo.impl;

import model.Patient;
import repo.PatientRepository;

import java.util.HashMap;
import java.util.Map;

public class PatientRepositoryImpl implements PatientRepository {
    private Map<String, Patient> patients;

    public PatientRepositoryImpl() {
        this.patients = new HashMap<>();
    }

    public void addPatient(Patient patient) {
        patients.put(patient.getName(), patient);
    }

    public Patient getPatient(String name) {
        return patients.get(name);
    }
}
