CREATE DATABASE SchoolDB;

USE SchoolDB;

-- Table for Students
CREATE TABLE Students (
    StudentID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    Class VARCHAR(10)
);

-- Table for Subjects
CREATE TABLE Subjects (
    SubjectID INT AUTO_INCREMENT PRIMARY KEY,
    SubjectName VARCHAR(100)
);

-- Table for Marks
CREATE TABLE Marks (
    MarkID INT AUTO_INCREMENT PRIMARY KEY,
    StudentID INT,
    SubjectID INT,
    MarksObtained INT,
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);

INSERT INTO Students (Name, Class) VALUES ('Alice', '10th');
INSERT INTO Students (Name, Class) VALUES ('Bob', '10th');

INSERT INTO Subjects (SubjectName) VALUES ('Math');
INSERT INTO Subjects (SubjectName) VALUES ('English');

INSERT INTO Marks (StudentID, SubjectID, MarksObtained) VALUES (1, 1, 90);
INSERT INTO Marks (StudentID, SubjectID, MarksObtained) VALUES (1, 2, 85);
INSERT INTO Marks (StudentID, SubjectID, MarksObtained) VALUES (2, 1, 78);
INSERT INTO Marks (StudentID, SubjectID, MarksObtained) VALUES (2, 2, 82);
