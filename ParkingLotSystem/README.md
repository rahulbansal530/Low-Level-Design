## DESIGN

enum ParkingSlotType: TwoWHEELER, SMALLCOMPACT, MEDIUMCOMPACT, LARGECOMPACT

getPriceforParking(duration);

class Vehicle: 
vehicleNo
vehicleCategory;

enum vehicleCategory: scooty, bike, sedan, suv, bus, hatchback

class Person:
personId;
personName;
address;



class Ticket:
ticketNumber;
startTime;
endTime;
vehicle;
parkingSlotType;

createTicket();


class ParkingSlot
parkingSlotType;
isAvailable;
parkingSlotId;
vehicle;

addVehicle(vehicle)
removeVehicle(vehicle)

class ParkingFloor
name;
Map<ParkingSlotType, Map<parkingSlotId, parkingSlot>> map;

getRelevantSlotForVehicleAndPark(vehicle)
pickCorrectSlot(vehicleCategory)

class ParkingLot:
name;
address;
List<ParkingFloor> parkingFloors;

getInstance(name, address, floors);
addFloors(name, parkingSlots)
assignTicket(vehicle)
scanAndPay(ticket)
removeFloors(parkingFloor)
createTicketForSlot(vehicle, parkingSlotType);
getRelevantSlotForVehicleAndPark(vehicle);
