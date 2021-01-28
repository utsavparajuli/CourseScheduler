package allpeople;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import courses.Course;
import courses.Session;
import utilities.DataValidation;


/**
 * The Faculty class extends the Person class and has all the data for a faculty member
 * 
 * @author  Utsav Parajuli
 * @version 2.0
 * @since   11/29/2020
 */
public class Faculty extends Person implements Cloneable{
    private Date               startDate;        //date the faculty joined
    private boolean            tenured;          //if the faculty is tenured or not
    private List<String>       teachingWishList; //string of courses the faculty wishes to teach
    private ArrayList<Course>  classes;          //courses the faculty is teaching
    private ArrayList<Session> sessions;         //sessions of the courses faculty is teaching

    /**
     * This is a constructor for the Faculty class. It takes in all personal information which will be used to initialize the base class
     * and the remaining data of start date and tenured status will be used to initialize the faculty class.
     * 
     * @param firstName     : First Name of the faculty
     * @param middleName    : Middle Name of the faculty
     * @param lastName      : Last Name of the faculty
     * @param email         : email of the faculty
     * @param phoneNum      : phone number of the faculty
     * @param streetAddress : street address of the faculty
     * @param city          : City where the faculty lives
     * @param state         : State where the faculty lives
     * @param zipCode       : Zip code
     * @param startDate     : Date the faculty joined the school
     * @param tenured       : The position status of the Faculty
     * @throws Exception    : throws Exception on input error
     */
    public Faculty(String firstName, String middleName, String lastName, 
                   String email, String phoneNum, String streetAddress, 
                   String city, String state, long zipCode,
                   String startDate, String tenured) 
                   throws Exception {
           super(firstName, middleName, lastName, email, phoneNum, streetAddress, city, state, zipCode);
        
        setStartDate(startDate);
        setTenured(tenured);

        //initializes the arrayList of courses
        classes = new ArrayList<>();
        sessions = new ArrayList<>();
        teachingWishList = new ArrayList<>();
    }
    

    /**
     * This method will setup a list for the courses the teacher wishes to teach
     * @param wishlist : A string containing various course ids
     */
    public void setTeachingWishlist(String wishlist)
    {
        String[] coursesArray = wishlist.split("\\s+");

        teachingWishList = Arrays.asList(coursesArray);
    }

    /**
     * This method will return the list of strings which contains id of courses the faculty wants to teach
     * @return : the list of course wishlist
     */
    public List<String> getTeachingWishlist()
    {
        return teachingWishList;
    }

    /**
     * This method will set the status whether the Faculty member is tenured or not.
     * 
     * @param tenured : Is the faculty member tenured or not
     * @throws Exception : throws an invalid input exception
     */
    public void setTenured(String tenured) throws Exception{
        if(tenured.equals("Y"))
            this.tenured = true;
        else if(tenured.equals("N"))
            this.tenured = false;
        else
            throw new Exception("Invalid Tenured value");
	}

    /**
     * No arg constructor for faculty
     */
	public Faculty() {
    }

    /**
     * Method that will add a course to the list of courses that the faculty member 
     * @param c : a course object
     */
    public void addCourse(Course c)
    {
        classes.add(c);
    }

    /**
     * Method that will add a session to the list of sessions
     * @param s : a session object
     */
    public void addSession(Session s)
    {
        sessions.add(s);
    }


    /**
     * This method will return the date the faculty joined
     * @return the startDate
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * This method will set the start date for the Faculty
     * @param startDate : The date faculty member joined
     * @throws Exception : throws a input mismatch exception
     */
    public void setStartDate(String startDate) throws Exception{
        DataValidation.ensureNonEmptyString("dd/MM/yyyy", startDate);
        this.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
    }

    /**
     * This method will get the courses the Faculty is teaching
     * @return : the courses faculty plans to teach
     */
    public ArrayList<Course> getCourses()
    {
        return classes;
    }

    /**
     * This method will get the sessions of courses the faculty is teaching
     * @return : list of sessions
     */
    public ArrayList<Session> getSessions()
    {
        return sessions;
    }

    /**
     * This method overrides the toString method which will return a string of all the data of the Faculty class
     * 
     * @return the string representation of the data of Faculty
     */
    @Override
    public String toString() {
        return super.toString() + 
        "\nJoined: " + getStartDate().toString().substring(0, 10) + getStartDate().toString().substring(23, 28) +
        "\nStatus: " + (tenured? "Tenured" : "Not Tenured");
    }

    /**
     * This method will clone(copy) the class object
     * 
     * @return the copied object
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

}
