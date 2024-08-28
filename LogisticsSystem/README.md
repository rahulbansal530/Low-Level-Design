we want to design a Logistics System with following basic functionality:

1. The system can take an order to deliver it to a given destination. 
2. The order will be a list of items and there is a cost of each order to process. 
3. User has to register himself / herself to use this system. 
4. User can track his / her order. 
5. Orders will be shipped by bike or truck, but only a single order will be shipped by a single vehicle.
6. These type of questions are asked in interviews to Judge the Object Oriented Design skill of a candidate. So, first of all we should think about the classes.



### Order
orderId
List<Items>
OrderStatus currentStatus
User Sender
Location destination
totalWeight
Date orderPlacedDate
Date OrderDeliveredDate
Vehicle vehicleForOrder
PaymentDetails paymentDetails

### Item
id
name
description
price
weight


### User
userId
firstNname
lastName
address
mobile
email

### Location
longitude
latitude

### PaymentDetails
PaymentStatus
PaymentMode
transactionId
amount

### Vehicle
id
name
vehicleNo
vehicleStatus

### Bike 
capacityOfBike

### Truck
capacityOfTruck

### VehicleStatus
FREE, BUSY, NOT_WORKING

### OrderStatus
PROCESSING, DELIVERED, CANCELLED

### PaymentStatus
PAID, UNPAID

### PaymentMode
NET_BANKING, CREDIT_CARD, DEBIT_CARD




