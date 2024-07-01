package service;

public interface PatientService {

    void registerPatient(String name);

    void bookAppointment(String patientName, String doctorName, String slot);

    void cancelAppointment(String patientName, String slot);

    void viewAppointments(String patientName);

}
