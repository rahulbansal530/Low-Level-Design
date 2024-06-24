Restaurants can register themselves.
Users can create, update, delete, get their profiles.
User can search for the restaurant using a restaurant name, city name.
Restaurants can add, update the food menu.
User can see the food menu. User can get the food items based on Meal type or Cuisine type.
User can add/remove items to/from the cart. User can get all the items of the cart.
User can place or cancel the order. User can get all the orders ordered by him/her.
User can apply the coupons. User can get the detailed bill containing tax details.
User can make a payment using different modes of payment — credit card, wallet, etc.
Delivery boy can get all the deliveries made by him using his Id.
User can get the order status anytime. Success, Out for Delivery, Delivered, etc.


### Restaurant -
String id,
String name,
Address address
Menu menu

### Menu
List<MenuItem> items

### MenuItem
String id
String name
Cuisine cuisine
double price

### Address
String id
String streetAddress
String city
String locality
String pincode


### Cart
String id
List<CartItem> cartItem

### CartItem
MenuItem menuItem
int quantity

### User
String id
String name
String email
Address Address

### Order
String id
User user
String restaurantId
OrderStatus status
double totalPrice
List<CartItem> items
Address deliveryAddress
update() {
user.notify()
}


### OrderStatus
PENDING, SUCCESSFUL, 

### Cuisine
INDIAN, KOREAN


### interface OrderObserver
update()

### OrderStatusTracker implements OrderObserver
update() {

}

