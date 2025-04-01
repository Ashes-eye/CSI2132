-- Fix Booking ID Sequence
-- This script adds a sequence for bookingid and alters the column to use it

-- First, create a sequence for booking IDs if it doesn't exist
CREATE SEQUENCE IF NOT EXISTS booking_id_seq;

-- Modify the bookingid column to use the sequence for default values
ALTER TABLE booking ALTER COLUMN bookingid SET DEFAULT nextval('booking_id_seq');

-- Set the sequence to start after the highest existing booking ID
-- This ensures new IDs don't conflict with existing ones
SELECT setval('booking_id_seq', COALESCE((SELECT MAX(bookingid) FROM booking), 0) + 1);

-- Make sure bookingid is marked as NOT NULL
ALTER TABLE booking ALTER COLUMN bookingid SET NOT NULL; 