
--
-- User hotel
--

create user 'adminHotel'@'localhost' identified by '1234';
GRANT SELECT, INSERT, UPDATE, DELETE ON `hotel`.* TO 'adminHotel'@'localhost';