--- ROOM POPULARITY RATES
SELECT Room, COUNT(Room) / 180 FROM lab6_reservations
WHERE Checkout >= (CURDATE() - INTERVAL 180 DAY)
GROUP BY Room
ORDER BY COUNT(Room) DESC;

--- NEXT AVAILABLE CHECK-IN DATE


--- LENGTH OF MOST RECENT STAY IN ROOM AND MOST RECENT CHECK OUT
SELECT DATEDIFF(Checkout, Checkin), Checkout FROM lab6_reservations
WHERE Checkout <= NOW()
ORDER BY Checkout DESC LIMIT 2;

--- FIND MATCHING ROOMS
SELECT Room FROM lab6_rooms
WHERE Room = <RoomCode>
AND WHERE <bedType> > 0
AND WHERE <beginDate> >= (
   -- subquery checking for checkin date starting as close to beginDate as possible
)
AND WHERE <endDate> >= (
   -- subquery checking for checkout date ending as close to endDate as possible
)
AND WHERE <numChildren> + <numAdults> <= maxOcc

--- TOTAL COST OF STAY
