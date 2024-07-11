import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    public List<Student> getAllStudents() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Students";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("StudentID"),
                    rs.getString("Name"),
                    rs.getString("Class")
                ));
            }
            return students;
        }
    }

    public void addStudent(Student student) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO Students (Name, Class) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setString(2, student.getClassName());
            ps.executeUpdate();
        }
    }

    // Other methods for updating and deleting students
}

public class SubjectManager {
    public List<Subject> getAllSubjects() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Subjects";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            List<Subject> subjects = new ArrayList<>();
            while (rs.next()) {
                subjects.add(new Subject(
                    rs.getInt("SubjectID"),
                    rs.getString("SubjectName")
                ));
            }
            return subjects;
        }
    }

    // Other methods for adding, updating, and deleting subjects
}

public class MarksManager {
    public List<Mark> getMarksByStudent(int studentID) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Marks WHERE StudentID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();

            List<Mark> marks = new ArrayList<>();
            while (rs.next()) {
                marks.add(new Mark(
                    rs.getInt("MarkID"),
                    rs.getInt("StudentID"),
                    rs.getInt("SubjectID"),
                    rs.getInt("MarksObtained")
                ));
            }
            return marks;
        }
    }

    public void addMark(Mark mark) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO Marks (StudentID, SubjectID, MarksObtained) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, mark.getStudentID());
            ps.setInt(2, mark.getSubjectID());
            ps.setInt(3, mark.getMarksObtained());
            ps.executeUpdate();
        }
    }

    // Other methods for updating and deleting marks
}
