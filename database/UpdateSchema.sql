-- Ensure tables have the correct structure for JPA entity mappings
-- This script ensures all ID columns use sequences for auto-generation

-- 1. Fix Booking table
-- If the table doesn't exist, create it with proper ID generation
CREATE TABLE IF NOT EXISTS booking (
    bookingid SERIAL PRIMARY KEY,
    customerid INT NOT NULL,
    roomid INT NOT NULL,
    startdate DATE NOT NULL,
    enddate DATE NOT NULL,
    bookdate DATE NOT NULL,
    status VARCHAR(20) NOT NULL
);

-- If the table exists but needs the sequence
DO $$
BEGIN
    -- Check if the booking table exists but bookingid doesn't use a sequence
    IF EXISTS (
        SELECT FROM pg_tables WHERE tablename = 'booking'
    ) AND NOT EXISTS (
        SELECT FROM pg_attribute a
        JOIN pg_class c ON a.attrelid = c.oid
        JOIN pg_attrdef d ON d.adrelid = c.oid AND d.adnum = a.attnum
        WHERE c.relname = 'booking' AND a.attname = 'bookingid'
        AND d.adsrc LIKE '%nextval%'
    ) THEN
        -- Create a sequence if it doesn't exist
        CREATE SEQUENCE IF NOT EXISTS booking_id_seq;
        
        -- Set the sequence to start after the highest existing ID
        PERFORM setval('booking_id_seq', COALESCE((SELECT MAX(bookingid) FROM booking), 0) + 1);
        
        -- Alter the column to use the sequence
        ALTER TABLE booking ALTER COLUMN bookingid SET DEFAULT nextval('booking_id_seq');
    END IF;
END
$$;

-- 2. Check other tables (Room, Customer, Hotel, Employee, Renting) and fix if needed
-- Room table
CREATE TABLE IF NOT EXISTS room (
    roomid SERIAL PRIMARY KEY,
    hotelid INT NOT NULL,
    price DECIMAL(18, 2) NOT NULL,
    capacity INT NOT NULL,
    amenities VARCHAR(200),
    seaview BOOLEAN DEFAULT FALSE,
    mountainview BOOLEAN DEFAULT FALSE,
    isextendable BOOLEAN DEFAULT FALSE,
    problems VARCHAR(500)
);

-- Create sequence for room if needed
DO $$
BEGIN
    IF EXISTS (
        SELECT FROM pg_tables WHERE tablename = 'room'
    ) AND NOT EXISTS (
        SELECT FROM pg_attribute a
        JOIN pg_class c ON a.attrelid = c.oid
        JOIN pg_attrdef d ON d.adrelid = c.oid AND d.adnum = a.attnum
        WHERE c.relname = 'room' AND a.attname = 'roomid'
        AND d.adsrc LIKE '%nextval%'
    ) THEN
        CREATE SEQUENCE IF NOT EXISTS room_id_seq;
        PERFORM setval('room_id_seq', COALESCE((SELECT MAX(roomid) FROM room), 0) + 1);
        ALTER TABLE room ALTER COLUMN roomid SET DEFAULT nextval('room_id_seq');
    END IF;
END
$$;

-- Hotel table
CREATE TABLE IF NOT EXISTS hotel (
    hotelid SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    email VARCHAR(100),
    phone VARCHAR(50)
);

-- Create sequence for hotel if needed
DO $$
BEGIN
    IF EXISTS (
        SELECT FROM pg_tables WHERE tablename = 'hotel'
    ) AND NOT EXISTS (
        SELECT FROM pg_attribute a
        JOIN pg_class c ON a.attrelid = c.oid
        JOIN pg_attrdef d ON d.adrelid = c.oid AND d.adnum = a.attnum
        WHERE c.relname = 'hotel' AND a.attname = 'hotelid'
        AND d.adsrc LIKE '%nextval%'
    ) THEN
        CREATE SEQUENCE IF NOT EXISTS hotel_id_seq;
        PERFORM setval('hotel_id_seq', COALESCE((SELECT MAX(hotelid) FROM hotel), 0) + 1);
        ALTER TABLE hotel ALTER COLUMN hotelid SET DEFAULT nextval('hotel_id_seq');
    END IF;
END
$$;

-- Customer table
CREATE TABLE IF NOT EXISTS customer (
    customerid SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100)
);

-- Create sequence for customer if needed
DO $$
BEGIN
    IF EXISTS (
        SELECT FROM pg_tables WHERE tablename = 'customer'
    ) AND NOT EXISTS (
        SELECT FROM pg_attribute a
        JOIN pg_class c ON a.attrelid = c.oid
        JOIN pg_attrdef d ON d.adrelid = c.oid AND d.adnum = a.attnum
        WHERE c.relname = 'customer' AND a.attname = 'customerid'
        AND d.adsrc LIKE '%nextval%'
    ) THEN
        CREATE SEQUENCE IF NOT EXISTS customer_id_seq;
        PERFORM setval('customer_id_seq', COALESCE((SELECT MAX(customerid) FROM customer), 0) + 1);
        ALTER TABLE customer ALTER COLUMN customerid SET DEFAULT nextval('customer_id_seq');
    END IF;
END
$$;

-- Employee table
CREATE TABLE IF NOT EXISTS employee (
    employeeid SERIAL PRIMARY KEY,
    hotelid INT NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    address VARCHAR(200),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    position VARCHAR(50)
);

-- Create sequence for employee if needed
DO $$
BEGIN
    IF EXISTS (
        SELECT FROM pg_tables WHERE tablename = 'employee'
    ) AND NOT EXISTS (
        SELECT FROM pg_attribute a
        JOIN pg_class c ON a.attrelid = c.oid
        JOIN pg_attrdef d ON d.adrelid = c.oid AND d.adnum = a.attnum
        WHERE c.relname = 'employee' AND a.attname = 'employeeid'
        AND d.adsrc LIKE '%nextval%'
    ) THEN
        CREATE SEQUENCE IF NOT EXISTS employee_id_seq;
        PERFORM setval('employee_id_seq', COALESCE((SELECT MAX(employeeid) FROM employee), 0) + 1);
        ALTER TABLE employee ALTER COLUMN employeeid SET DEFAULT nextval('employee_id_seq');
    END IF;
END
$$;

-- Renting table
CREATE TABLE IF NOT EXISTS renting (
    rentingid SERIAL PRIMARY KEY,
    customerid INT NOT NULL,
    roomid INT NOT NULL,
    startdate DATE NOT NULL,
    enddate DATE NOT NULL,
    payment DECIMAL(18, 2),
    status VARCHAR(20) NOT NULL
);

-- Create sequence for renting if needed
DO $$
BEGIN
    IF EXISTS (
        SELECT FROM pg_tables WHERE tablename = 'renting'
    ) AND NOT EXISTS (
        SELECT FROM pg_attribute a
        JOIN pg_class c ON a.attrelid = c.oid
        JOIN pg_attrdef d ON d.adrelid = c.oid AND d.adnum = a.attnum
        WHERE c.relname = 'renting' AND a.attname = 'rentingid'
        AND d.adsrc LIKE '%nextval%'
    ) THEN
        CREATE SEQUENCE IF NOT EXISTS renting_id_seq;
        PERFORM setval('renting_id_seq', COALESCE((SELECT MAX(rentingid) FROM renting), 0) + 1);
        ALTER TABLE renting ALTER COLUMN rentingid SET DEFAULT nextval('renting_id_seq');
    END IF;
END
$$; 