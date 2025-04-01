--Update number of hotels when inserting and deleting
CREATE OR REPLACE FUNCTION trg_increase_room()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE Hotel
    SET NumberOfRooms = NumberOfRooms + 1
    WHERE HotelID = NEW.HotelID;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_room_insert
    AFTER INSERT ON Room
    FOR EACH ROW
EXECUTE PROCEDURE trg_increase_room();

CREATE OR REPLACE FUNCTION trg_decrease_room()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE Hotel
    SET NumberOfRooms = NumberOfRooms - 1
    WHERE HotelID = OLD.HotelID;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_room_delete
    AFTER DELETE ON Room
    FOR EACH ROW
EXECUTE PROCEDURE trg_decrease_room();


--Update booking status
CREATE OR REPLACE FUNCTION trg_cancel_bookings()
    RETURNS TRIGGER AS $$
BEGIN
    -- If the startDate has passed, make it cancelled.
    IF (OLD.status = 'booked') AND (OLD.StartDate < CURRENT_DATE) THEN
        NEW.status := 'cancelled';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_check_booking_past
    BEFORE UPDATE ON Booking
    FOR EACH ROW
    WHEN (OLD.status = 'booked')  -- Only if old status was 'booked'
EXECUTE PROCEDURE trg_cancel_bookings();


--One Manger per hotel
CREATE OR REPLACE FUNCTION trg_one_manager_per_hotel()
    RETURNS TRIGGER AS $$
DECLARE
    existing_managers INT;
BEGIN
    IF NEW.role = 'Manager' THEN
        SELECT COUNT(*) INTO existing_managers
        FROM EMPLOYEE
        WHERE hotelid = NEW.hotelid
          AND role = 'Manager';

        IF existing_managers > 0 THEN
            RAISE EXCEPTION 'Hotel % already has a manager!', NEW.hotelid;
        END IF;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_employee_insert_manager
    BEFORE INSERT ON EMPLOYEE
    FOR EACH ROW
EXECUTE PROCEDURE trg_one_manager_per_hotel();


-- no multiple bookings for one room
CREATE OR REPLACE FUNCTION trg_no_double_booking()
    RETURNS TRIGGER AS $$
DECLARE
    conflict_count INT;
BEGIN
    IF NEW.status = 'booked' THEN
        SELECT COUNT(*)
        INTO conflict_count
        FROM booking
        WHERE roomid = NEW.roomid
          AND status = 'booked'
          AND (
            NEW.startdate < enddate
                AND NEW.enddate > startdate
            );

        IF conflict_count > 0 THEN
            RAISE EXCEPTION 'Room % is already booked in the overlapping date range', NEW.roomid;
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_booking_no_overlap
    BEFORE INSERT OR UPDATE ON booking
    FOR EACH ROW
EXECUTE PROCEDURE trg_no_double_booking();
