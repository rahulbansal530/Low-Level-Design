package model;

public class DoctorAvailability {

    private String slot;
    private String doctorName;
    private String startTime;

    public DoctorAvailability(String slot, String doctorName) {
        this.slot = slot;
        this.doctorName = doctorName;
        this.startTime = slot.split("-")[0];
    }

    public String getSlot() {
        return slot;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "DoctorAvailability{" +
                "slot='" + slot + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
