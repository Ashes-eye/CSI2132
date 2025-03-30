--Show all current booking
SELECT 
    b.BookingID,
    c.Name AS Customer,
    r.RoomID,
    b.StartDate,
    b.EndDate
FROM 
    Booking b
JOIN 
    Customer c ON b.CustomerID = c.CustomerID
JOIN 
    Room r ON b.RoomID = r.RoomID
WHERE 
    CURRENT_DATE BETWEEN b.StartDate AND b.EndDate
    AND b.Status = 'booked';
