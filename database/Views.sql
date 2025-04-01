--The number of available rooms per area.
CREATE OR REPLACE VIEW v_available_rooms_per_area AS
SELECT h.address AS area,
       COUNT(r.roomid) AS available_rooms
FROM HOTEL h
         JOIN ROOM r ON h.hotelid = r.hotelid
WHERE r.roomid NOT IN (
    SELECT b.roomid
    FROM BOOKING b
    WHERE b.status In ('booked','transformed')
      AND CURRENT_DATE BETWEEN b.startdate AND b.enddate
    UNION
    SELECT rt.roomid
    FROM RENTING rt
    WHERE CURRENT_DATE BETWEEN rt.startdate AND rt.enddate
)
GROUP BY h.address;

--Aggregated capacity of all the rooms of a specific hotel
CREATE OR REPLACE VIEW v_hotel_capacity AS
SELECT h.hotelid,
       h.address,
       SUM(r.capacity) AS total_capacity,
       COUNT(r.roomid) AS total_rooms
FROM hotel h
         JOIN ROOM r ON h.hotelid = r.hotelid
GROUP BY h.hotelid, h.address;
