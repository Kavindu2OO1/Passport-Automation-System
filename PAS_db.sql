CREATE DATABASE PASdB;
use PASdB;

Create table Applications(
Application_ID int NOT NULL AUTO_INCREMENT,
 National_ID varchar(12) NOT NULL,
First_Name varchar (50) NOT NULL,
Last_Name varchar (50) NOT NULL,
Other_Names varchar (100),
Gender varchar(6) NOT NULL,
Place_Of_Birth varchar(50) NOT NULL,
Job varchar (50),
Mobile_Number varchar(10),
Email varchar(255) NOT NULL,




PRIMARY KEY (Application_ID)

);

DELETE FROM Applications WHERE Application_ID='22';

DELETE FROM Application_Status WHERE Application_ID='22';

ALTER TABLE Applications
ADD Birth_certificate_img blob;

ALTER TABLE Applications
ADD ID_img LONGBLOB;

ALTER TABLE Applications
MODIFY COLUMN Birth_certificate_img LONGBLOB;
ALTER TABLE Applications
MODIFY COLUMN Pas_photo_img LONGBLOB;






ALTER TABLE Applications
DROP COLUMN Pas_photo_img;


ALTER TABLE Applications
ADD Birth_certificate_img blob;
ALTER TABLE Applications
ADD Pas_photo_img blob;

 

DELIMITER //

CREATE TRIGGER ScheduleAppointment
AFTER INSERT
ON Applications
FOR EACH ROW
BEGIN
    -- Calculate the appointment date (for example, 7 days from the current date)
    DECLARE appointment_date DATE;
    SET appointment_date = DATE_ADD(CURDATE(), INTERVAL 7 DAY); -- Adjust as needed
    
    -- Update the newly inserted row with the appointment date
    UPDATE Applications
    SET Appointment_Date = appointment_date
    WHERE Application_ID = NEW.Application_ID;
END;

//

DELIMITER ;
DROP TRIGGER IF EXISTS ScheduleAppointment;





ALTER TABLE Applications
MODIFY National_ID varchar(12) NOT NULL;


select * from Applications;

SET SQL_MODE = 'STRICT_ALL_TABLES';

INSERT INTO Applications
    (National_ID, First_Name, Last_Name, Other_Names, Gender, Place_Of_Birth, Job, Mobile_Number, Email)
VALUES
    (200212902360,'kavindu','Smith', 'Marie', 'Female', 'New York', 'Software Engineer', '5551234567', 'alice@example.com');


DELETE FROM Applications
WHERE Last_Name = 'smith';
select * from ApplicationIDView;
CREATE VIEW ApplicationIDView AS
SELECT Application_ID
FROM Applications;
Select *  from Application_Status;

CREATE TABLE Application_Status(
ASID int NOT NULL AUTO_INCREMENT,
Application_ID varchar(12),
Application_Status varchar (50),
PRIMARY KEY (ApplicationStatusID),
appoinmant_date date,
FOREIGN KEY (Application_ID) REFERENCES Applications(Application_ID)
);

alter table Application_Status add appoinmant_date date;


DELIMITER //

CREATE TRIGGER UpdateApplicationStatus
AFTER INSERT
ON Applications
FOR EACH ROW
BEGIN
    
    DECLARE appointment_date DATE;
    SET appointment_date = DATE_ADD(CURDATE(), INTERVAL 2 DAY); -- Adjust as needed
    
    
    INSERT INTO Application_Status (Application_ID, Application_Status, appoinmant_date)
    VALUES (NEW.Application_ID, "Verification pending", appointment_date);
END;
//

DELIMITER ;

DROP TRIGGER IF EXISTS UpdateApplicationStatus;

SELECT ID_img, Birth_certificate_img, Pas_photo_img FROM applications WHERE Application_ID = 26;
