-- Create Tables
-- Hotel Chains
<<<<<<< HEAD
/*CREATE TABLE HotelChain(
=======
CREATE TABLE HotelChain(
>>>>>>> c23b03cee851e4a31fbf205b0a87f362dada3572
    HotelChainId  INT PRIMARY KEY,
    Address  VARCHAR(200) NOT NULL,
    NumberOfHotels INT DEFAULT 0,
    Email  VARCHAR(100),
    PhoneNumber  VARCHAR(50)
);


-- hotel
CREATE TABLE Hotel(
    HotelID  INT PRIMARY KEY,
    HotelChainId  INT          NOT NULL,
    Email  VARCHAR(100),
    PhoneNumber  VARCHAR(50),
    Address  VARCHAR(200) NOT NULL,
    NumberOfRooms  INT,
    Rating  INT CHECK ( Rating BETWEEN 0 AND 5),
    CONSTRAINT fk_HotelChain
        FOREIGN KEY (HotelChainId)
            REFERENCES HotelChain (HotelChainId)
            ON DELETE CASCADE
);

--Room
CREATE TABLE Room(
    RoomID   INT PRIMARY KEY,
    HotelID  INT            NOT NULL,
    Price  DECIMAL(18, 2) NOT NULL CHECK (price >= 0),
    Capacity  INT NOT NULL CHECK (capacity > 0),
    Amenities  VARCHAR(200),
    SeaView  BOOLEAN DEFAULT FALSE,
    MountainView  BOOLEAN DEFAULT FALSE,
    isExtendable  BOOLEAN DEFAULT FALSE,
    Problems  VARCHAR(500),
    CONSTRAINT fk_Room_Hotel
        FOREIGN KEY (HotelID)
            REFERENCES Hotel (HotelID)
            ON DELETE CASCADE
);

--Employee
CREATE TABLE Employee (
    EmployeeID  INT PRIMARY KEY,
    HotelID  INT NOT NULL,
    Name  VARCHAR(100) NOT NULL,
    Address  VARCHAR(200),
    Role  VARCHAR(20) ,
    CONSTRAINT fk_employee_hotel
        FOREIGN KEY (HotelID)
            REFERENCES Hotel(HotelID)
            ON DELETE CASCADE
);

--Customer
    CREATE TABLE Customer(
        CustomerID  INT PRIMARY KEY,
        Name  VARCHAR(100) NOT NULL ,
        Address  VARCHAR(200) NOT NULL ,
        RegisterDate  DATE NOT NULL
    );

--Booking
    CREATE TABLE Booking(
        BookingID  INT PRIMARY KEY ,
        CustomerID  INT,
        RoomID  INT,
        StartDate  Date NOT NULL ,
        EndDate  Date NOT NULL ,
        BookDate  DATE NOT NULL ,
        Status  VARCHAR(20) NOT NULL  CHECK ( Status IN ('booked', 'transformed', 'cancelled') ),
        Constraint fk_Booking_Customer
            FOREIGN KEY (CustomerID)
                        REFERENCES Customer(CustomerID)
                        ON DELETE SET NULL,
        Constraint fk_Booking_Room
                        FOREIGN KEY (RoomID)
                        REFERENCES Room(RoomID)
                        ON DELETE SET NULL,
        CONSTRAINT chk_BookingDates CHECK ( Booking.StartDate < Booking.EndDate )

    );

--Renting
    CREATE TABLE Renting(
        RentID  INT PRIMARY KEY ,
        CustomerID  INT,
        RoomID  INT,
        StartDate  DATE NOT NULL ,
        EndDate  DATE NOT NULL ,
        Payment  DECIMAL(18,2),
        Constraint fk_Renting_Customer
                        FOREIGN KEY (CustomerID)
                        REFERENCES Customer(CustomerID)
                        ON DELETE SET NULL ,
        CONSTRAINT fk_Renting_Room
                        FOREIGN KEY (RoomID)
                        REFERENCES Room(RoomID)
                        ON DELETE SET NULL ,
        CONSTRAINT chk_RentingDates check ( Renting.StartDate < Renting.EndDate )
    );


<<<<<<< HEAD
*/
=======

>>>>>>> c23b03cee851e4a31fbf205b0a87f362dada3572










