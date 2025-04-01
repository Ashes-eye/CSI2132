--Show how many rooms each hotel has
SELECT h.HotelID,
       h.Address,
       COUNT(r.RoomID) AS total_rooms
  FROM HOTEL h
  JOIN ROOM r ON h.HotelID = r.HotelID
 GROUP BY h.HotelID, h.Address
 ORDER BY total_rooms DESC;