package repo.impl;

import repo.WaitlistRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class WaitlistRepositoryImpl implements WaitlistRepository {
    private Map<String, Map<String, Queue<String>>> waitlists;

    public WaitlistRepositoryImpl() {
        this.waitlists = new HashMap<>();
    }

    public void addToWaitlist(String doctorName, String patientName, String slot) {
        waitlists.computeIfAbsent(doctorName, docs -> new HashMap<>())
                .computeIfAbsent(slot, slots -> new LinkedList<>())
                .add(patientName);
    }

    public String getNextInQueue(String docName, String slot) {
        Map<String, Queue<String>> waitlist = waitlists.get(docName);
        if (waitlist != null) {
            Queue<String> waitlistForTheSlot = waitlist.get(slot);
            if (waitlistForTheSlot != null && !waitlistForTheSlot.isEmpty()) {
                return waitlistForTheSlot.poll();
            }
        }
        return null;
    }
}
