ALTER TABLE employee
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
