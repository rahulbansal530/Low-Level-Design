package service.impl;

import exceptions.CustomException;
import exceptions.NoUserFoundException;
import model.Doctor;
import model.Patient;
import repo.DoctorRepository;
import repo.PatientRepository;
import repo.WaitlistRepository;
import service.PatientService;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private WaitlistRepository waitlistRepository;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository,
                              WaitlistRepository waitlistRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.waitlistRepository = waitlistRepository;
    }

    public void registerPatient(String name) {
        Patient patient = new Patient(name);
        patientRepository.addPatient(patient);
        System.out.println("Patient added - " + name);
    }

    public void bookAppointment(String patientName, String doctorName, String slot) {
        Patient patient = patientRepository.getPatient(patientName);
        if (patient == null) {
            throw new NoUserFoundException("Patient not found");
        }
        Doctor doctor = doctorRepository.getDoctor(doctorName);
        if (doctor == null) {
            throw new NoUserFoundException("Doctor not found");
        }
        if (!isOverlapping(slot, patient.getBookedAppointments().keySet())) {
            if (doctor.getAvailableSlots().contains(slot)) {
                doctor.getAvailableSlots().remove(slot);
                doctor.getAppointments().put(slot, patientName);
                patient.getBookedAppointments().put(slot, doctorName);
                System.out.println("Booked. doctor - " + doctorName + ", patient - " + patientName + ", slot - " + slot);
            } else {
                waitlistRepository.addToWaitlist(doctorName, patientName, slot);
                System.out.println("Slot not available, added to the waitlist");
            }
        } else {
            throw new CustomException("Overlapping slot for patient");
        }
    }

    private boolean isOverlapping(String newSlot, Set<String> availableSlots) {
        String[] newSlotParts = newSlot.split("-");
        LocalTime newStartTime = LocalTime.parse(newSlotParts[0]);
        LocalTime newEndTime = LocalTime.parse(newSlotParts[1]);

        for (String slot : availableSlots) {
            String[] slotParts = slot.split("-");
            LocalTime existingStartTime = LocalTime.parse(slotParts[0]);
            LocalTime existingEndTime = LocalTime.parse(slotParts[1]);

            if (newStartTime.isBefore(existingEndTime) && newEndTime.isAfter(existingStartTime)) {
                return true;
            }
        }
        return false;
    }

    public void cancelAppointment(String patientName, String slot) {
        Patient patient = patientRepository.getPatient(patientName);
        if (patient == null) {
            throw new NoUserFoundException("Patient not found");
        }
        String docName = patient.getBookedAppointments().remove(slot);
        if (docName == null) {
            throw new CustomException("No Appointment found for slot - " + slot);
        }
        Doctor doctor = doctorRepository.getDoctor(docName);
        doctor.getAppointments().remove(slot);
        doctor.getAvailableSlots().add(slot);
        System.out.println("Booking cancelled");
        String nextPatient = waitlistRepository.getNextInQueue(docName, slot);
        if (nextPatient != null) {
            bookAppointment(nextPatient, docName, slot);
            System.out.println("Booked for next in Waitlist patient - " + nextPatient);
        }
    }

    public void viewAppointments(String patientName) {
        Patient patient = patientRepository.getPatient(patientName);
        if (patient == null) {
            throw new NoUserFoundException("patient not found");
        }
        for (Map.Entry<String, String> appointment : patient.getBookedAppointments().entrySet()) {
            System.out.println(appointment.getKey() + " : " + appointment.getValue());
        }
    }

    public void getBookedSlots(String name) {
        Map<String, String> bookedSlots = patientRepository.getPatient(name).getBookedAppointments();
        for (String slot : bookedSlots.keySet()) {
            System.out.println(slot + " - " + bookedSlots.get(slot));
        }
    }
}
