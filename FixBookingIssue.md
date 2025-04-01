# Fix for Booking Issue

The error message indicates that there's a problem with the database schema. The `bookingid` column in the `booking` table is not automatically generating values as expected by the JPA entity mapping.

## The Problem

When trying to create a booking, you get this error:
```
Error creating booking: could not execute statement [ERROR: null value in column "bookingid" of relation "booking" violates not-null constraint
Detail: Failing row contains (null, 1, 10, 2025-03-31, 2025-04-10, 2025-03-31, PENDING).]
```

## Solution

Follow these steps to fix the issue:

1. **Run the SQL script to fix the database schema**

   I've created two SQL scripts to help you:
   
   - `database/FixBookingSequence.sql` - A simple script that just fixes the booking table sequence
   - `database/UpdateSchema.sql` - A comprehensive script that checks and fixes all tables in the database

2. **Apply the SQL script to your PostgreSQL database:**
   
   Connect to your PostgreSQL database and run the scripts. You can use any of these methods:
   
   **Option 1: Using psql command line:**
   ```
   psql -U postgres -d postgres -f "C:\Users\souhail\Downloads\CSI2132-main\database\FixBookingSequence.sql"
   ```
   
   **Option 2: Using pgAdmin:**
   - Open pgAdmin
   - Connect to your database
   - Open the Query Tool
   - Paste the content of `FixBookingSequence.sql` or `UpdateSchema.sql`
   - Click Execute

3. **Restart the Spring Boot application:**
   ```
   cd backend/e-hotel
   ./mvnw spring-boot:run
   ```
   Or on Windows:
   ```
   cd backend\e-hotel
   mvnw.cmd spring-boot:run
   ```

4. **Try booking again**

   The issue should be resolved, and you should be able to create bookings without errors.

## What the Fix Does

The SQL script does the following:

1. Creates a sequence for generating booking IDs
2. Alters the booking table to use the sequence for the bookingid column
3. Sets the sequence to start from the next available ID

This ensures that when the JPA code tries to insert a new booking without specifying an ID, the database automatically generates one using the sequence. 