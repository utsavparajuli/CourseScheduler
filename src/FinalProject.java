import scheduler.CourseScheduler;
import scheduler.ScheduleCourseSeq;

/**
 * This program will emulate a Course Registration System. The data of students, courses, faculties will be taken in from a list
 * of input files. The courses students wish to take and the courses faculties wish to teach will also be take in from an input file.
 * Then those data will be parsed and contained in the course directory and person directory. Then the scheduling algorith will be called
 * and it will schedule students and faculties to the courses.
 * 
 * After scheduling we are left with sorting the refining the courses as some courses will not have enough students to be taught. After
 * refining and deleting courses and sessions that did not meet the requirement the data will be outputted to different output files.
 * 
 * @author  : Utsav Parajuli
 * @version : 4.0
 * @since   : 11/29/2020
 */
public class FinalProject {

   /**
    * Main method for the class
    * @param args : string args
    * @throws Exception : throws exception
    */
      public static void main(String[] args) throws Exception {

      //Creating a course scheduler object that will create the seqeuntial course scheduler
      CourseScheduler cs = new ScheduleCourseSeq();

      //scheduling the students and faculties
      cs.schedule();

      //sorting the courses
      cs.returnCourseDirectory().sortScheduledCourse();

      //sorting the sessions for each course
      cs.returnCourseDirectory().sortScheduledSessions();

      //sorting the courses for each student
      cs.returnPersonDirectory().sortStudentCourses();

      //sorting the courses for each faculty
      cs.returnPersonDirectory().sortFacultyCourses();

      //sorting the sessions of courses for each faculty
      cs.returnPersonDirectory().sortFacultySessions();

      //outputting the scheduled course data
      cs.returnCourseDirectory().scheduledCourseOutput();

      //outputtitng the unscheduled course data
      cs.returnCourseDirectory().unscheduledCourseOutput();

      //outputting the scheduled students data
      cs.returnPersonDirectory().scheduledStudentsOutput();

      //outputting the unscheduled students data
      cs.returnPersonDirectory().unscheduledStudentsOutput();

      //ouputting the faculty data
      cs.returnPersonDirectory().facultyOutput();


      //OUTUT - the overall status
      System.out.println("TOTAL STUDENTS                 : " + cs.returnPersonDirectory().numOfStudents());
      System.out.println("TOTAL FACULTY                  : " + cs.returnPersonDirectory().numOfFaculty());
      System.out.println("TOTAL COURSES                  : " + cs.returnCourseDirectory().getCourses().size());
      System.out.println("TOTAL SESSIONS SCHEDULED       : " + cs.returnCourseDirectory().getNumOfSessions());
      System.out.println("TOTAL COURSES UNSCHEDULED      : " + cs.returnCourseDirectory().numOfUnscheduledCourses());
      System.out.println("TOTAL STUDENTS WITH NO CLASSES : " + cs.returnPersonDirectory().numberOfUnscheduledStudents());

   }
}