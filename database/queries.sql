-- creating the tables
CREATE TABLE Hotel (
    HotelID SERIAL PRIMARY KEY,
    Email VARCHAR(100),
    PhoneNumber VARCHAR(20),
    Address TEXT,
    NumberOfRooms INT,
    Rating VARCHAR(5)
);

-- inserting in tables

  INSERT INTO Hotel(HotelID ,Email ,PhoneNumber ,Address ,NumberOfRooms  ,Rating ) 
                  VALUES (1,'hotel1@gmail.com','(343-7856-500)','Washington DC',50,'*****');

   INSERT INTO Hotel(HotelID ,Email ,PhoneNumber ,Address ,NumberOfRooms  ,Rating ) 
                  VALUES (2,'hotel2@hotmail.com','(343-9526-452)','Ottawa ON',50,'***');
				  
  INSERT INTO Hotel(HotelID ,Email ,PhoneNumber ,Address ,NumberOfRooms  ,Rating ) 
                  VALUES (3,'hotel3@live.com','(343-2453-653)','Montreal QC',50,'****');
				  
-- getting attributes from tables	  
   SELECT Address FROM Hotel WHERE Email='hotel3@live.com';
   
--updating attributes in tables
   UPDATE Hotel SET Address='Toronto ON' WHERE HotelID='1';
   
--Deleting attributes in tables
   DELETE FROM Hotel WHERE HotelID='1';
