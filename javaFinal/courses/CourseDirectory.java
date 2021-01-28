package courses;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will represent course directory of a school. It will contain a list of all the courses,
 * a list of scheduled courses, and a list of unschedueld courses. We will be able to manipulate this
 * data to do certain tasks, as well as using it to output some data onto the file.
 * 
 * @author  : Utsav Parajuli
 * @version : 4.0
 * @since   : 12/04/2020
 */
public class CourseDirectory {

    private int                      sessions;              //number of sessions
    private ArrayList<Course>        courses;               //The list of all courses
    private ArrayList<Course>        scheduledCourses;      //The list of scheduled courses
    private ArrayList<Course>        unscheduledCourses;    //The list of unscheduled courses
    private ArrayList<StudentCourse> studentCourse;         //The list of student courses

    /**
     * The contructor for this class will initialize all the lists and will parse the courses and create sessions for those courses.
     * The parseCourse method will read data from an input file and create courses with that file and add it to the courses list. After reading in the number of
     * sessions while parsing course it will create sessions for that particular course
     * @throws Exception : file exceptions
     */
    public CourseDirectory() throws Exception
    {
        courses            = new ArrayList<>();
        studentCourse      = new ArrayList<>();
        scheduledCourses   = new ArrayList<>();
        unscheduledCourses = new ArrayList<>();
        parseCourse();
        createSessions();
    }

    /**
     * This method will parse the courses. It will read in the input of courses from a file and create courses after reading the data. It will then add that course
     * to the list of courses.
     * @throws Exception : File error exceptions
     */
    public void parseCourse() throws Exception
    {
        //file variable
        File courseFile = new File("inputFiles/courseInfo.txt");
    
        //try with block
        try
        (
            Scanner input  = new Scanner(courseFile);
        )
        {
        //Reads in the data from file
        while(input.hasNext())
        {
                 String department  = input.nextLine();
                 String code        = input.nextLine();
                 String courseId    = department + code;
                 String description = input.nextLine();

                 int    sessionsNum = Integer.parseInt(input.nextLine());
                 int    maxStudents = Integer.parseInt(input.nextLine());
                 int    minStudents = Integer.parseInt(input.nextLine());

                 //try catch block whether we can skip the line or not
                 try{
                     input.nextLine();
                 }
                 catch(Exception e)
                 {
                     //test
                 }

                 //creates a new course with those data
                 Course course = new Course(code, courseId, description, sessionsNum, maxStudents, minStudents);

                 //creates a simple student course object with the same data
                 StudentCourse stuCourses = new StudentCourse(courseId, description);
                 
                 //adds course to list
                 courses.add(course);

                 //adds stuCourses to the other list
                 studentCourse.add(stuCourses);
        }
        }
    }       


    /**
     * This method will return the list of courses
     * @return courses
     */
    public ArrayList<Course> getCourses(){
        return courses;
    }

    /**
     * This method will return the lust of StudentCourse. Student course is an abbreviated version of regular course object
     * @return studentCourse
     */
    public ArrayList<StudentCourse> gStudentCourses()
    {
        return studentCourse;
    }

    /**
     * This method will build a string of all the courses
     * @return : a string value of all the data of courses
     */
    public String printCourses()
    {
                //creates a string builder variable
                StringBuilder sb = new StringBuilder();

                sb.append("\n\n--Course List--\n");
        
                //adds the list to the string builder c
                for (Course s : courses)
                {
                    sb.append(s.toString());
                    sb.append("\n\n\n");
                }
        
                sb.append("--END--\n\n");
        
                //returns the string builder to the caller
                return sb.toString();
    }

    /**
     * This method will create sessions for a course
     * @throws Exception : input mismatch exception when creating session object
     */
    public void createSessions() throws Exception
    {
        //goes through the courses
        for(int i = 0; i < courses.size(); i++)
        {
            //goes through the sessions for that course
            for(int j = 0; j < courses.get(i).getNumOfSessions(); j++)
            {
                //adds session & sets the minimum and maximum number of students allowed for each course
                courses.get(i).addSession(new Session());
                courses.get(i).getSessions().get(j).setMax(courses.get(i).getMax() / courses.get(i).getNumOfSessions());
                courses.get(i).getSessions().get(j).setMin(courses.get(i).getMin());
            }
        }
    }

