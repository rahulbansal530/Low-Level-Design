# ELEVATOR SYSTEM

## Requirements

There can be multiple floors
Each floor can have multiple elevators
Each elevator will have buttons inside and outside the elevator
Each elevator will have maximum capacity of 10 persons (~700 Kg)
An Elevator takes 1 minute to travel 1 floor
Inside buttons will have floor numbers, and buttons to close and open the door of elevator
Outside buttons will have up and down button
Elevators can be added or removed(for maintenance) as needed.
Two types of requests to be considered-
i. Person on the floor pressing the UP/DOWN button to call the elevator
ii. Person in the elevator pressing the floor number button to reach a destination

Additional req
What would be best algorithm for elevator system


OBJECTS

ElevatorState
UP, DOWN, IDLE

Button DIRECTION
	UP, DOWN

DoorState
	OPEN, CLOSE


BUILDING
name
List<Floor> floors;
List<Elevator> elevators;


Floor
floorId
List<OutsidePanel> outsidePanels;

Elevator
elevatorId
InsidePanel
DoorState doorState
int currentFloor
ElevatorState
openDoor()
closeDoor()
move(floor)


OutsidePanel
OutsideButton up
OutsideButton down

InsidePanel
DoorButton openDoor
DoorButton closeDoor
List<FloorButton> floorNumberButtons;


Abstract Button

	Bool isPressed
// is button pressed
	Bool isPressed();
	// press the button
	Void pressKaro();

FloorButton
Class FloorButton extends Button
Int floorNumber;

OutsideButton
Class OutsideButton extends Button
	Direction buttonState;

DoorButton
Class DoorButton extends Button
	DOOR buttonState;






ElevatorSystemApp // main class


Dispatcher // for algorithm
