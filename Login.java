import java.sql.*;
import java.util.*;

public class Login {
    public static boolean authenticate() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Username: ");
            String user = sc.next();
            System.out.print("Password: ");
            String pass = sc.next();

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?"
            );
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}

