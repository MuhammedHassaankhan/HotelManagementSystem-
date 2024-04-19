package HotelBookingSystem;

import java.util.*;
import java.time.*;

class Guest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String idCardNumber;
    private int age;

    public Guest(String name, String email, String password, String phoneNumber, String idCardNumber, int age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.idCardNumber = idCardNumber;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}

class Room {
    private int roomNumber;
    private double price;
    private boolean dirty;

    public Room(int roomNumber, double price) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.dirty = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public boolean isDirty() {
        return dirty;
    }
}

class Reservation {
    private Guest guest;
    private List<Integer> roomNumbers;
    private LocalDate checkInDate;
    private int days;

    public Reservation(Guest guest, List<Integer> roomNumbers, LocalDate checkInDate, int days) {
        this.guest = guest;
        this.roomNumbers = roomNumbers;
        this.checkInDate = checkInDate;
        this.days = days;
    }

    public Guest getGuest() {
        return guest;
    }

    public List<Integer> getRoomNumbers() {
        return roomNumbers;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public int getDays() {
        return days;
    }
}

class HotelManagementSystem {
    private List<Guest> guests;
    public List<Reservation> reservations; // Change access modifier to public
    private List<Room> rooms;
    private int totalMealsOrdered;

    public HotelManagementSystem() {
        guests = new ArrayList<>();
        reservations = new ArrayList<>();
        rooms = new ArrayList<>();
        totalMealsOrdered = 0;

        double[] roomPrices = {100, 110, 120, 111, 112, 118, 128, 130, 140, 150};
        for (int i = 0; i < roomPrices.length; i++) {
            rooms.add(new Room(i + 1, roomPrices[i]));
        }
    }

    public void signUp(String name, String email, String password, String phoneNumber, String idCardNumber, int age) {
        guests.add(new Guest(name, email, password, phoneNumber, idCardNumber, age));
        System.out.println("Welcome to the hotel management system, " + name + "!");
    }

    public Guest signIn(String email, String password) {
        for (Guest guest : guests) {
            if (guest.getEmail().equals(email) && guest.verifyPassword(password)) {
                return guest;
            }
        }
        return null;
    }

    public Reservation createReservation(Guest guest, List<Integer> roomNumbers, LocalDate checkInDate, int days) {
        for (Integer roomNumber : roomNumbers) {
            Room room = findRoom(roomNumber);
            if (room == null) {
                System.out.println("Room " + roomNumber + " does not exist.");
                return null;
            }
        }

        Reservation reservation = new Reservation(guest, roomNumbers, checkInDate, days);
        reservations.add(reservation);
        System.out.println("Reservation successful.");
        return reservation;
    }

    public void updateReservation(Reservation reservation, List<Integer> newRoomNumbers) {
        if (reservation != null) {
            reservation.getRoomNumbers().clear();
            reservation.getRoomNumbers().addAll(newRoomNumbers);
            System.out.println("Reservation updated successfully.");
        } else {
            System.out.println("Reservation does not exist.");
        }
    }

    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
        System.out.println("Reservation canceled successfully.");
    }

    public void roomManagement() {
        System.out.println("\nRoom Management:");
        for (Room room : rooms) {
            System.out.println("Room Number: " + room.getRoomNumber() + ", Price: $" + room.getPrice() + ", Status: " + (room.isDirty() ? "Dirty" : "Clean"));
        }
    }

