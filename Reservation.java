import java.sql.*;
import java.util.*;
import java.sql.Date;


public class Reservation {

    public static void reserveTicket() {
        try {
            Scanner sc = new Scanner(System.in);
            Connection con = DBConnection.getConnection();

            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = sc.nextInt();
            System.out.print("Train Number: ");
            int trainNo = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps1 = con.prepareStatement(
                    "SELECT train_name FROM trains WHERE train_no=?"
            );
            ps1.setInt(1, trainNo);
            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("Invalid Train Number");
                return;
            }

            String trainName = rs.getString(1);
            System.out.println("Train Name: " + trainName);

            System.out.print("Class Type: ");
            String classType = sc.nextLine();
            System.out.print("Journey Date (yyyy-mm-dd): ");
            String date = sc.nextLine();
            System.out.print("From: ");
            String from = sc.nextLine();
            System.out.print("To: ");
            String to = sc.nextLine();

            long pnr = System.currentTimeMillis();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO reservations VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setLong(1, pnr);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setInt(4, trainNo);
            ps.setString(5, trainName);
            ps.setString(6, classType);
            ps.setDate(7, Date.valueOf(date));
            ps.setString(8, from);
            ps.setString(9, to);

            ps.executeUpdate();
            System.out.println("✅ Ticket Reserved");
            System.out.println("PNR Number: " + pnr);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
