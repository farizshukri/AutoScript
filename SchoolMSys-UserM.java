import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

    public void addUser(String username, String password, String role) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO Users (Username, Password, Role) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();
        }
    }

    public void updateUser(int userID, String username, String password, String role) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "UPDATE Users SET Username = ?, Password = ?, Role = ? WHERE UserID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setInt(4, userID);
            ps.executeUpdate();
        }
    }

    public void deleteUser(int userID) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "DELETE FROM Users WHERE UserID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.executeUpdate();
        }
    }

    public ResultSet getAllUsers() throws SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String query = "SELECT * FROM Users";
        PreparedStatement ps = conn.prepareStatement(query);
        return ps.executeQuery();
    }
}