    public void housekeepingManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nHousekeeping Management:");
        System.out.print("How many meals would you like to order? ");
        int mealsOrdered = scanner.nextInt();
        totalMealsOrdered += mealsOrdered;
        double mealPrice = 3.0;
        double totalMealCost = mealPrice * mealsOrdered;
        System.out.println("Total Meal Cost: $" + totalMealCost);
        System.out.print("How was the meal? (Good/Bad): ");
        String mealFeedback = scanner.next();
        System.out.println("Thank you for your feedback!");
    }

    public void checkIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCheck-In Management:");
        System.out.println("Please select your check-in date:");
        System.out.println("1. April 1, 2024");
        System.out.println("2. April 2, 2024");
        System.out.println("3. April 3, 2024");
        System.out.println("4. April 4, 2024");
        System.out.println("5. April 5, 2024");
        System.out.println("6. April 6, 2024");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        LocalDate checkInDate;
        switch (choice) {
            case 1:
                checkInDate = LocalDate.of(2024, Month.APRIL, 1);
                break;
            case 2:
                checkInDate = LocalDate.of(2024, Month.APRIL, 2);
                break;
            case 3:
                checkInDate = LocalDate.of(2024, Month.APRIL, 3);
                break;
            case 4:
                checkInDate = LocalDate.of(2024, Month.APRIL, 4);
                break;
            case 5:
                checkInDate = LocalDate.of(2024, Month.APRIL, 5);
                break;
            case 6:
                checkInDate = LocalDate.of(2024, Month.APRIL, 6);
                break;
            default:
                checkInDate = LocalDate.now();
                break;
        }
        System.out.println("You have selected check-in date: " + checkInDate);
    }

    public void checkOut() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCheck-Out Management:");
        System.out.println("Please select your check-out date:");
        System.out.println("1. April 1, 2024");
        System.out.println("2. April 2, 2024");
        System.out.println("3. April 3, 2024");
        System.out.println("4. April 4, 2024");
        System.out.println("5. April 5, 2024");
        System.out.println("6. April 6, 2024");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        LocalDate checkOutDate;
        switch (choice) {
            case 1:
                checkOutDate = LocalDate.of(2024, Month.APRIL, 1);
                break;
            case 2:
                checkOutDate = LocalDate.of(2024, Month.APRIL, 2);
                break;
            case 3:
                checkOutDate = LocalDate.of(2024, Month.APRIL, 3);
                break;
            case 4:
                checkOutDate = LocalDate.of(2024, Month.APRIL, 4);
                break;
            case 5:
                checkOutDate = LocalDate.of(2024, Month.APRIL, 5);
                break;
            case 6:
                checkOutDate = LocalDate.of(2024, Month.APRIL, 6);
                break;
            default:
                checkOutDate = LocalDate.now();
                break;
        }
        System.out.println("You have selected check-out date: " + checkOutDate);
    }

    public void paymentMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPayment Method:");
        System.out.println("Please select your payment method:");
        System.out.println("1. Cash");
        System.out.println("2. JazzCash");
        System.out.println("3. EasyPaisa");
        System.out.println("4. Bank Card");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                System.out.println("Payment successful with Cash.");
                break;
            case 2:
                System.out.println("Payment successful with JazzCash.");
                break;
            case 3:
                System.out.println("Payment successful with EasyPaisa.");
                break;
            case 4:
                System.out.print("Enter bank card number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter card owner's name: ");
                String cardOwner = scanner.nextLine();
                System.out.println("Payment successful with Bank Card (" + cardOwner + ").");
                break;
            default:
                System.out.println("Invalid option. Payment failed.");
                break;
        }
    }

    public void billGeneration(Reservation reservation) {
        double totalPrice = 0;
        for (Integer roomNumber : reservation.getRoomNumbers()) {
            Room room = findRoom(roomNumber);
            if (room != null) {
                totalPrice += room.getPrice();
            }
        }
        System.out.println("Total Room Price: $" + totalPrice);
        System.out.println("Total Meals Ordered: " + totalMealsOrdered);
        System.out.println("Total Bill: $" + (totalPrice + totalMealsOrdered * 3));
        // Additional bill details can be added here
    }

    public void bookingReport(Guest guest) {
        // Generate booking report for the guest
        System.out.println("\nBooking Report:");
        // Display guest details, booking date, check-in date, check-out date, etc.
    }

    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}

