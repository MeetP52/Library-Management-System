# Intro

This is a short introduction to the purpose of this project.

The goal of the project is to learn and make use of the java programming language. 
This is a simple tool that makes use of data that is locally stored in a txt file, specifically in JSON format. 
This data acts as a database off of which all queries are made of. 
This is accomplised by making use of the Jackson library to go back and forth between json notation and a hashmap data structure.

Specifically: (version 2.13.3)
- Jackson.core
- Jackson.core.databind
- Jackson.core.annotations
- Jackson.datatype.jsr

have been used.
Book, User, & Admin data specifically gets stored and read from in this manner, 
which makes up the core data that the program relies on in relation to all other operations that occur within the program.

The core functionality get broken down into these sections:
- Book
- Trie
- Cart
- User
- Admin
- lpa (main)

## Book (Section)
The book section consists of 3 classes:
- Book
- BookCatalog
- BookCatalogItem

### Book (Class)
This class contains the basic book information needed.
It contains 5 fields:
- String
  - Title
  - Series
  - Description
- Set<String>
  - Authors
  - Genres

Where, books can get quried bassed oof of its title, series, authors or genres.

### BookCatalog
This class reads the book data and creates a main book catalog which will be used by the program.
It makes use of a HashMap data strucutre, where the book's hashcode is used as the key, and a bookcatalogitem wrapper class is used to hold the book object with additional infomation as the value.
There is also a static book catalog object, which gets passed around in all instances where this catalog may be required.
This class makes use of a custom generate and store book data methods, as well as methods to find, add and remove books from the catalog. 
As well as a checkout method that works with the __Cart__ class to perform the checkout operation.
Lastly it also contains 4 different Trie instances each associated with different values that a book could be searched for by the end user.

Fields:
- Map<Integer, BookCatalogItem> books = new HashMap<>();
- static BookCatalog catalog;
- Trie titleTrie
- Trie seriesTrie
- Trie authorTrie
- Trie genreTrie

### BookCatalogItem
This is a wrapper class associates additional information per unique book. 
A core functionality of this management system is to checkout books. 
The system puts a imaginary limitation on the number of books that this system has access to. 
This wrapper class add an additional quantity field associated to the book object.
Such that users cannot checkout / borrow books if there aren't enough copies.

Fields:
- Book book
- int quantity

## Trie (Section)
The Trie section consists of 2 classes:
- Trie
- TrieNode

These two classes are used in conjunction with the classes in the Book section, to provide search functionality.

### Trie (Class)
This class provides the core functionality for the prefix trees used to make searching for a book based off of a specific string without the full context easier. 
It contains one field.

Field:
- TrieNode root

### TrieNode
This is the underlying structure dictating how the trie gets searched. This class has 4 fields.

Fields:
- char ch
- boolean wordEndChar
- Map<Character, TrieNode> childNodes = new HashMap<>()
- Set<Integers> books = new HashSet<>()

Here the key field is the __ch__ field, all strings gets broken down into indivisual characters, and the tree is iterated based off of sequences of characters seen within the word being searched for.
The boolean wordEndChar is an indicator used to let the program know that this is a end of a word for a valid search input.
If a character is a wordEndChar then it contains the set of associated books that contain that sequence of characters for a book which has that word in its title, series, author names or genres.
The map holds values and refernces to the child nodes of possible workd that can be formed with the current character at its current position within the prefix tree.

## Cart (Section)
Contains a signle class:
- Cart

the __cart__ class. Originally it was its own object associated with classed within both the Book and User sections. However, it has now been aggregated into the __addInfo__ class within the User section of classes discussed later on.

### Cart (Class)
The cart class, contains 1 field:

Field:
- List<Book> books = new LinkedList<>()

