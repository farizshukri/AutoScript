import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ATMServlet")
public class ATMServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "your_db_user";
    private static final String DB_PASS = "your_db_password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int userId = Integer.parseInt(request.getParameter("userId"));
        double amount = Double.parseDouble(request.getParameter("amount"));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            if ("withdraw".equals(action)) {
                withdraw(conn, userId, amount);
            } else if ("deposit".equals(action)) {
                deposit(conn, userId, amount);
            } else if ("checkBalance".equals(action)) {
                double balance = checkBalance(conn, userId);
                response.getWriter().print("Balance: " + balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void withdraw(Connection conn, int userId, double amount) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT Balance FROM Users WHERE UserID = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            double balance = rs.getDouble("Balance");
            if (balance >= amount) {
                ps = conn.prepareStatement("UPDATE Users SET Balance = Balance - ? WHERE UserID = ?");
                ps.setDouble(1, amount);
                ps.setInt(2, userId);
                ps.executeUpdate();
            } else {
                throw new Exception("Insufficient funds");
            }
        }
    }

    private void deposit(Connection conn, int userId, double amount) throws Exception {
        PreparedStatement ps = conn.prepareStatement("UPDATE Users SET Balance = Balance + ? WHERE UserID = ?");
        ps.setDouble(1, amount);
        ps.setInt(2, userId);
        ps.executeUpdate();
    }

    private double checkBalance(Connection conn, int userId) throws Exception {
        PreparedStatement ps = conn.prepareStatement("SELECT Balance FROM Users WHERE UserID = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getDouble("Balance");
        } else {
            throw new Exception("User not found");
        }
    }
}
