package allpeople;

import java.util.Objects;
import utilities.DataValidation;
import utilities.UUIDGenerator;

/**
 * The Person class is a representation of a person. It contains all the basic information about a person. It includes their name, contact information,
 * address, and a personal id
 * 
 * @author  Utsav Parajuli
 * @version 1.0
 * @since   11/29/2020
 */
public class Person {
    private String firstName;           //First name of the person
    private String middleName;          //Middle name of the person
    private String lastName;            //Last name of the person
    private String email;               //email of the person
    private String phoneNum;            //phone number of the person
    private String streetAddress;       //address of the person
    private String city;                //city the person lives in
    private String state;               //state the person resides in
    private long   zipCode;             //Zip code of the person's city
    private String id;                  //A personal id

    /**
     * A contructor for the person class that will initialize and set all the data members.
     * 
     * @param firstName     : First name of the person
     * @param middleName    : Middle name of the person
     * @param lastName      : Last name of the person
     * @param email         : email of the person
     * @param phoneNum      : phone number of the person
     * @param streetAddress : address of the person
     * @param city          : city the person lives in
     * @param state         : state the person resides in
     * @param zipCode       : Zip code of the person's city
     * @throws Exception    : an input mismatch exception
     */
    public Person(String firstName, String middleName, String lastName, 
                  String email, String phoneNum, String streetAddress, 
                  String city, String state, long zipCode) 
                  throws Exception{

        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNum(phoneNum);
        setStreetAddress(streetAddress);
        setCity(city);
        setState(state);
        this.zipCode = zipCode;
        setID();
    }

    /**
     * A default constructor for the person class
     */
    public Person() {}

    /**
     * This method will generate a id for the person
     */
    public void setID()
    {
        String tempId = new UUIDGenerator().generateId();
        this.id = tempId.substring(0,5);
    }

    /**
     * This method will return the first name of the person
     * @return  : the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the first name of the person
     * @param firstName : the first name of person
     * @throws Exception : input mismatch exception
     */
    public void setFirstName(String firstName) throws Exception {
        DataValidation.ensureNonEmptyString("Person.FirstName", firstName);
        this.firstName = firstName;
    }

    /**
     * Gets the middle name of the person
     * @return : the middle name
     */
    public String getMiddleName() {
        return this.middleName;
    }

    /**
     * sets the middle name of person
     * @param middleName : middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * gets the last name of person
     * @return : last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Gets the full name of the person
     * @return : a new string that combines the first middle and last name
     */
    public String getFullName() {
        return this.firstName + " " + this.middleName + " " + this.lastName;
    }

    /**
     * sets the last name of the person
     * @param lastName : the last name
     * @throws Exception : input mismatch exception
     */
    public void setLastName(String lastName) throws Exception {
        DataValidation.ensureNonEmptyString("Person.LastName", lastName);
        this.lastName = lastName;
    }

    /**
     * returns the email of the person
     * @return : the email
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Sets the email of the person
     * @param email : the email
     * @throws Exception ; input mismatch exception
     */
    public void setEmail(String email) throws Exception {
        DataValidation.ensureNonEmptyString("Person.Email", email);
        this.email = email;
    }

    /**
     * returns the phone number of the person
     * @return : the phone number
     */
    public String getPhoneNum() {
        return this.phoneNum;
    }

    /**
     * sets the phone number of the person
     * @param phoneNum : the phone number
     * @throws Exception : input mismatch exception
     */
    public void setPhoneNum(String phoneNum) throws Exception {
        DataValidation.ensureNonEmptyString("Person.PhoneNum", phoneNum);
        this.phoneNum = phoneNum;
    }

    /**
     * returns the street address of the person
     * @return : the street address
     */
    public String getStreetAddress() {
        return this.streetAddress;
    }

    /**
     * sets the street address of the person
     * @param streetAddress : the street address
     * @throws Exception : throws input mismatch exception
     */
    public void setStreetAddress(String streetAddress) throws Exception {
        DataValidation.ensureNonEmptyString("Person.StreetAddress", streetAddress);
        this.streetAddress = streetAddress;
    }

    /**
     * returns the city person lives in
     * @return : the city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the city person lives in
     * @param city : the city
     * @throws Exception : input mismatch exception
     */
    public void setCity(String city) throws Exception {
        DataValidation.ensureNonEmptyString("Person.City", city);
        this.city = city;
    }

    /**
     * returns the state where the person lives
     * @return : the state
     */
    public String getState() {
        return this.state;
    }

    /**
     * Sets the state where the person lives
     * @param state : the state
     * @throws Exception : input mismatch exception
     */
    public void setState(String state) throws Exception {
        DataValidation.ensureNonEmptyString("Person.State", state);
        this.state = state;
    }

    /**
     * returns the zip code of the person
     * @return : the zipcode
     */
    public long getZipCode() {
        return this.zipCode;
    }

    /**
     * returns the id of the person
     * @return : the personal id
     */
    public String getId() {
        return this.id;
    }

    /** 
     * Overrides the equals method for the person
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return id.equals(person.id);
    }

    /**
     * Hash code for the person object that is generated by the id
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Overrides the toString method that will return the string representation of the object
     */
    @Override
    public String toString() {
        return
            "Name: " + getFirstName() + " " + getMiddleName() + " " + getLastName() +
            "\nEmail: " + getEmail() +
            "\nPhone Number: " + getPhoneNum() +
            "\nAddress: " + getStreetAddress() + ", " + getCity() + ", " + getState() + ", " + getZipCode() +
            "\nID: " + getId();
    }
}