The cart class contains basic functionality of adding and removing books to the cart, as well as the __checkout__ method, this method makes use of an auxiliry method called __setup()__, which checks the book catalog to see if it has the books being requested for checkout and also to see if there is enough quanity for a proper checkout, using the list of books and min book quanity as method parameters (for setup method).
Next it maked a request to the book catalog to commit the transaction, and adjust the book quantity according.
Next it add the books to an __UserBooks__ field associated to a specific user (UserBook is discussed in the User Section.), on a successful transaction.
Lastly it empties the cart as a final step.


## User (Section)
The User Section contains 5 classes:
- User
- AddInfo
- UserBook
- UserCatalog
- UserCatalogItem

### User (Class)
The user class contains basic information relating to the user, such as, first name, last name, email address, phone number, and their physical address (country, state, city). 
Off of which the fields were created:

Fields:
- String
  - firstName
  - lastName
  - emailAddress
  - phoneNumber
  - country
  - state
  - city

Each user is considered unique bassed off their email.
Each user must have a unique email address which gets used to identify the user within the system.
The email address must meet a certain standard matched againts a email address regex.
While currently on a failed attempt the system still creates the object, the value of the user email is set to null.
The same occurs in reguard with the user's phone number. While it is not used to identity the user, it is also matched againts a valid phone number regex, and set to null on failure.

### AddInfo
The additional infomation class, holds additional information associated with each user. This information is:

Fields:
- String password
- Enum securityQuestion
- String securityAnswer
- List<UserBook> userBooks = new LinkedList<>()
- Cart cart
- int strikes

 Password is used for authentication.
 Security question and security answer is used for account restoration.
 User books contains a list of books that the user has checked out of their cart.
 The cart is used to temporarily hold books that they user might want to checkout.
 Lastly strikes are used to block user access. 
 If a user has more than 3 strikes for late returns or no returns, the user gets locked out of their account. 
 An admin user can reset the strikes and unlock user accounts, on top of manipulating the book and user catalogs.

 ### UserBook
 This class is a wrapper class for the books that the user checksout.
 It add additional fields for a theoretical pickup date, and a generated due date.
 By default the duedate is one month away from date of check out.
 But this can be extended if more time is required.

 Fields:
- Book book
- LocalDate pickUpDate
- LocalDate dueDate

### UserCatalog
This class contains the original User data, from where all user account quires can be made.
Like the book catalog class, this class also makes use of methods to read and store data from and to txt files.
On top of this it provides functionality to find, add and remove users.
Eeach user within the catalog gets an entry based off a unique hashcode generated within the User class based off of the user's email address.

Fields:
- Map<Integer, UserCatalogItem> users = new HashMap<>()
- static UserCatalog catalog

### UserCatalogItem
This is a wrapper class to provide a single instance of a user which is associated with their corresponding add info object referece.
No additional fields are added, just the two classes represented as one object for the user catalog class use.

Fields:
- User user
- AddInfo addInfo

## Admin (Section) 
The Admin section contains 2 classes:
- Admin
- AdminCatalog

Originally the admin class was part of the User section.
However upon further creation of the project it seemed clear to make admin its own section.
Rather than adding different field and methods to existing user section classes to accomadate the use of the admin class,
the admin class was moved into its own section, with its own admin catalog to keep track of admin users.

### Admin (Class)
The admin class is a super set of the users class. 
It contains an instance of the user class, and provides additional methods for increase functionality, such as adding and removing books and users.
The admin user is the only user who can directly affect the main data sets for books and users, including its own admin catalog.

Field:
- User Admin
- Addinfo info

### AdminCatalog
This is a catalog personal to the admin users.
The underlying data strcuture is the same as the regular users.

Fields:
- Map<Integer,Admin> admins = new HashMap<>()
- static AdminCatalog catalog

## Intro cont.
Concepts Used:
- Streams
- Lambdas
- Regex
- File I/O
- OOP

Tools:
- IntelliJ
- Trello
- Lucid Chart
- Excalidraw

Technologies:
- Java
- JSON
- Jackson
- JUnit5
- Maven


# Tests
At the time of writing, all classes have been tested via unit tests. Which are included within the project file.
