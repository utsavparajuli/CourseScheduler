package courses;

import java.util.ArrayList;
import allpeople.Faculty;
import allpeople.Student;

/**
 * This class represents a course. It contains a course id, description, several sessions inside the course, the students in the course,
 * the faculties teaching the course and the minimum and maximum number of students allowed to join the course.
 * 
 * @author  : Utsav Parajuli
 * @version : 4.0
 * @since   : 12/01/2020
 */
public class Course {
    private int                numOfSessions;       //number of sessions initially scheduled
    private int                max;                 //maximum number of students allowed in course
    private int                min;                 //minimum number of students for the course to be valid
    private String             courseId;            //course id
    private String             courseCode;          //course code
    private String             description;         //description of the course
    private ArrayList<Session> sessionList;         //list of sessions intially scheduled
    private ArrayList<Session> scheduledSession;    //list of sessions that are scheduled after sorting and deleting the cancelled sessions
    private ArrayList<Student> students;            //list of students in the course
    private ArrayList<Faculty> faculties;           //list of faculties in the course



    /**
     * Default constructor for the course object
     */
    public Course() {
    }

    /**
     * Constructor for the course object. It will initialize basic information about the course like, name, id, min and max students allowed.
     * It will also initialze the list of students, sessions, and faculty
     * @param courseId      : the id of the course
     * @param courseCode    : course code
     * @param description   : description of the course
     * @param numOfSessions : number of sessions initially scheduled in course
     * @param max           : max number of students in course
     * @param min           : minimum number of students
     */
    public Course(String courseId, String courseCode, String description, int numOfSessions, int max, int min) {
        this.courseId      = courseId;
        this.courseCode    = courseCode;
        this.description   = description;
        this.numOfSessions = numOfSessions;
        this.max           = max;
        this.min           = min;

        sessionList        = new ArrayList<>();
        students           = new ArrayList<>();
        scheduledSession   = new ArrayList<>();
        faculties          = new ArrayList<>();

    }

    /**
     * This method will add a faculty to the course
     * @param f : faculty object
     */
    public void addFaculty(Faculty f)
    {
        faculties.add(f);
    }

    /**
     * This method will return the list of faculties
     * @return : the list of faculties for the course
     */
    public ArrayList<Faculty> getFaculties()
    {
        return faculties;
    }

    /**
     * This method will check if the course can be taught or not
     * @return : returns true if the minimum requirement of students is met. Returns false if the minimum requirement is not met
     */
    public boolean canTeachCourse()
    {
        return students.size() >= min;
    }

    /**
     * This method will add the session to the list of scheduled sessions
     * @param s : session to be added
     */
    public void scheduledSession(Session s)
    {
        scheduledSession.add(s);
    }

    /**
     * This method will check of the course is full
     * @return : returns true if the course is not full. Returns false if the course if full
     */
    public boolean courseNotFull()
    {
        return students.size() < max;
    }

    /**
     * This method will add a student to the list of students in course
     * @param s : student to add to list
     */
    public void addStudent(Student s)
    {
        students.add(s);
    }

    /**
     * This method will return the number of students in the course
     * @return : the number of students in course
     */
    public int numOfStudents()
    {
        return students.size();
    }

    /**
     * This method will check if the course contains a certain session
     * @param s : the session
     * @return : returns true if the session is in the list
     */
    public boolean containsSession(Session s)
    {
        return (sessionList.contains(s));
    }

    /**
     * This method add a session to the list
     * @param s : session to add to the list
     */
    public void addSession(Session s)
    {
        sessionList.add(s);
    }

    /**
     * This method will return the list of sessions in the course
     * @return : sessionList - list of sessions
     */
    public ArrayList<Session> getSessions()
    {
        return sessionList;
    }

    /**
     * This method will delete a session from the list
     * @param s : session to delete
     */
    public void deleteSession(Session s)
    {
        sessionList.remove(s);
    }

    /**
     * This method will return the course id
     * @return : courseId
     */
    public String getCourseId() {
        return this.courseId;
    }

    /**
     * This method will set the course id
     * @param courseId : the course id
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * This method will return the course code
     * @return the course code
     */
    public String getCourseCode() {
        return this.courseCode;
    }

    /**
     * This method will set the course code
     * @param courseCode : the code of the course
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * This method will return the description of the course
     * @return the description of the course
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method will set the description of the course
     * @param description the course decription
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method will return the number of sessions initially scheduled
     * @return the number of sessions
     */
    public int getNumOfSessions() {
        return this.numOfSessions;
    }

    /**
     * This method will set the number of sessions for course
     * @param numOfSessions the number of session
     */
    public void setNumOfSessions(int numOfSessions) {
        this.numOfSessions = numOfSessions;
    }

    /**
     * This method will return the number of students allowed to join course
     * @return the number of students allowed in course
     */
    public int getMax() {
        return this.max;
    }

    /**
     * This method will set the number of students in course
     * @param max : the number of students
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * This method will return the minimum number of students that have to be in course
     * @return the minimum number of students
     */
    public int getMin() {
        return this.min;
    }

    /**
     * This method will set the minimum number of students in course
     * @param min : the number of students
     */
    public void setMin(int min) {
        this.min = min;
    }
    
    /**
     * This method will override the toString method for course class. It will return all the data of the class in a string format
     */
    @Override
    public String toString() {
        return
            "\nCourse: " + getCourseCode() +
            "\nDescription: " + getDescription() +
            "\nNumber of Students Allowed to Join: " + getMax() +
            "\nMinimum Number of Students Needed: " + getMin() +
            "\nInitial Number Of Sessions: " + getNumOfSessions() +
            "\nNumber Of Students in Course: " + students.size() +
            "\nNumber of Sessions scheduled: " + sessionList.size();
    }

    /**
     * This method will use a string builder to make a string of all the sessions in the course
     * @return : the string representation of the sessions
     */
    public String printSessions()
    {
                //creates a string builder variable
                StringBuilder sb = new StringBuilder();

                sb.append("\n\n--Sessions List--\n");
        
                //adds the list to the string builder c
                for (Session s : scheduledSession)
                {
                    sb.append(s.toString());
                    sb.append("\n\n");
                }
        
                sb.append("--END--\n\n");
        
                //returns the string builder to the caller
                return sb.toString();
    }
}
