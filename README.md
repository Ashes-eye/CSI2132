<<<<<<< HEAD
# E-Hotel Booking System

A simple hotel room booking system with customer and employee interfaces.

## Project Structure

- **backend**: Spring Boot application for the backend API
- **frontend**: HTML, CSS, and JavaScript for the frontend UI
- **database**: SQL scripts for database setup

## Prerequisites

- Java 17 or higher
- PostgreSQL database
- Maven (or you can use the included Maven wrapper)

## Database Setup

1. Create a PostgreSQL database
2. Update the database connection settings in `backend/e-hotel/src/main/resources/application.properties` if needed
3. Run the following SQL scripts in order:
   - `database/CreateTables.sql` - Creates all required tables
   - `database/InsertData.sql` - Populates the database with sample data
   - `database/Indexes.sql` - Creates indexes for better performance
   - `database/Triggers.sql` - Sets up database triggers
   - `database/Views.sql` - Creates views for common queries

## Running the Application

### Backend

1. Navigate to the backend directory:
   ```
   cd backend/e-hotel
   ```

2. Run the Spring Boot application:
   ```
   ./mvnw spring-boot:run
   ```
   
   Or on Windows:
   ```
   mvnw.cmd spring-boot:run
   ```

3. The backend server will start on http://localhost:8080

### Frontend

1. Open any of the HTML files in your browser, starting with `Login.html`
2. Sample login credentials:
   - Customer: Use the email and password from the customer table
   - Employee: Use the email and password from the employee table

## Features

- Customer registration and login
- Employee login
- Room searching and filtering
- Room booking
- View and manage bookings
- Basic user authentication

## API Endpoints

- **Authentication**: `/api/auth/customerLogin` and `/api/auth/employeeLogin`
- **Bookings**: `/api/booking`
- **Rooms**: `/api/room`
- **Hotels**: `/api/hotel`
- **Customers**: `/api/customer`
- **Employees**: `/api/employee`
- **Rentings**: `/api/renting`
=======
# CSI2132

The e-Hotel Booking System is a web application that allows users to book hotels, manage reservations, and handle employee authentication. It is built using:

Frontend: HTML 

Backend: Spring Boot (Java) with PostgreSQL

Database: PostgreSQL

Prerequisites

1. Install Java Development Kit (JDK)
Download and install JDK 17 or later: Download JDK
Verify installation:
java -version

 2. Install PostgreSQL (Database)
 Download and install PostgreSQL 17: Download PostgreSQL
Create a database named e_hotel.
Open pgAdmin and set up the database

 3. Install Maven (Build Tool)
Download and install Apache Maven: Download Maven
Verify installation:
mvn -version
>>>>>>> c23b03cee851e4a31fbf205b0a87f362dada3572
