import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FlightServlet")
public class FlightServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try (Connection conn = DatabaseUtil.getConnection()) {
            if ("checkAvailability".equals(action)) {
                checkAvailability(conn, request, response);
            } else if ("bookTicket".equals(action)) {
                bookTicket(conn, request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkAvailability(Connection conn, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String flightNumber = request.getParameter("flightNumber");
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flights WHERE FlightNumber = ?");
        ps.setString(1, flightNumber);
        ResultSet rs = ps.executeQuery();

        PrintWriter out = response.getWriter();
        while (rs.next()) {
            out.println("Flight Number: " + rs.getString("FlightNumber"));
            out.println("Origin: " + rs.getString("Origin"));
            out.println("Destination: " + rs.getString("Destination"));
            out.println("Departure Date: " + rs.getDate("DepartureDate"));
            out.println("Arrival Date: " + rs.getDate("ArrivalDate"));
            out.println("Available Seats: " + rs.getInt("AvailableSeats"));
            out.println("Class: " + rs.getString("ClassType"));
        }
    }

    private void bookTicket(Connection conn, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int flightID = Integer.parseInt(request.getParameter("flightID"));
        String customerName = request.getParameter("customerName");
        String email = request.getParameter("email");
        int seats = Integer.parseInt(request.getParameter("seats"));

        // Check availability
        PreparedStatement ps = conn.prepareStatement("SELECT AvailableSeats FROM Flights WHERE FlightID = ?");
        ps.setInt(1, flightID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int availableSeats = rs.getInt("AvailableSeats");
            if (availableSeats >= seats) {
                // Book the ticket
                ps = conn.prepareStatement("INSERT INTO Reservations (FlightID, CustomerName, Email, SeatsReserved) VALUES (?, ?, ?, ?)");
                ps.setInt(1, flightID);
                ps.setString(2, customerName);
                ps.setString(3, email);
                ps.setInt(4, seats);
                ps.executeUpdate();

                // Update available seats
                ps = conn.prepareStatement("UPDATE Flights SET AvailableSeats = AvailableSeats - ? WHERE FlightID = ?");
                ps.setInt(1, seats);
                ps.setInt(2, flightID);
                ps.executeUpdate();

                response.getWriter().println("Booking confirmed for " + customerName);
            } else {
                response.getWriter().println("Not enough available seats");
            }
        }
    }
}
