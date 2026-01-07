import java.util.*;

public class Main {
    public static void main(String[] args) {

        if (!Login.authenticate()) {
            System.out.println("Invalid Login ❌");
            return;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Reserve Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Reservation.reserveTicket();
                    break;
                case 2:
                    Cancellation.cancelTicket();
                    break;
                case 3:
                    System.out.println("Thank You!");
                    System.exit(0);
            }
        }
    }
}
