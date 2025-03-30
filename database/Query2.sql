-- Show bookings after 2025-01-01
SELECT b.BookingID,
       b.StartDate,
       b.EndDate,
       c.Name AS Customer
  FROM Booking b
  JOIN Customer c ON b.CustomerID = c.CustomerID
 WHERE b.StartDate > (
       SELECT MIN(StartDate)
         FROM Booking
        WHERE StartDate >= '2025-01-01'
      );
