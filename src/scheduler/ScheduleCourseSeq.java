package scheduler;

import java.util.ArrayList;
import java.util.List;
import allpeople.PersonDirectory;
import courses.CourseDirectory;
import courses.Session;

/**
 * This class implements the CourseScheduler interface. It implements all the methods from the interface. In this class
 * we will be scheduling courses sequentially. The students will get to schedule courses based on the order they are in the PersonDirectory (pd).
 * 
 * @author  : Utsav Parajuli
 * @version : 4.0
 * @since   : 12/05/2020
 */
public class ScheduleCourseSeq implements CourseScheduler{
    
    private static final int CLASS_LIMIT         = 5;       //Number of courses a student can take
    private static final int FACULTY_CLASS_LIMIT = 4;       //Number of courses a faculty member can teach
    private PersonDirectory pd;                             //person directory of the school
    private CourseDirectory cd;                             //course directory of the school

    /**
     * This method will initialize the the pd and cd which are personDirectory and CourseDirectory objects. When we initialize these objects
     * their constuctors will be called and it will get all the data from an input file and store it onto the directory.
     * @throws Exception : file error exceptions
     */
    public ScheduleCourseSeq() throws Exception{
        pd = new PersonDirectory();
        cd = new CourseDirectory();
    }

    /** 
     * This method will override the schedule method from the interface and will call on two functions inside this class.
     * It will call on the method to schedule students and to schedule faculties
     * 
     * @throws CloneNotSupportedException : throws clone not supported Exception
     */
    @Override
    public void schedule() throws CloneNotSupportedException
    {
        scheduleStudents();
        scheduleFaculties();
    }

    /**
     * This method will schedule the faculties to the courses they wished to teach. The scheduling algorithm for faculties work in the
     * following sequence :
     *      1) First this method will go through the list of faculties inside the PersonDirectory and will check if the faculty has
     *         reached a limit on teaching courses
     *      2) It will then get the courses the faculties wishes to teach
     *      3) It will go through the courses wishlist and if it matches the courses in the directory it will add the course to
     *         Faculty's course list and will add the Faculty to the course's faculty list.
     *      4) Then it will go through the sessions inside the course and if the session does not contain a faculty it will add
     *         the faculty to that session
     * It will run through all these steps and schedule a course for each faculty in the directory
     * @throws CloneNotSupportedException : throws this exception as we have to clone a faculty object to store inside a session
     */
    public void scheduleFaculties() throws CloneNotSupportedException
    {
        //goes through the list of all faculties
        for(int i = 0; i < pd.returnFaculties().size(); i++)
        {
            //will check if the faculty has reached the limit on teaching courses
            if(pd.returnFaculties().get(i).getTeachingWishlist().size() < FACULTY_CLASS_LIMIT)
            {
                //courses will contain the list of courses the faculy wishes to teach
                List<String> courses = pd.returnFaculties().get(i).getTeachingWishlist();

                //will go through all the courses in the directory
                for(int j = 0; j < cd.getCourses().size(); j++)
                {
                    //will go through all the courses in the list of courses faculy wishes to teach
                    for(int k = 0; k < courses.size(); k++)
                    {
                        //here we are checking if the courses match and the course still needs some faculties to teach
                        if(courses.get(k) != null && courses.get(k).equals(cd.getCourses().get(j).getCourseCode()) && 
                           cd.getCourses().get(j).getFaculties().size() < cd.getCourses().get(j).getNumOfSessions())
                        {
                            //adding the faculty to course and course to faculty
                            cd.getCourses().get(j).addFaculty(pd.returnFaculties().get(i));
                            pd.returnFaculties().get(i).addCourse(cd.getCourses().get(j));

                            //sessions will contain the sessions in the course
                            ArrayList<Session> sessions = cd.getCourses().get(j).getSessions();

                            //will go thorugh each session in the course
                            for(int l = 0; l < sessions.size(); l++)
                            {
                                //if the session still needs a teacher then we schedule the teacher to the session
                                if(!(sessions.get(l).getStatus()))
                                {
                                    sessions.get(l).setTeacher(pd.returnFaculties().get(i));
                                    pd.returnFaculties().get(i).addSession(sessions.get(l));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method will schedule the students to the courses they wished to take. The scheduling algorithm for students work in the
     * following sequence :
     *      1) First this method will go through the list of students inside the PersonDirectory and will check if the student has
     *         reached a limit on taking courses
     *      2) It will then get the courses the student wishes to take
     *      3) It will go through the courses wishlist and if it matches the courses in the directory it will add the course to
     *         Student's course list and will add the Student to the course's Student list.
     *      4) Then it will go through the sessions inside the course and if the session is open it will add the student to that 
     *         session
     * It will run through all these steps and schedule a course for each student in the directory
     */
    public void scheduleStudents()
    {
        //goes through the list of students
        for(int i = 0; i < pd.returnStudents().size(); i++)
        {
            //checks if the student can take the course bc of limit on courses
            if(pd.returnStudents().get(i).getCourses().size() < CLASS_LIMIT)
            {
                //gets the courses student wants to take
                List<String> courses = pd.returnStudents().get(i).getCourseWishlist();

                //goes through the courses in directory
                for(int j = 0; j < cd.getCourses().size(); j++)
                {
                    //goes through the num of courses student wants to take
                    for(int k = 0; k < courses.size(); k++)
                    {
                        //here we check if the course matches according to the person's wish
                        //we also check the condition that a student cannot take the same course twice and
                        //finally check if the course still has seats open
                        if(courses.get(k) != null && courses.get(k).equals(cd.getCourses().get(j).getCourseCode()) && 
                           !(pd.returnStudents().get(i).containsCourse(cd.getCourses().get(j)))
                           && cd.getCourses().get(j).courseNotFull())
                        {
                            //if the previous conditions pass we schedule the student
                            //adding the student to the course
                            //adding the course to the student's list of courses
                            cd.getCourses().get(j).addStudent(pd.returnStudents().get(i));
                            pd.returnStudents().get(i).addCourses(cd.getCourses().get(j));
                            pd.returnStudents().get(i).addStudentCourses(cd.gStudentCourses().get(j));

                            //sessions will contain the list of sessions in the course
                            ArrayList<Session> sessions = cd.getCourses().get(j).getSessions();
                                    
                            //going thrugh each of the sessions
                            for(int l = 0; l < sessions.size(); l++)
                            {
                                //checks if the session is open
                                if(sessions.get(l).isOpen())
                                {
                                    //adds the student to the session
                                    sessions.get(l).addStudent(pd.returnStudents().get(i));

                                    //adds the session to the student
                                    pd.returnStudents().get(i).addSessions(sessions.get(l));

                                    //checks if the session can be scheduled after meeting the minimum requirement &
                                    //seeing that the course doesn't already have the session scheduled
                                    if(sessions.get(l).canSchedule() && !(cd.getCourses().get(j).containsSession(sessions.get(l))))
                                    {
                                        //adds the session to the list of sessions inside course
                                        cd.getCourses().get(j).addSession(sessions.get(l));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * This method will override the the interface method and will return the updated personal directory after
     * scheduling students and faculties
     */
    @Override
    public PersonDirectory returnPersonDirectory() {
        return pd;
    }

    /**
     * This method will override the the interface method and will return the updated course directory after
     * scheduling students and faculties
     */
    @Override
    public CourseDirectory returnCourseDirectory() {
        return cd;
    }
}

