package UserInterface;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;

import java.util.*;

/**
 * The Admin User Interface to interact with the application
 *
 * @author Gideon Isa
 * February 04, 2022
 */
public class AdminMenu {

    public AdminMenu adminMenu;

    private AdminMenu() {}

    private static final List<IRoom> listOfRoom = new ArrayList<>();
    private static final AdminResource adminResourceInstance = AdminResource.getInstance();
    private static final CustomerService customerServiceInstance = CustomerService.getInstance();

    public static void displayAdmin() {


        boolean isTrue = true;

        Scanner input = new Scanner(System.in);

        while (isTrue) {
            System.out.println("Admin Menu");
            System.out.println("-----------------------------------------------");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservation");
            System.out.println("4. Add a Room");
            System.out.println("5. Get customer");
            System.out.println("6. Back to Main Menu");
            System.out.println("-----------------------------------------------");
            System.out.println("Please select a number from the new option");

            try {

                int myInput = input.nextInt();

                switch (myInput) {
                    case 1 -> {
                        Collection<Customer> listOfCustomers = customerServiceInstance.getAllCustomer();
                        if(listOfCustomers.isEmpty()) {
                            System.out.println("No customers yet");
                        }
                        for (Customer customer : listOfCustomers) {
                            System.out.println(customer);
                        }
                    }

                    case 2 -> {
                        Collection<IRoom> collectionOfRooms = adminResourceInstance.getAllRooms();
                        for (IRoom room : collectionOfRooms) {
                            System.out.println(room);
                        }
                    }

                    case 3 -> {
                        adminResourceInstance.displayAllReservations();
                    }

                    case 4 -> {
                        // Add a room
                        boolean toContinue = true;
                        String roomNumber;
                        Double price;
                        RoomType roomType = null;
                        int customerInput;

                        try {
                            while (toContinue) {

                                Scanner addRoomInput = new Scanner(System.in);

                                System.out.println("Enter room number.");
                                roomNumber = addRoomInput.nextLine();

                                if (!listOfRoom.isEmpty()) {
                                    for (IRoom uniqueRom : listOfRoom) {
                                        if (uniqueRom.getRoomNumber().equals(roomNumber)){
                                            System.out.println("This room number has already been entered \n");
                                            AdminMenu.displayAdmin();
                                        }
                                    }
                                }

                                System.out.println("Enter price per night.");
                                price = addRoomInput.nextDouble();


                                do {
                                    System.out.println("Enter room type: enter 1 for single bed or 2 for double bed.");
                                    Scanner roomTypeInput = new Scanner(System.in);
                                    customerInput = roomTypeInput.nextInt();
                                    if (customerInput == 1) {
                                        roomType = RoomType.SINGLE;
                                    }else if(customerInput == 2) {
                                        roomType = RoomType.DOUBLE;
                                    }else if(customerInput == 0) {
                                        AdminMenu.displayAdmin();
                                    }else{
                                        System.out.println("You entered an invalid input for room type");
                                        System.out.println("Enter \"0\" if you would like to exit the process or enter a valid input as given below \n");
                                    }

                                }while(!(customerInput == 1 || customerInput == 2));

                                // storing the room to my collection

                                IRoom room = new Room(roomNumber, price, roomType);

                                // Adding the newly created room to the list of Rooms and,
                                // Adding it also to the reservation class
                                listOfRoom.add(room);
                                adminResourceInstance.addRoom(listOfRoom);

                                System.out.println("Room added successfully \n");
                                System.out.println("Would you like to add another room?");
                                Scanner secondInput = new Scanner(System.in);

                                boolean toAsk = true;

                                while (toAsk) {
                                    System.out.println("Enter \"y\" to add another room or enter \"n\" not to.");        ;
                                    String newInput = secondInput.nextLine().toLowerCase();

                                    if(newInput.equals("y")) {
                                        toAsk = false;
                                    }else if(newInput.equals("n")) {
                                        toAsk = false;
                                        toContinue = false;
                                        displayAdmin();
                                    }else {
                                        System.out.println("Enter a valid answer.");
                                        //toContinue = false;
                                        //displayAdmin(
                                    }
                                }

                            }
                        }catch (Exception e) {
                            System.out.println("Invalid input.\nPlease start the process again \n");
                            displayAdmin();
                        }
                    }


                    case 5 -> {
                        // get customer
                        System.out.println("Enter customer's email");
                        Scanner getCustomerEmail = new Scanner(System.in);
                        String inputEmail = getCustomerEmail.nextLine();
                        Customer customer = HotelResource.hotelResourceInstance.getCustomer(inputEmail);
                        System.out.println(customer);
                    }

                    case 6 -> {
                        isTrue = false;
                        MainMenu.startMainMenu();
                    }

                    default -> System.out.println("Please select any of the desired option from 1 to 5 as listed above to use the application.\n");


                }
            }catch (Exception e) {
                System.out.println("An Invalid input");
            }

        }


    }




}