public class HotelManagementApp {
    public static void main(String[] args) {
        HotelManagementSystem hotelSystem = new HotelManagementSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Management System!");

        while (true) {
            System.out.println("\n1. Create Reservation");
            System.out.println("2. Room Management");
            System.out.println("3. Housekeeping Management");
            System.out.println("4. Update Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Check-In");
            System.out.println("7. Check-Out");
            System.out.println("8. Payment Method");
            System.out.println("9. Bill Generation");
            System.out.println("10. Booking Report");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Reservation reservation = null;
			switch (option) {
                case 1:
                    // Create Reservation
                    System.out.println("\nCreate Reservation:");
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter your phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter your ID card number: ");
                    String idCardNumber = scanner.nextLine();
                    System.out.print("Enter your age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    hotelSystem.signUp(name, email, password, phoneNumber, idCardNumber, age);

                    System.out.println("\nWould you like to sign in or sign out? (1 for Sign In, 2 for Sign Out): ");
                    int reservationOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (reservationOption == 1) {
                        System.out.print("\nEnter your email: ");
                        String signInEmail = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String signInPassword = scanner.nextLine();
                        Guest guest = hotelSystem.signIn(signInEmail, signInPassword);
                        if (guest != null) {
                            System.out.println("\nWelcome, " + guest.getName() + "!");
                            System.out.println("\nRoom Management:");
                            hotelSystem.roomManagement();
                            System.out.print("How many rooms would you like to book? ");
                            int roomsToBook = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            List<Integer> roomNumbers = new ArrayList<>();
                            for (int i = 0; i < roomsToBook; i++) {
                                System.out.print("Enter room number " + (i + 1) + ": ");
                                int roomNumber = scanner.nextInt();
                                roomNumbers.add(roomNumber);
                            }
                            System.out.print("How many days will you stay? ");
                            int days = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            hotelSystem.createReservation(guest, roomNumbers, LocalDate.now(), days);
                        } else {
                            System.out.println("Guest not found. Please sign up first.");
                        }
                    } else if (reservationOption == 2) {
                        System.out.println("Signed out successfully.");
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case 2:
                    hotelSystem.roomManagement();
                    break;
                case 3:
                    hotelSystem.housekeepingManagement();
                    break;
                case 4:
                    // Update Reservation
                    System.out.print("Enter your email: ");
                    String updateEmail = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String updatePassword = scanner.nextLine();
                    Guest updateGuest = hotelSystem.signIn(updateEmail, updatePassword);
                    if (updateGuest != null) {
                        hotelSystem.bookingReport(updateGuest);
                        // Implement update reservation logic here
                        // Prompt user to update room or meal
                        System.out.println("Would you like to update your room or meal? (Enter 'room' or 'meal'): ");
                        String choice = scanner.nextLine();
                        if (choice.equals("room")) {
                            // Logic to update room
                            System.out.print("Enter new room numbers separated by commas: ");
                            String[] roomNumbersStr = scanner.nextLine().split(",");
                            List<Integer> newRoomNumbers = new ArrayList<>();
                            for (String roomNum : roomNumbersStr) {
                                newRoomNumbers.add(Integer.parseInt(roomNum.trim()));
                            }
                            hotelSystem.updateReservation(reservation, newRoomNumbers);
                        } else if (choice.equals("meal")) {
                            // Logic to update meal
                            // Implement logic here
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("Guest not found. Please sign in first.");
                    }
                    break;
                case 5:
                    // Cancel Reservation
                    System.out.print("Enter your email: ");
                    String cancelEmail = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String cancelPassword = scanner.nextLine();
                    Guest cancelGuest = hotelSystem.signIn(cancelEmail, cancelPassword);
                    if (cancelGuest != null) {
                        hotelSystem.bookingReport(cancelGuest);
                        // Implement cancel reservation logic here
                        System.out.println("Do you want to cancel your reservation? (yes/no): ");
                        String cancelChoice = scanner.nextLine();
                        if (cancelChoice.equalsIgnoreCase("yes")) {
                            // Logic to cancel reservation
                            // Implement logic here
                            hotelSystem.cancelReservation(reservation);
                        } else if (cancelChoice.equalsIgnoreCase("no")) {
                            System.out.println("Reservation not canceled.");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("Guest not found. Please sign in first.");
                    }
                    break;
                case 6:
                    hotelSystem.checkIn();
                    break;
                case 7:
                    hotelSystem.checkOut();
                    break;
                case 8:
                    hotelSystem.paymentMethod();
                    break;
                case 9:
                    // Bill Generation
                    System.out.print("Enter your email: ");
                    String billEmail = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String billPassword = scanner.nextLine();
                    Guest billGuest = hotelSystem.signIn(billEmail, billPassword);
                    if (billGuest != null) {
                        hotelSystem.bookingReport(billGuest);
                        // Implement bill generation logic here
                        Reservation billReservation = null;
                        for (Reservation reservation1 : hotelSystem.reservations) {
                            if (reservation1.getGuest().getEmail().equals(billEmail)) {
                                billReservation = reservation1;
                                break;
                            }
                        }
                        if (billReservation != null) {
                            hotelSystem.billGeneration(billReservation);
                        } else {
                            System.out.println("Reservation not found.");
                        }
                    } else {
                        System.out.println("Guest not found. Please sign in first.");
                    }
                    break;
                case 10:
                    // Booking Report
                    System.out.print("Enter your email: ");
                    String reportEmail = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String reportPassword = scanner.nextLine();
                    Guest reportGuest = hotelSystem.signIn(reportEmail, reportPassword);
                    if (reportGuest != null) {
                        hotelSystem.bookingReport(reportGuest);
                    } else {
                        System.out.println("Guest not found. Please sign in first.");
                    }
                    break;
                case 11:
                    System.out.println("Exiting Hotel Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}