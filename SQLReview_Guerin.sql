.header on
.mode column
-- This is a comment (above commands setup our output)
--------------------- LECTURE OUTLINES AND NOTES ----------------------
-- We are going to create and use student together
  -- Either in the lectures or the videos
-- Then you will complete the below assignments (making & using an Employee table)
-- You will submit the sql commands in a file: "employee.sql" in two weeks.




-- .print is a common SQL command (through its tools) that just says "print a line"
  -- It is not a part of the SQL Language
.print 'The whole table is:'


.print 'How about John & Mary?'

---------------------- START ASSIGNMENT -----------------------------
-- Assignment: Create a Table called Employee with:
   -- A primary key (int) called "eid"
   -- Fields for: first & last name, job title, zipcode, payrate, and deptartment number
   -- payrate is "Double", Zipcode should be "Numeric (how many numbers?)" dept # is int

CREATE TABLE Employee (
   EID int,
   First_Name varchar(255),
   Last_Name varchar(255),
   Job_Title varchar(255),
   Zip numeric(5,0),
   Payrate double,
   Dept_Num int,
   PRIMARY KEY (EID)
);

-- Then insert 3 rows into the table (2 employee's names should start with S & one should have the department id 8 & 2 zipcodes should start with "34" (rest can be made up)

INSERT INTO Employee VALUES ('0', 'Sandra', 'Parker', 'Project Manager', '34292', '70000', '8');
INSERT INTO Employee VALUES ('1', 'Stefan', 'Lopez', 'Software Developer', '10312', '100000', '12');
INSERT INTO Employee VALUES ('2', 'George', 'Martin', 'Data Analyst', '34232', '80000', '3');

-- Get all the data from the table (directly from lecture)
.print 'The whole table is:'

SELECT * FROM Employee;

-- Then select every person that's first name begins with S
.print 'select every employee that''s name begins with "S"'

SELECT * FROM Employee WHERE First_Name LIKE 'S%';

-- Finally select every person whose name starts with S & whose deptartment # isn't 8

SELECT * FROM Employee WHERE First_Name LIKE 'S%' AND NOT Dept_Num='8';

-- But that last person should be in dept 29 so Update their dept # to 29
.print 'Updating department'

UPDATE Employee SET Dept_Num='29' WHERE Dept_Num='8';

.print 'update complete'
-- And the non-'S' person just quit: Delete the person who doesn't start with S
.print 'delete started'

DELETE FROM Employee WHERE First_Name NOT LIKE 'S%';

.print 'delete complete'
.print
.print 'Assignment Complete'