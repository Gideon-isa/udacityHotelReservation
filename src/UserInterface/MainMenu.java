package UserInterface;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class MainMenu {
    private static final HotelResource HotelResourceInstance = HotelResource.getInstance();
    private static final CustomerService customerServiceInstance = CustomerService.getInstance();
    private static Collection<Reservation> reservations = new HashSet<>();


    /**
     * The Main Menu of the application
     * This is the User-Interface
     */
    public static void startMainMenu() {

        boolean mainEntry = true;

        while(mainEntry) {

            Scanner input = new Scanner(System.in);

            System.out.println("Welcome to the Hotel Application \n");
            System.out.println("--------------------------------------------");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("--------------------------------------------");
            System.out.println("Please select a number for the menu option.");

            try {

                int myInput = input.nextInt();
                switch (myInput) {
                    case 1 -> {
                        //Find and reserve a room method
                        Scanner scanDate = new Scanner(System.in);
                        String checkInStringDate;
                        String checkOutStringDate;
                        Date checkOutDate = null;
                        Date checkInDate = null;
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
                        boolean testDate = true;
                        int count = 0;

                        // CheckInDate method
                        while (testDate) {

                            while (count < 2) {

                                try {
                                    System.out.println("Enter CheckIn Date mm/dd/yyyy example 01/27/2022");
                                    checkInStringDate = scanDate.nextLine();
                                    checkInDate = dateFormatter.parse(checkInStringDate);

                                    if (checkInDate.equals(dateFormatter.parse(checkInStringDate))) {
                                        testDate = false;

                                    }
                                } catch (Exception e) {
                                    System.out.println("Please enter with a correct date format.");

                                }

                                if (!testDate) {
                                    count = 3;
                                } else {
                                    count++;
                                }

                            }

                            if (count == 2) {
                                System.out.println("You have entered an incorrect date format twice.");
                                System.out.println("Enter 1 to continue with the room reservation or otherwise.");
                                startMainMenu();
                            }


                        }

                        // setting testDate value to true to run the checkOutDate method
                        testDate = true;

                        //CheckOutDate Method

                        count = 0;
                        while (testDate) {

                            while (count < 2) {

                                try {
                                    System.out.println("Enter CheckOut Date mm/dd/yyyy example 01/27/2022.");
                                    checkOutStringDate = scanDate.nextLine();
                                    checkOutDate = dateFormatter.parse(checkOutStringDate);

                                    if (checkOutDate.equals(dateFormatter.parse(checkOutStringDate))) {
                                        testDate = false;

                                    }
                                } catch (Exception e) {
                                    System.out.println("Please enter with a correct date format.");

                                }

                                if (!testDate) {
                                    count = 3;
                                } else {
                                    count++;
                                }

                            }

                            if (count == 2) {
                                System.out.println("You have entered an incorrect date format twice.");
                                System.out.println("Enter 1 to continue with the room reservation or otherwise.");
                                startMainMenu();
                            }
                        }


                        // This should display only the available rooms
                        Collection<IRoom> roomsAvailable = HotelResourceInstance.findARoom(checkInDate, checkOutDate);

                        // To return to the Main Menu if they are no available rooms
                        if (roomsAvailable.isEmpty()) {
                            startMainMenu();
                        }

                        Scanner toBookInput = new Scanner(System.in);
                        System.out.println("Would you like to book a room? Enter \"y\" for yes or \"n\" for no.");
                        String toBook = toBookInput.nextLine().toLowerCase();
                        if (toBook.equals("y")) {
                            System.out.println("do you have an account with us? Enter \"y\" for yes or \"n\" for no.");
                            // a try and catch
                            Scanner accountInput = new Scanner(System.in);
                            String haveAccount = accountInput.nextLine().toLowerCase();


                            if (haveAccount.equals("y")) {
                                Scanner emailInput = new Scanner(System.in);
                                System.out.println("Enter Email with format: name@domain.com");
                                String inputEmail = emailInput.nextLine().toLowerCase();

                                // I need to check if the customer's email is contained among the list of registered emails
                                Customer customer = HotelResourceInstance.getCustomer(inputEmail);
                                if (!(customer == null)) {
                                    System.out.println("What room number would you like to reserve from the available rooms list below?");

                                    // To store available room numbers
                                    Collection<String> availableRoomNumber = new HashSet<>();


                                    // To print all available room and store available room numbers
                                    for (IRoom room : roomsAvailable) {
                                        System.out.println(room);
                                        availableRoomNumber.add(room.getRoomNumber());
                                    }


                                    System.out.println("Enter only the room number you like to reserve.");
                                    Scanner toReserveInput = new Scanner(System.in);
                                    String roomNumber = toReserveInput.nextLine();

                                    if (availableRoomNumber.contains(roomNumber)) {
                                        IRoom room = HotelResourceInstance.getRoom(roomNumber);
                                        reservations.add(HotelResourceInstance.bookARoom(customer.getEmail(), room, checkInDate, checkOutDate));
                                    } else {
                                        System.out.println("Invalid input. \n Please repeat the process.");
                                        System.out.println("The room number you entered is not among the list of available rooms.");
                                        startMainMenu();
                                    }


                                } else {
                                    System.out.println("Your email is not registered with us . Please create an account.");
                                    startMainMenu();
                                }

                            } else if (haveAccount.equals("n")) {
                                startMainMenu();
                            }

                        } else if (toBook.equals("n")) {
                            startMainMenu();
                        } else {
                            System.out.println("Please enter either y for yes or n for no.");
                        }
                        startMainMenu();
                    }
                    case 2 -> {
                        // see my reservation method
                        Scanner scanCustomerEmail = new Scanner(System.in);
                        System.out.println("Enter your email address used in your reservation.");
                        String customerEmail = scanCustomerEmail.nextLine();
                        reservations = HotelResourceInstance.getCustomersReservations(customerEmail);
                        for (Reservation newReservation : reservations) {
                            System.out.println(newReservation);
                            startMainMenu();
                        }

                    }
                    case 3 -> {
                        //
                        String email = null;
                        String emailRegex = "^(.+)@(.+).com$";
                        String firstName = null;
                        String nameRegex = "^[a-zA-Z]+([ '-]*[a-zA-Z]+)$";
                        String lastName = null;
                        boolean toProceed = true;
                        int count = 0;
                        while (toProceed) {

                            while (count < 2) {

                                try {
                                    Scanner scanEmail = new Scanner(System.in);
                                    System.out.println("Enter your email. Email format: name@domain.com");
                                    email = scanEmail.nextLine().toLowerCase();

                                    Pattern pattern = Pattern.compile(emailRegex);

                                    if (pattern.matcher(email).matches()) {
                                        toProceed = false;
                                    } else {
                                        throw new IllegalArgumentException();
                                    }

                                } catch (Exception e) {
                                    System.out.println("Please enter check your email again.");

                                }


                                if (!toProceed) {
                                    count = 3;
                                } else {
                                    count++;
                                }

                            }

                            if (count == 2) {
                                System.out.println("You have entered an incorrect email format twice.");
                                System.out.println("Enter 1 to continue with the room reservation or otherwise.");
                                startMainMenu();
                            }

                        }
                        count = 0;
                        toProceed = true;
                        while (toProceed) {

                            while (count < 2) {
                                Scanner scanFirstName = new Scanner(System.in);
                                System.out.println("Enter your first name.");
                                firstName = scanFirstName.nextLine();
                                Pattern pattern = Pattern.compile(nameRegex);
                                try {
                                    if (pattern.matcher(firstName).matches()) {
                                        toProceed = false;
                                    } else {
                                        throw new IllegalArgumentException();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Please enter valid name characters");
                                }

                                if (!toProceed) {
                                    count = 3;
                                } else {
                                    count++;
                                }

                            }

                            if (count == 2) {
                                System.out.println("You have entered an incorrect first name twice");
                                System.out.println("Enter 3 to continue with the account creation or otherwise.");
                                startMainMenu();
                            }


                        }
                        count = 0;
                        toProceed = true;
                        while (toProceed) {

                            while (count < 2) {
                                Scanner scanLastName = new Scanner(System.in);
                                System.out.println("Enter your last name.");
                                lastName = scanLastName.nextLine();
                                Pattern pattern = Pattern.compile(nameRegex);
                                try {
                                    if (pattern.matcher(lastName).matches()) {
                                        toProceed = false;
                                    } else {
                                        throw new IllegalArgumentException();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Please enter valid name characters");
                                }

                                if (!toProceed) {
                                    count = 3;
                                } else {
                                    count++;
                                }

                            }
                            if (count == 2) {
                                System.out.println("You have entered an incorrect last name twice.");
                                System.out.println("Enter 3 to continue with the room reservation or otherwise.");
                                startMainMenu();
                            }

                        }
                        HotelResourceInstance.createACustomer(firstName, lastName, email);
                        MainMenu.startMainMenu();
                    }
                    case 4 -> AdminMenu.displayAdmin();
                    case 5 -> {
                        //startMainMenu();;
                        System.out.println("Remember to visit us again soon. Bye");
                        mainEntry = false;
                    }
                    default -> System.out.println("Please select any of the desired option from 1 to 5 as listed above to use the application.\n");


                }
            }catch (Exception e) {
                System.out.println("Invalid input");
            }



        }

    }


}
