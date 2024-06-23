package repo;

public interface WaitlistRepository {

    public void addToWaitlist(String doctorName, String patientName, String slot);

    public String getNextInQueue(String docName, String slot);

}
