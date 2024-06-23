package repo;

import model.Patient;

public interface PatientRepository {
    public void addPatient(Patient patient);

    public Patient getPatient(String name);
}
