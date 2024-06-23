package model;

import enums.Speciality;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Doctor {
    private String name;
    private Speciality speciality;
    private Set<String> availableSlots;
    private Map<String, String> appointments;

    public Doctor(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
        this.availableSlots = new HashSet<>();
        this.appointments = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Set<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Set<String> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public Map<String, String> getAppointments() {
        return appointments;
    }

    public void setAppointments(Map<String, String> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", speciality=" + speciality +
                ", availableSlots=" + availableSlots +
                '}';
    }
}
