# Lab 6 - Inn Reservation System

## Main Requirements 
---
### Rooms and Rates
   When this option is selected, the system shall output a list of rooms to the
user sorted by popularity (highest to lowest, see below for definition of ”popularity”) Include all
information from the rooms table, as well as the following:
   * room popularity score: number of days the room has been occupied during the previous 180 days divided by 180 (round to two decimal places)
   * next available check in
   * length of most recent stay

---
### Reservations 
   When this option is selected, your system shall accept from the user the following
information:
   * first name
   * last name
   * A room code to indicate the specific room desired (or ”Any” to indicate no preference)
   * A desired bed type (or ”Any” to indicate no preference)
   * Desired begin and end dates of stay
   * Number of children
   * Number of adults

With this info, find all available, matching rooms, along with a prompt that allows the user to book them. 

If no exact matches are found, find 5 possibilities for a different room. 

If the requested person count (children plus adults) exceeds the maximum capacity of any one
room in the system, print a message indicating this. To reserve a block of rooms, it would be
up to the user to submit multiple reservation requests.
At the prompt, the user may decide to cancel the current request, which will return the user to
the main menu. 

If the user chooses to book one of the room options presented, they will enter
the option number at the prompt. After a choice is made, provide the user with a confirmation
screen that displays the following:

* First name, last name
* Room code, room name, bed type
* Begin and end date of stay
* Number of adults
* Number of children
* Total cost of stay, based on a sum of the following:
   * Number of weekdays * room base rate
   * Number of weekend days * (110% of the room base rate)
   * An 18% tourism tax applied to the total of the above two lines

Allow the user to either cancel, returning to the main menu, or confirm, which will finalize their
reservation and create an entry in the lab6 reservations table.

---
### Detailed Reservation Information 
Present the user with a search prompt or form that allows them to enter any combination of the fields listed below (a blank entry should indicate ”Any”). For all fields except dates, permit partial values using SQL LIKE wildcards (for example: GL% should be allowed as a last name search value)

* First name
* Last name
* A range of dates
* Room code
* Reservation code

Using the search information provided, display a list of all matching reservations found in the
database. The list shall show the contents of every attribute from the lab6 reservations table (as well as the full name of the room, and any extra information about the room you wish to add).

---
### Revenue

When this option is selected, your system shall provide a month-by-month overview
of revenue for an entire year. 

For the purpose of this assignment, all revenue from the reservation
is assigned to the month and year when the reservation ended. For example a seven-day hotel
stay that started on October 30 will be treated as November revenue.

Your system shall display a list of rooms, and, for each room, 13 columns: 12 columns showing
dollar revenue for each month and a 13th column to display total year revenue for the room.
There shall also be a ”totals” row in the table, which contains column totals. All amounts
should be rounded to the nearest whole dollar.