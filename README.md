presentation: https://1drv.ms/p/c/ef276fdc3036f9ab/IQDGEswgJWaKTbNTJEhl_AIZAeasMmBdazczFxz9FEQojq0?e=Ya9daC

Nazarova Madina (COMFCI-25)

Event Ticket Sales Manager

Manages the sale of tickets for events, including pricing, availability, and sales tracking.

The Event Ticket Sales Manager is a Java-based console application designed to handle the sale and management of event tickets. It allows administrators to create different types of tickets (Regular and VIP), calculate final prices based on ticket categories, and track sales. The system ensures data persistence by saving all information to a local file, making it a reliable tool for event organization.

Project Requirement List

1. Create Records: The system can add new tickets for specific events.
2. Ticket Categorization: Support for multiple ticket types, specifically "Regular" and "VIP".
3. Automatic Price Calculation: Implementation of polymorphism to calculate final prices (e.g., a 50% surcharge for VIP tickets).
4. View Records (Read): Users can display a complete list of all issued tickets.
5. Update Records: The system allows updating the base price of existing tickets using their index.
6. Delete Records: Users can remove specific tickets from the database.
7. Command Line Interface (CLI): A user-friendly text-based menu for navigating all features.
8. Input Validation: The system checks for empty strings and ensures numerical fields receive valid data.
9. Error Handling: Use of try-catch blocks to prevent the application from crashing due to invalid user input.
10. Data Export: Automatic saving of the ticket list to a binary file (tickets_data.bin) upon exit.
11. Data Import: Automatic loading of saved data from the file when the application starts.

Documentation

Algorithms and Logic

CRUD Logic: The application uses a List<Ticket> to manage data in memory. Standard loops and indexing are used for searching and modifying records.

File I/O: The project utilizes Java Serialization (ObjectOutputStream / ObjectInputStream). This allows the entire list of objects to be saved and loaded efficiently without losing the object structure.

OOP Principles: Encapsulation: All sensitive data fields are private with public getters.

Inheritance: VipTicket and RegularTicket extend the abstract Ticket class.

Polymorphism: The calculatePrice() method is overridden in subclasses to provide specific pricing logic.

Encountered Difficulties

Data Consistency: Ensuring that the correct ticket type (VIP vs Regular) was maintained during the "Update" process. This was solved using the instanceof operator to check the object type before recreating it with a new price.

Input Errors: Handling cases where users enter text instead of numbers. This was resolved by wrapping the scanner logic in a try-catch block.


Input Errors: Handling cases where users enter text instead of numbers. This was resolved by wrapping the scanner logic in a try-catch block.
