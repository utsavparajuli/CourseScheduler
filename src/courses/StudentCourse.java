package courses;

/**
 * This class will contain a courseId and description. This class is used as a helper class that will be easy to output
 * course data for each student.
 * 
 * @author  : Utsav Parajuli
 * @version : 1.0
 * @since   : 12/04/2020
 */
public class StudentCourse {

    private String courseId;        //Course id of the course
    private String description;     //description of the course

    /**
     * The default constructor for StudentCourse
     */
    public StudentCourse() {
    }

    /**
     * The constructor that will set the courseId and description
     * @param courseId    : id of the course
     * @param description : description of the course
     */
    public StudentCourse(String courseId, String description) {
        this.courseId = courseId;
        this.description = description;
    }

    /**
     * This method will return the course id
     * @return courseId
     */
    public String getCourseId() {
        return this.courseId;
    }

    /**
     * This method will set the courseId
     * @param courseId : courseId
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * This method will return the description of course
     * @return the course description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method will set the description of the course
     * @param description : description of course
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method will override the toString method for studentCourse class. It will return a string of the data of class
     */
    @Override
    public String toString() {
        return
            "\nCourse ID: " + getCourseId() +
            "\nDescription: " + getDescription();
    }
}
