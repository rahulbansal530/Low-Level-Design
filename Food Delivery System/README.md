Food Delivery System
Low level design food ordering app

- 
- 


# Account 
id
name
email
password

# User extends Account
cart
addToCart
checkout()

# Rider extends Account
isAvailable
+ setAvailability()

# Cart
id
List<CartItem>

# CartItem
id
MenuItem
quantity


# Order
id
user
address
paymentMethod
List<CartItem>

# Restraunt
id
name
cuisine ENUM
Menu
Rating

# Menu
List<MenuItem>

# MenuItem
id
name
price



FoodDeliveryService
List<User>
List<Restraunt>
