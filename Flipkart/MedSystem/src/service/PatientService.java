package service;

import enums.Speciality;
import model.Doctor;

public interface PatientService {

    public void registerPatient(String name);
    void bookAppointment(String patientName, String doctorName, String slot);

    void cancelAppointment(String patientName, String slot);

    void viewAppointments(String patientName);

    void getBookedSlots(String name);
}
