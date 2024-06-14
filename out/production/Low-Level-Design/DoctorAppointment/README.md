Problem Statement:

We are required to build an app that lets patients connect to doctors and book appointments.
The day is divided into time slots of 1hr each(for simplicity), starting from 9 am to 9 pm. Doctors can login to the portal and declare their availability for the given day in terms of slots. Patients can login and book appointments/ cancel existing appointments.
For simplicity you can assume that the doctors’ availability is declared for that particular day only.

Functionalities required:
A new doctor should be able to register.
A doctor should be able to declare his/her availability in 1hr slots(start of the hour) for the day from 9 am till 9 pm.
Patients can look at all available slots across all doctors. The slots should be sorted by slot time. However, the sorting criteria can be extended in future.
Patients should be able to book appointments with a doctor for an available slot.Patient can book multiple slots in the same day. A patient cannot book two appointments with two different doctors in the same time slot.
Patients can also cancel an appointment, in which case that slot becomes available for someone else to book.
We should be able to search for all booked appointments given the doctor’s name or patient’s name

Bonus feature:
Build a waitlist feature:
If the patient wishes to book a slot for a particular doctor that is already booked, then add this patient to the waitlist. If the patient with whom the appointment is booked originally, cancels the appointment, then the first in the waitlist gets the appointment.
Examples:
The input/output need not be exactly in this format but the functionality should remain intact

i: input
o: output
i:registerDoc -> Curious
o: Welcome Dr. Curious !!
i: markDocAvail: Curious 9-9:30
o: Sorry Dr. Curious slots are 1 hr only
i: markDocAvail: Curious 9-10, 12-13, 16-17
o: Done Doc!
i:registerDoc -> Dreadful
o: Welcome Dr. Dreadful !!
i: markDocAvail: Dreadful 11-12
o: Done Doc!
i: showAvailability
o: Dr.Curious: (9-10)
O: Dr.Dreadful (11-12)
o: Dr.Curious: (12-13)
o: Dr.Curious: (16-17)
i: bookAppointment: (PatientA, Dr.Curious, 12)
O: Booked. Booking id: 1234
i:showAvailability
o: Dr.Curious: (9-10)
O: Dr.Dreadful (11-12)
o: Dr.Curious: (16-17)
I: cancelBookingId: 1234
O: Booking Cancelled
i: showAvailability
o: Dr.Curious: (9-10)
O: Dr.Dreadful (11-12)
o: Dr.Curious: (12-13)
o: Dr.Curious: (16-17)
i: bookAppointment: (PatientB, Dr.Curious, 12)
o: Booked. Booking id: 5678
I: showActiveAppointmentByPatient: PatientA
O; empty
I: showActiveAppointmentByPatient: PatientB
o: Dr.Curious 12

Guidelines:
Time: 90mins
Write modular and clean code.
A driver program/main class/test case is needed to test out the code by the evaluator with multiple test cases. But do not spend too much time in the input parsing. Keep it as simple as possible.
Evaluation criteria: Demoable & functionally correct code, Code readability, Proper Entity modelling, Modularity & Extensibility, Separation of concerns, Abstractions. Use design patterns wherever applicable, corner cases handling.
You are not allowed to use any external databases like MySQL. Use only in memory data structures.
No need to create any UX
Please focus on the Bonus Feature only after ensuring the required features are complete and demoable.