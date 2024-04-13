

## Requirements
* There can be n meeting rooms
* Each room can have a maximum capacity
* User can book a meeting room for a particular time interval - start time and end time
* System should send notification to all attendees on scheduling and cancelling a meeting
* Users will recieve an invite on scheduling a meeintg, they can accept or decline the invite.

## Class Design


#### User
- name
- email
- contact
- respondInvitation() // Accept or decline

#### Interval
- startTime : Date
- endTime : Date

#### Meeting
- id : int
- interval : Interval
- attendees : List{User}
- room : Room

#### Room
- id : int
- capacity : int
- isAvailable : boolean
- bookedIntervals : List{Interval}

#### Calendar


#### Notification


#### MeetingSchedulerApp
- users : List{User}
- rooms : List{Room}
- scheduleMeeting(roomId, List<User>)
- 
