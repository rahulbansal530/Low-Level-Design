package service.impl;

import enums.Speciality;
import exceptions.CustomException;
import exceptions.NoUserFoundException;
import model.Doctor;
import repo.DoctorRepository;
import service.DoctorService;
import strategies.DoctorRankingStrategy.RankingStrategy;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void registerDoctor(String name, Speciality speciality) {
        Doctor d = doctorRepository.getDoctor(name);
        if (d != null) {
            throw new CustomException("Doctor with name " + name + " already exists");
        }
        Doctor doctor = new Doctor(name, speciality);
        doctorRepository.addDoctor(doctor);
        System.out.println("Doctor added - Dr." + name);
    }

    public void addAvailabilitySlots(String docName, List<String> slots) {
        Doctor doctor = doctorRepository.getDoctor(docName);
        if (doctor == null) {
            throw new NoUserFoundException("Dcotor not found");
        }
        for (String slot : slots) {
            if (isValidSlot(slot) && !isOverlapping(slot, doctor.getAvailableSlots())) {
                doctor.getAvailableSlots().add(slot);
                System.out.println("Added availability slot for Dr." + docName + ", slot - " + slot);
            } else {
                System.out.println("Invalid or overlapping slot - " + slot);
            }
        }
        System.out.println();
    }

    private boolean isValidSlot(String slot) {
        String[] parts = slot.split("-");
        if (parts.length != 2) return false;

        String start = parts[0];
        String end = parts[1];

        try {
            LocalTime startTime = LocalTime.parse(start);
            LocalTime endTime = LocalTime.parse(end);

            if (startTime.compareTo(LocalTime.of(9, 0)) < 0 || endTime.compareTo(LocalTime.of(21, 0)) > 0) return false;
            if (startTime.equals(endTime) || startTime.isAfter(endTime)) return false;
            Duration duration = Duration.between(startTime, endTime);
            if (duration.toMinutes() != 30) return false;
            return true;
        } catch (Exception e) {
            return false;
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

            if ((newStartTime.isAfter(existingStartTime) && newStartTime.isBefore(existingEndTime))
                    || (newEndTime.isAfter(existingStartTime) && newEndTime.isBefore(existingEndTime))
                    || (newStartTime.equals(existingStartTime) && newEndTime.equals(existingEndTime))) {
                return true;
            }
        }
        return false;
    }

    public void getDoctorsBySpeciality(Speciality speciality, RankingStrategy rankingStrategy) {
        List<Doctor> doctors = doctorRepository.getDoctorBySpeciality(speciality);
        rankingStrategy.rank(doctors);
    }

    public void viewAppointments(String doctorName) {
        Doctor doctor = doctorRepository.getDoctor(doctorName);
        if (doctor == null) {
            throw new NoUserFoundException("doctor with name " + doctorName + " not found");
        }
        System.out.println("Appointments for doctor - " + doctorName + " :");
        for (Map.Entry<String, String> appointment : doctor.getAppointments().entrySet()) {
            System.out.println(appointment.getKey() + " : Patient - " + appointment.getValue());
        }
        System.out.println();
    }
}
