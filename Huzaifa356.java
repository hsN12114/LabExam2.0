class MovieBookingApp {
    private int available_Seats = 20; 
    private final Object lock = new Object(); 

    public void booking(int seatsToBook, String user) {
        synchronized (lock) {
            if (seatsToBook <= 0) {
                System.out.println(user + ": Wrong Number of Seats.");
                return;
            }
            if (seatsToBook > available_Seats) {
                System.out.println(user + ": No seats available.");
                return;
            }
            available_Seats -= seatsToBook;
            System.out.println(user + " Booked " + seatsToBook + " seats.");
            System.out.println("Total booked seats: " + (20 - available_Seats) + ", Remaining seats: " + available_Seats);
        }
    }
    public static void main(String[] args) {
        MovieBookingApp app = new MovieBookingApp();
        Thread userA = new Thread(() -> {
            try {
                app.booking(10, "User A");
            } catch (Exception e) {
                System.out.println("Error in User A booking: " + e.getMessage());
            }
        });
        Thread userB = new Thread(() -> {
            try {
                app.booking(12, "User B");
            } catch (Exception e) {
                System.out.println("Error in User B booking: " + e.getMessage());
            }
        });
        userA.start();
        userB.start();
    }
}
