package model;

import java.util.HashMap;
import java.util.Map;

public class Patient {
    private String name;
    private Map<String, String> bookedAppointments;

    public Patient(String name) {
        this.name = name;
        this.bookedAppointments = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getBookedAppointments() {
        return bookedAppointments;
    }

    public void setBookedAppointments(Map<String, String> appointments) {
        this.bookedAppointments = appointments;
    }
}
