package repo;

public interface WaitlistRepository {

    void addToWaitlist(String doctorName, String patientName, String slot);

    String getNextInQueue(String docName, String slot);

}
