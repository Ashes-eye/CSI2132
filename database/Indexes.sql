-- It's a common case that we find all rooms for a given hotel
-- Improve speed for that
CREATE INDEX idx_room_hotel ON room(hotelid);

--Finding booking for a given room or looking bookings for a specific range of time
--are common cases in life.
CREATE INDEX idx_booking_room_dates ON booking(roomid, startdate, enddate);

--Looking for hotel by geographical perspective is important and common.
CREATE INDEX idx_hchain_address ON hotelchain(address);
