CREATE TABLE Students (
    StudentID AUTOINCREMENT PRIMARY KEY,
    Name TEXT,
    Class TEXT,
    DateOfBirth DATE,
    Address TEXT
);

CREATE TABLE Teachers (
    TeacherID AUTOINCREMENT PRIMARY KEY,
    Name TEXT,
    Subject TEXT,
    DateOfBirth DATE,
    Address TEXT
);

CREATE TABLE Staff (
    StaffID AUTOINCREMENT PRIMARY KEY,
    Name TEXT,
    Position TEXT,
    DateOfBirth DATE,
    Address TEXT
);

CREATE TABLE Users (
    UserID AUTOINCREMENT PRIMARY KEY,
    Username TEXT,
    Password TEXT,
    Role TEXT -- Possible values: Admin, Student, Teacher, Staff
);
