import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:ucanaccess://path_to_your_database/SchoolManagementDB.accdb";
    private static final String USER = ""; // Leave empty for MS Access
    private static final String PASSWORD = ""; // Leave empty for MS Access

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
