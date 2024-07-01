package repo;

import model.Patient;

public interface PatientRepository {
    void addPatient(Patient patient);

    Patient getPatient(String name);
}
