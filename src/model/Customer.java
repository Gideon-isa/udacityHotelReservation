package model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * A Customer class for modeling customer objects
 *
 * @author Gideon Isa
 * February 04, 2022
 *
 */

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);

        if(pattern.matcher(email).matches()) {
            this.email = email;
        }else {
            throw new IllegalArgumentException("\n Error, Invalid email");
        }

    }

    /**
     * An accessor method to get customer's first name.
     *
     * @return - String - Result
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * An accessor method to get customer's last name.
     *
     * @return - String - Result
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * An accessor method to get the customer email
     *
     * @return - String - Result
     */
    public String getEmail() {
        return email;
    }

    /**
     * To give a description of the customer
     *
     * @return - String Results
     */
    @Override
    public String toString() {
        return "Customer details:- " +
                "FirstName: " + firstName +
                ", LastName: " + lastName +
                ", Email: " + email + "\n";
    }

    /**
     * It overrides ONLY the email in terms of equality.
     * This is so because customers can have the same firstName and lastName but Never the same email address
     * So the Only unique key is the email address.
     *
     * @param o - Object to be compared to
     * @return - boolean - Result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    /**
     * To return an integer of the hashcode
     * @return - int - Result
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
