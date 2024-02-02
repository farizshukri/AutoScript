CREATE TABLE Users (
    UserID INT PRIMARY KEY,
    Username VARCHAR(50),
    Password VARCHAR(50),
    Balance DECIMAL(10, 2)
);
INSERT INTO Users (UserID, Username, Password, Balance) VALUES (1, 'user1', 'password1', 1000.00);
