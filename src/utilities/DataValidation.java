package utilities;

import java.util.Date;

/**
 * This is a utility class that will validate data.
 * 
 * @author  : Utsav Parajuli
 * @version : 1.5
 * @since   : 12/01/2020
 */
public class DataValidation {

    /**
     * Private constructor for the class so that an object for this class cannot be declared
     */
    private DataValidation() {}

    /**
     * This method will ensure that the String is not empty
     * @param propertyName : the property of string
     * @param value        : the value of string
     * @throws Exception   : throws exception if the string is empty
     */
    public static void ensureNonEmptyString(String propertyName, String value) throws Exception
    {
        if(value == null || value.trim().equals(""))
            throw new Exception(String.format("%s cannot be null or empty", propertyName));
    }

    /**
     * This method will ensure that an object is not null
     * @param propertyName : The name of the object
     * @param value        : the object to check
     * @throws Exception   : throws an exception if the object is empty
     */
    public static void ensureObjectNotNull(String propertyName, Object value) throws Exception
    {
        if(value == null)
            throw new Exception(String.format("%s cannot be null", propertyName));
    }

    /**
     * This method will ensure a valid date
     * @param birthDate  : the birthdate
     * @param startDate  : the startdate
     * @throws Exception : throws exception if the start date of school is before the birthdate
     */
    public static void ensureValidDate(Date birthDate, Date startDate) throws Exception{
        if(startDate.before(birthDate) || startDate.equals(birthDate))
        {
            throw new Exception("The date student started school is before their birth date.");
        }
    }
}
