package service.impl;

import enums.RankingType;
import enums.Speciality;
import exceptions.CustomException;
import exceptions.NoUserFoundException;
import model.Doctor;
import repo.DoctorRepository;
import service.DoctorService;
import strategies.RankByStartTime;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void registerDoctor(String name, Speciality speciality) {
//        Doctor d = doctorRepository.getDoctor(name);
//        if (d != null) {
//            throw new CustomException("Doctor with name " + name + " already exists");
//        }
        Doctor doctor = new Doctor(name, speciality);
        doctorRepository.addDoctor(doctor);
        System.out.println("Doctor added - " + name);
    }

    public void addAvailabilitySlots(String docName, List<String> slots) {
        Doctor doctor = doctorRepository.getDoctor(docName);
        if (doctor == null) {
            throw new NoUserFoundException("Dcotor not found");
        }
        for (String slot : slots) {
            if (isValidSlot(slot) && !isOverlapping(slot, doctor.getAvailableSlots())) {
                doctor.getAvailableSlots().add(slot);
                System.out.println("Added availability slots for doc - " + docName + ", slot - " + slot);
            } else {
                System.out.println("Invalid or overlapping slot - " + slot);
            }
        }
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

            if (newStartTime.isBefore(existingEndTime) && newEndTime.isAfter(existingStartTime)) {
                return true;
            }
        }
        return false;
    }

    public List<Doctor> getDoctorsBySpeciality(Speciality speciality) {
        return doctorRepository.getDoctorBySpeciality(speciality);
    }

    public void getDoctors(RankingType rankingType) {
        if (rankingType.equals(RankingType.START_TIME)) {
            new RankByStartTime().rank();
        }
    }
}