    /**
     * This method will go through the list of courses and check if the course can be schedueld or not. If it can be scheduled it will be added to that list or
     * else it will be added to a list with unscheduled courses
     */
    public void sortScheduledCourse()
    {
        //goes through the courses
        for(int i = 0; i < courses.size(); i++)
        {
            //checks if the course can be taught
            //if it can be taught it will be addded to the scheduled courses list
            if(courses.get(i).canTeachCourse())
            {
                scheduledCourses.add(courses.get(i));
            }
            //if the previous condition fails the course will be added to the unscheduled courses list
            else
            {
                unscheduledCourses.add(courses.get(i));
            }
        }
    }     

    /**
     * This method will go through the list of courses and check if each of the sessions can be scheduled. If it doesn't have enough people to meet the minimum
     * class size requirement the session will be deleted from that course
     */
    public void sortScheduledSessions()
    {
        //goes through the list of scheduled courses
        for(int i = 0; i < scheduledCourses.size(); i++)
        {
            //goes through the list of sessions for that course
            for(int j = 0; j < scheduledCourses.get(i).getSessions().size(); j++)
            {
                //checks if the session cannot be scheduled then if the contition passes the session will be deleted
                if(!(scheduledCourses.get(i).getSessions().get(j).canSchedule()))
                {
                    scheduledCourses.get(i).deleteSession(scheduledCourses.get(i).getSessions().get(j));
                }
            }
        }
    }
       

    /**
     * This method will output the following information to the ScheduledCourseSessions.txt file:
     *      1) Each course that was scheduled (i.e. all the information of the class : the id, number of students, min and max requirements)
     *      2) Each session for that course (the session ID is displayed)
     *      3) The full name and id number of the instructor for the session
     *      4) The number of students in session and the name and id of each student in each session
     * @throws Exception : throws file not found exception
     */
    public void scheduledCourseOutput() throws Exception
    {
        //creates a file variable
        File scheduledCourseOutput = new File("outputFiles/ScheduledCourseSessions.txt");

        //try with code block
        try (
            //creates a print writer object which will be used to output to a file
            PrintWriter output = new PrintWriter(scheduledCourseOutput);
        )
        {
            output.print("---------------------------------------------------------------------\n");
            output.print("-------------------------SCHEDUELD COURSES---------------------------\n");
            output.print("---------------------------------------------------------------------\n");

            //goes through the scheduled courses list
            for(int i = 0; i < scheduledCourses.size(); i++)
            {
                //outputs the course info
                output.println( scheduledCourses.get(i).toString());

                //goes through the sessions inside the course
                for(int j = 0; j < scheduledCourses.get(i).getSessions().size(); j++)
                {
                    //outputs the sessions data
                    output.println("\n---------------------------");
                    output.println("--------SESSION #" + (j + 1) + "---------");
                    output.println("---------------------------");
                    output.println(scheduledCourses.get(i).getSessions().get(j).toString() + 
                                   scheduledCourses.get(i).getSessions().get(j).printStudents() + "\n\n");
                    
                    sessions++;
                }
                output.print("---------------------------------------------------------------------");
                output.println("\n\n");
            }
        }
    }
        
    /**
     * This method will output the following information to the UnscheduledCourseSessions.txt file:
     *      1) Each course that was not scheduled (i.e. all the information of the class : the id, number of students, min and max requirements)
     *      2) Minimum number of students that needed to be in that course
     * @throws Exception : file exceptions
     */
    public void unscheduledCourseOutput() throws Exception
    {
        //creates a file variable
        File unscheduledCourseOutput = new File("outputFiles/UnscheduledCourseSessions.txt");

        //try with block of code
        try (
            //creates a print writer object
            PrintWriter output = new PrintWriter(unscheduledCourseOutput);
        )
        {
            output.print("----------------------------------------------------------------------\n");
            output.print("-------------------------UNSCHEDUELD COURSES--------------------------\n");
            output.print("----------------------------------------------------------------------\n");
            
            //goes through the list of unscheduled courses and outputs it
            for(int i = 0; i < unscheduledCourses.size(); i++)
            {
                output.println("COURSE: " + unscheduledCourses.get(i).getCourseCode());
                output.println("DESCRIPTION: " + unscheduledCourses.get(i).getDescription());
                output.println("MINIMUM STUDENTS NEEDED: " + unscheduledCourses.get(i).getMin());
                output.println("STUDENTS THAT TRIED TO ENROLL: " + unscheduledCourses.get(i).numOfStudents());

                output.print("----------------------------------------------------------------------");
                output.println("\n\n");
            }
        }
    }

    /**
     * This method will return number of sessions scheduled.
     * 
     * @return : sessions count
     */
    public int getNumOfSessions()
    {
        return sessions;
    }

    /**
     * This method will return the number of unscheduled courses
     * @return number of unscheduled courses
     */
    public int numOfUnscheduledCourses()
    {
        return unscheduledCourses.size();
    }
}
