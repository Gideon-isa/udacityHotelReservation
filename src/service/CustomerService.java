package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * To Store, retrieve and process Customer's information
 *
 * @author Gideon Isa
 * February 04, 2022
 */

public class CustomerService {

    private final Collection<Customer> listOfCustomers = new HashSet<>();
    private final Map<String, Customer> mapOfCustomers = new HashMap<>();

    // Adding a static reference to make sure only one instance of this class is created
    public static CustomerService customerServiceInstance;


    public CustomerService() {}

    /**
     * To Limit the instantiating of customer service to be only one
     *
     * @return - CustomerService
     */
    public static CustomerService getInstance() {
        if(customerServiceInstance == null) {
            customerServiceInstance = new CustomerService();
        }
        return customerServiceInstance;
    }

    /**
     * To add new customers to the customers collection
     *
     * @param firstName - customer's first name
     * @param lastName - customer's last name
     * @param email - customer's email address
     */
    public void addCustomer(String firstName, String lastName, String email) {
        Customer newCustomer = new Customer(firstName, lastName, email);

        listOfCustomers.add(newCustomer);

        // adding newly added customer to map for quick search
        mapOfCustomers.put(email, newCustomer);

        // Displaying the Customer's information
        System.out.println(customerServiceInstance.getCustomer(email));

    }

    /**
     * It provides access to get Customer's information
     *
     * @param customerEmail - Customer's registered email addresses
     * @return - Customer Results
     */
    public Customer getCustomer(String customerEmail) {
        return (mapOfCustomers.getOrDefault(customerEmail, null));

    }

    /**
     * To retrieve all registered customers' information
     *
     * @return Collection of Customer - Results
     */
    public Collection<Customer> getAllCustomer() {
        return listOfCustomers;
    }



}
