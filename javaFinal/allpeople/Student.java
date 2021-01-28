package allpeople;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import courses.Course;
import courses.Session;
import courses.StudentCourse;
import java.util.List;
import utilities.DataValidation;

/**
 * This class will represent a student object and it will contain the basic data and information for a college student.
 * 
 * @author  : Utsav Parajuli
 * @version : 3.0
 * @since   : 11/29/2020
 */
public class Student extends Person {

    private Date                     birthDate;         //birth date of student
    private Date                     startDate;         //date the student joined college
    private double                   gpa;               //gpa of the student
    private List<String>             courseWishlist;    //list of courses the student wishes to take
    private ArrayList<Course>        scheduledCourses;  //courses that the student is scheduled for
    private ArrayList<Session>       sessions;          //sessions of courses the student is scheduled for
    private ArrayList<StudentCourse> courses;           //list of courses the student is scheduled for
    


    /**
     * This is a constructor for the Student class. It will initialize all the data members of the person class[the parent class] as well
     * as the student class itself
     * 
     * @param firstName     : The first name of student
     * @param middleName    : middle name of the student
     * @param lastName      : last name of the student
     * @param email         : email of the student
     * @param phoneNum      : phone number of the student
     * @param streetAddress : street address of the student
     * @param city          : city the student lives in
     * @param state         : state the student lives in
     * @param zipCode       : zip code of the student
     * @param birthDate     : birthdate of the student
     * @param gpa           : gpa of the student
     * @param startDate     : date the student joined college
     * @throws Exception    : throws input mismatch exception
     */
    public Student(String firstName, String middleName, String lastName, 
                   String email, String phoneNum, String streetAddress, 
                   String city, String state, long zipCode,
                   String birthDate, double gpa, String startDate) 
                   throws Exception {
            super(firstName, middleName, lastName, email, phoneNum, streetAddress, city, state, zipCode);
        
        setBirthDate(birthDate);
        this.gpa = gpa;
        setStartDate(startDate);
        scheduledCourses = new ArrayList<>();
        courseWishlist = new ArrayList<>();
        sessions = new ArrayList<>();
        courses = new ArrayList<>();
    }
    
    /**
     * This method will check if the student has a certain course that they are already taking
     * @param c : a course
     * @return : a boolean value whether the student is already taking that course or not. If they are taking the course it will return true else false.
     */
    public boolean containsCourse(Course c)
    {
        return (scheduledCourses.contains(c));
    }

    /**
     * This method will add a studentCourse object to the list of courses. Student Course object only contains the course id and description
     * @param sc : a student course object
     */
    public void addStudentCourses(StudentCourse sc)
    {
        courses.add(sc);
    }

    /**
     * This method will add a course into the list of courses taking by the student
     * @param c : a course object
     */
    public void addCourses(Course c)
    {
        scheduledCourses.add(c);
    }

    /**
     * This method will add a session to the list of sessions the student is in
     * @param s : a session object
     */
    public void addSessions(Session s)
    {
        sessions.add(s);
    }

    /**
     * This method will return the list of sessions the student is in
     * @return ; the arraylist of session
     */
    public ArrayList<Session> getSessions()
    {
        return sessions;
    }
    
    /**
     * This method will take in a line of string that contains several classes the student wants to take and will convert that line
     * into a list of several strings which correspond to the course id.
     * @param wishlist : the string of classes student wishes to take
     */
    public void setCourseWishlist(String wishlist)
    {
        String[] coursesArray = wishlist.split("\\s+");

        courseWishlist = Arrays.asList(coursesArray);
    }

    /**
     * This method will return the list of course the student wishes to take
     * @return : the courses student wants to take
     */
    public List<String> getCourseWishlist()
    {
        return courseWishlist;
    }

    /**
     * This method will return the list of scheduled courses
     * @return : the courses student is scheduled for
     */
    public ArrayList<Course> getFullCourses()
    {
        return scheduledCourses;
    }

    /**
     * This method will return the list of abbreviated scheduled courses
     * @return : the list of courses
     */
    public ArrayList<StudentCourse> getCourses()
    {
        return courses;
    }

    /**
     * This method will return the birthdate of the student
     * @return : the birthdate
     */
    public Date getBirthDate() {
        return this.birthDate;
    }

    /**
     * This method will set the birthdate of the student
     * @param birthDate : the birthdate
     * @throws Exception : throws an exception for invalid input
     */
    public void setBirthDate(String birthDate) throws Exception{
        DataValidation.ensureNonEmptyString("dd/MM/yyyy", birthDate);
        this.birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
    }

    /**
     * This method will return the GPA of the student
     * @return : the gpa
     */
    public double getGpa() {
        return this.gpa;
    }

    /**
     * This method will set the GPA of the student
     * @param gpa : the gpa
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     * This method will return the date the student joined the college
     * @return : the startDate
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * This method will set the date the student joined the college
     * @param startDate : the date student started in college
     * @throws Exception : throws an exception for invalid input
     */
    public void setStartDate(String startDate) throws Exception{
        DataValidation.ensureNonEmptyString("dd/MM/yyyy", startDate);
        this.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        DataValidation.ensureValidDate(this.birthDate, this.startDate);
    }

    /**
     * This method will override the toString method for student class. It will return all the data of the class in a string format
     */
    @Override
    public String toString() {
        return super.toString() + 
        "\nDate of Birth: " + getBirthDate().toString().substring(0, 10) + getBirthDate().toString().substring(23, 28) +
        "\nGPA: " + getGpa() +
        "\nJoined: " + getStartDate().toString().substring(0, 10) + getStartDate().toString().substring(23, 28);
    }
}
