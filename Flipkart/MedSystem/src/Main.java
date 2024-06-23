import enums.Speciality;
import repo.DoctorRepository;
import repo.PatientRepository;
import repo.WaitlistRepository;
import repo.impl.DoctorRepositoryImpl;
import repo.impl.PatientRepositoryImpl;
import repo.impl.WaitlistRepositoryImpl;
import service.DoctorService;
import service.PatientService;
import service.impl.DoctorServiceImpl;
import service.impl.PatientServiceImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DoctorRepository doctorRepository = new DoctorRepositoryImpl();
        PatientRepository patientRepository = new PatientRepositoryImpl();
        WaitlistRepository waitlistRepository = new WaitlistRepositoryImpl();

        DoctorService doctorService = new DoctorServiceImpl(doctorRepository);
        PatientService patientService = new PatientServiceImpl(patientRepository, doctorRepository, waitlistRepository);

        doctorService.registerDoctor("Curious", Speciality.CARDIOLOGIST);
        doctorService.addAvailabilitySlots("Curious", Arrays.asList("09:00-09:30", "09:30-10:00"));

//        doctorService.registerDoctor("Curious", Speciality.DERMATOLOGIST);

        doctorService.registerDoctor("Dreadful", Speciality.DERMATOLOGIST);
        doctorService.addAvailabilitySlots("Dreadful", Arrays.asList("09:00-09:30", "09:30-10:00", "10:00-10:30"));

        System.out.println(doctorService.getDoctorsBySpeciality(Speciality.CARDIOLOGIST));

        patientService.registerPatient("patient1");
        patientService.bookAppointment("patient1", "Dreadful", "09:00-09:30");
        patientService.bookAppointment("patient1", "Curious", "09:30-10:00");
//        patientService.bookAppointment("patient1", "Dreadful", "09:30-10:00");

        patientService.registerPatient("patient2");
        patientService.bookAppointment("patient2", "Dreadful", "09:00-09:30");
        patientService.bookAppointment("patient2", "Curious", "09:30-10:00");

        System.out.println("Appointments - ");
        patientService.viewAppointments("patient1");

        patientService.cancelAppointment("patient1", "09:30-10:00");
        System.out.println("Booked slot for patient 1 - ");
        patientService.getBookedSlots("patient1");

        System.out.println("Booked slot for patient 2 - ");
        patientService.getBookedSlots("patient2");
    }
}