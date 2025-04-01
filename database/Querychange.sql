<<<<<<< HEAD
/*ALTER TABLE employee
=======
ALTER TABLE employee
>>>>>>> c23b03cee851e4a31fbf205b0a87f362dada3572
    ADD COLUMN email VARCHAR(255);

ALTER TABLE employee
    ADD COLUMN password VARCHAR(255);

ALTER TABLE customer
    ADD COLUMN email VARCHAR(255);

ALTER TABLE customer
    ADD COLUMN password VARCHAR(255);

UPDATE public.employee
SET email = 'employee@email.com',
    password = 'password'
WHERE employeeid = 1;

UPDATE public.customer
SET email = 'customer@email.com',
    password = 'password'
WHERE customerid = 1;
<<<<<<< HEAD
*/

-- Drop the existing booking table
/*DROP TABLE IF EXISTS booking;






=======
>>>>>>> c23b03cee851e4a31fbf205b0a87f362dada3572
