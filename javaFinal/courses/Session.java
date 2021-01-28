package courses;

import java.util.ArrayList;
import allpeople.Faculty;
import allpeople.Student;
import utilities.SessionIDGenerator;

/**
 * This class represesnts a session for a course. It contains a max number of students allowed to join the session, minimum number of students
 * for each session. It also contains a teacher for the session along with the list of students.
 * 
 * @author  : Utsav Parajuli
 * @version : 3.0
 * @since   : 12/02/2020
 */
public class Session{
    private int                max;         //maximum number of students for a session
    private int                min;         //minimum number of students for a session
    private String             uniqueId;    //unique id for the session
    private Faculty            teacher;     //teacher for the session
    private boolean            status;      //status of the session, whether it is active or not
    private ArrayList<Student> inSession;   //students in the session

    /**
     * This constructor will initialize the attributes of the class. It will also generate the the unique id
     * @throws Exception : input mismatch exception
     */
    public Session() throws Exception
    {
        setUniqueId(); 
        inSession = new ArrayList<>();
        teacher   = new Faculty();
        status    = false;
    }
    
    /**
     * This method will return the unique id of the session
     * @return : the unique id
     */
    public String getUniqueId()
    {
        return uniqueId;
    }

    /**
     * This method will return the teacher of the session
     * @return : the faculty
     */
    public Faculty getTeacher()
    {
        return teacher;
    }

    /**
     * This method will return the list of students in the session
     * @return : the list of students in session
     */
    public ArrayList<Student> getStudents()
    {
        return inSession;
    }

    /**
     * This method will check if the session can be scheduled
     * @return : this method will return true if the session can be scheduled, returns false if it can't
     */
    public boolean canSchedule()
    {
        return inSession.size() >= min;
    }

    /**
     * This method will check if the course is open.
     * @return : this method will return true if the session is not full, returns false if it is full
     */
    public boolean isOpen()
    {
        return inSession.size() < max; 
    }

    /**
     * This method will check if the course meets the minimum requirement to be scheduled
     * @return : this method will return true if the minimum requierement is not met. It will return false if the minimum requirement is met
     */
    public boolean minRequirement()
    {
        return (inSession.size() < min);
    }
    
    /**
     * This method will get the maximum number of students allowed in session
     * @return the max num of students
     */
    public int getMax() {
        return this.max;
    }

    /**
     * This method will set the max number of students for the session
     * @param max : number of students
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * This method will return the minimum number of students that has to be in session
     * @return the minimum number of students
     */
    public int getMin() {
        return this.min;
    }

    /**
     * This method will set the minimum number of students in session
     * @param min : the number of students
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * This method will set the unique id for session
     */
    public void setUniqueId() {
        String id = new SessionIDGenerator().generateId();
        this.uniqueId = id.substring(0, 5);
    }

    /**
     * This method will add a student to the session
     * @param s : student to add
     */
    public void addStudent(Student s)
    {
        inSession.add(s);
    }

    /**
     * This method will set a faculty member to the session
     * @param teacher : the faculty for session
     * @throws CloneNotSupportedException : clone not supported Exception
     */
    public void setTeacher(Faculty teacher) throws CloneNotSupportedException {
        this.teacher = (Faculty) teacher.clone();
        status = true;
    }

    /**
     * This method will set the status of the session, whether it is active or not
     * @param status : status a true or false value
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /**
     * This method will return the status of the session's faculty
     * @return will return true if faculty exists or else will return false if faculty does not exist
     */
    public boolean getStatus()
    {
        return status;
    }
    /**
     * This method will override the toString method for session class. It will return all the data of the class in a string format.
     */
    @Override
    public String toString() {
        return 
            "\nSession Id: " + getUniqueId() +
            "\nTeacher Name: " + getTeacher().getFullName() +
            "\nTeacher Id: " + getTeacher().getId() +
            "\nNumber of Students in Session: " + inSession.size();
    }

    /**
     * This method will return the string of all the students in the session.
     * @return : string that is built from all the students in session
     */
    public String printStudents()
    {
                //creates a string builder variable
                StringBuilder sb = new StringBuilder();

                sb.append("\n\n--STUDENTS--\n\n");
        
                int i = 0;
                //adds the list to the string builder c
                for (Student s : inSession)
                {
                    sb.append(++i);
                    sb.append(")");
                    sb.append(s.getFullName() + "\n");
                    sb.append("   " + s.getId());
                    sb.append("\n\n");
                }
        
                sb.append("--END--\n\n");
        
                //returns the string builder to the caller
                return sb.toString();
    }
}