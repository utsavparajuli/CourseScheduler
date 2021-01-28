package scheduler;

import allpeople.PersonDirectory;
import courses.CourseDirectory;

/**
 * This interface is a CourseScheduler interface. It has a method to schedule and 2 other methods which will be used to return the personal
 * directory and course directory after scheduling.
 * 
 * @author  : Utsav Parajuli
 * @version : 2.0
 * @since   : 12/05/2020
 */
public interface CourseScheduler {

    /**
     * This method will schedule the courses
     * @throws Exception : any exception that are caused during runtime
     */
    public void schedule() throws Exception;

    /**
     * This method will return the direcotry of people
     * @return : personDirectory object
     */
    public PersonDirectory returnPersonDirectory();

    /**
     * This method will return the directory of course
     * @return : CourseDirectory object
     */
    public CourseDirectory returnCourseDirectory();
}
