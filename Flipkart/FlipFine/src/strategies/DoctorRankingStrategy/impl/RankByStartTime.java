package strategies.DoctorRankingStrategy.impl;

import model.Doctor;
import model.DoctorAvailability;
import strategies.DoctorRankingStrategy.RankingStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankByStartTime implements RankingStrategy {

    public void rank(List<Doctor> doctors) {

        List<DoctorAvailability> allSlots = new ArrayList<>();

        for (Doctor doctor : doctors) {
            for (String slot : doctor.getAvailableSlots()) {
                allSlots.add(new DoctorAvailability(slot, doctor.getName()));
            }
        }
        if (!allSlots.isEmpty()) {
            System.out.println("Available slots based on given speciality, ordered by startTime - ");
        } else {
            System.out.println("No doctors available for the selected speciality");
        }
        allSlots.sort(Comparator.comparing(DoctorAvailability::getStartTime));

        for (DoctorAvailability da : allSlots) {
            System.out.println(da.getSlot() + " : " + da.getDoctorName());
        }
        System.out.println();
    }
}
