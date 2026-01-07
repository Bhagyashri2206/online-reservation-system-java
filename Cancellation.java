import java.sql.*;
import java.util.*;

public class Cancellation {

    public static void cancelTicket() {
        try {
            Scanner sc = new Scanner(System.in);
            Connection con = DBConnection.getConnection();

            System.out.print("Enter PNR: ");
            long pnr = sc.nextLong();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM reservations WHERE pnr=?"
            );
            ps.setLong(1, pnr);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Invalid PNR");
                return;
            }

            System.out.println("Passenger: " + rs.getString("name"));
            System.out.println("Train: " + rs.getString("train_name"));

            System.out.print("Confirm Cancellation (yes/no): ");
            String confirm = sc.next();

            if (confirm.equalsIgnoreCase("yes")) {
                PreparedStatement del = con.prepareStatement(
                        "DELETE FROM reservations WHERE pnr=?"
                );
                del.setLong(1, pnr);
                del.executeUpdate();
                System.out.println("✅ Ticket Cancelled");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
