### Requirements

- There can be n groups for expense sharing
- Each group can have multiple users
- Users can add , edit and settle an expense to a group , 
- There can be 3 ways to split the exxpense like Equal split, Exact split and Percentage Split.
- System should simplify expenses everytime someone adds an expense.


### Objects

#### User
- id
- name
- email
- phone

#### Expense
- id : int
- amount: int
- descripiton
- createdBy : User
- split : map<User, amount>
- splitType : SplitType

#### Group
- id
- title
- description
- imageURI
- expenses : List<Expense>
- balance : map<User, amount>
- isSettled
- ..............
- addExpense(splitType, )

#### SplitType
- EXACT_SPLIT
- EQUAL_SPLIT
- PERCENTAGE_SPLIT

#### ExpenseFactory
- 
-
-
-
-






