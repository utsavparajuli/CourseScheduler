package allpeople;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will represent a person directory of a school. It contains the list of students and the list of faculty members from
 * which we will be able to manipulate and output data as we please.
 * 
 * @author  : Utsav Parajuli
 * @version : 5.0
 * @since   : 12/01/2020
 */

public class PersonDirectory {
   private ArrayList<Student> studentDirectory;     //directory of students
   private ArrayList<Faculty> facultyDirectory;     //directory of faculty members
   private ArrayList<String>  sWishlist;            //an array list of the courses student wishes to take
   private ArrayList<String> fWishlist;             //an array list of the courses faculty wishes to teach
   private int studentCount;                        //number of students
   private int facultyCount;                        //number of faculties
   private int unscheduledStudents;                 //number of unscheduled students

   /**
    * This method will return the array list of students
    * @return : a list of students
    */
   public ArrayList<Student> returnStudents()
   {
       return studentDirectory;
   }

   /**
    * This method will return the array list of faculties
    * @return : a list of faculty
    */
   public ArrayList<Faculty> returnFaculties()
   {
       return facultyDirectory;
   }

   /**
    * A constructor for the PersonDirectory class. This constructor will initialize the arraylists and will get the input of
    * courses student wants to take. It will also read in data of all the students and faculties and create a list of students and faculties.
    * @throws Exception : throws exception if the file can't be opened by any of the parsing functions
    */
   public PersonDirectory() throws Exception
   {
      studentDirectory = new ArrayList<>();
      facultyDirectory = new ArrayList<>();
      sWishlist        = new ArrayList<>();
      fWishlist        = new ArrayList<>();
      studentCount = 0;
      facultyCount = 0;

      facultyWishlist();
      coursesWishlist();
      parseFaculty();
      parseStudent();
   }

   /**
    * This method will return the number of students
    * @return : the number of students, studentCount
    */
   public int numOfStudents()
   {
       return studentCount;
   }

   /**
    * This method will return the number of faculties
    * @return : the number of faculties, facultyCount
    */
   public int numOfFaculty()
   {
       return facultyCount;
   }

   /**
    * This method will parse the students. It will use a text file to get in data of 100 students and will create an array list of the student
    * objects.
    * @throws Exception : throws exception if the file is not found
    */
   public void parseStudent() throws Exception
   {
       //creates a file variable
       File studentFile = new File("inputFiles/student.txt");
       
       //try-with code block that will read in the data from the file
       try(
           Scanner input  = new Scanner(studentFile);
           )
           {
               //runs until there is still data in the file
               while(input.hasNext())
               {                   
                   String firstAndMiddle = input.nextLine();
                   String lastName       = input.nextLine();
                   String email          = input.nextLine();
                   String phoneNum       = input.nextLine();
                   String streetAddress  = input.nextLine();
                   String city           = input.nextLine();
                   String state          = input.nextLine();
                   long   zipCode        = Long.parseLong(input.nextLine());
                   
                   String birthDate      = input.nextLine();
                   double gpa            = Double.parseDouble(input.nextLine());
                   String startDate      = input.nextLine();

                   //try catch block to check if the file still exists and skip a line
                   try{
                       input.nextLine();
                    }
                    catch(Exception e)
                    {
                        //test
                    }
                    
                    //creates a new student student 
                    Student student = new Student(firstAndMiddle.substring(0, firstAndMiddle.indexOf(' ')), 
                                                  firstAndMiddle.substring(firstAndMiddle.indexOf(' ') + 1, firstAndMiddle.length()), 
                                                  lastName, email, phoneNum, streetAddress, city, state, zipCode, 
                                                  birthDate, gpa, startDate);
                    
                    //gets the list of courses student wishes to take
                    String coursesWish = sWishlist.get(studentCount);

                    //sends the courses to the student class
                    student.setCourseWishlist(coursesWish);

                    //adds the student to the directory
                    studentDirectory.add(student);

                    studentCount++;
                }
            }
    }
    


    /**
     * This method will get the courses student wishes to take from the input file.
     * @throws Exception : throws an exception if the file cannot be found or opened
     */
    public void coursesWishlist() throws Exception
    {
       //creates a file object
       File wishlistFile = new File("inputFiles/wishlist.txt");

       //try with code block
       try(
           Scanner input = new Scanner(wishlistFile);
       )
       {
           while(input.hasNext())
           {
            String courses = input.nextLine();

            try{
                input.nextLine();
             }
             catch(Exception e)
             {
                 //test
             }

            //adds the courses student wants to take to the array list
            sWishlist.add(courses);
           }
       }
   }


   /**
    * This method will parse the faculties. It will use a text file to get in data of 20 faculties and will create an array list of the faculty
    * objects.
    * @throws Exception : throws exception if file cannot be found or cannot read from file
    */
   public void parseFaculty() throws Exception
   {
       //creates a new file variable
       File facultyFile = new File("inputFiles/faculty.txt");

       //try with code block
       try(
           Scanner input  = new Scanner(facultyFile);
           )
           {
               while(input.hasNext())
               {
                   String firstAndMiddle = input.nextLine();
                   String lastName       = input.nextLine();
                   String email          = input.nextLine();
                   String phoneNum       = input.nextLine();
                   String streetAddress  = input.nextLine();
                   String city           = input.nextLine();
                   String state          = input.nextLine();
                   long   zipCode        = Long.parseLong(input.nextLine());
                   
                   String startDate      = input.nextLine();
                   String tenured        = input.nextLine();
                   
                   //try catch block if we can go to next line
                   try{
                       input.nextLine();
                    }
                    catch(Exception e)
                    {
                        //test
                    }
                    
                    //creates the faculty object with the new data
                    Faculty faculty= new Faculty(firstAndMiddle.substring(0, firstAndMiddle.indexOf(' ')), 
                                                 firstAndMiddle.substring(firstAndMiddle.indexOf(' ') + 1, 
                                                 firstAndMiddle.length()), lastName, email, phoneNum, 
                                                 streetAddress, city, state, zipCode, startDate, tenured);
                    
                    String coursesWish = fWishlist.get(facultyCount);

                    faculty.setTeachingWishlist(coursesWish);

                    //adds the faculty to the list of faculty                            
                    facultyDirectory.add(faculty);
                    
                    //increments the number of faculty
                    facultyCount++;
           }
       }
   }

   
    /**
     * This method will make a string of the full list of students
     * @return : the string of the whole student directory
     */
    public String printStudents()
    {
                //creates a string builder variable
                StringBuilder sb = new StringBuilder();

                sb.append("\n\n--Student List--\n");
        
                //adds the list to the string builder c
                for (Student s : studentDirectory)
                {
                    sb.append(s.toString());
                    sb.append("\n\n\n");
                }
        
                sb.append("--END--\n\n");
        
                //returns the string builder to the caller
                return sb.toString();
    }

    /**
     * This method will make a string of the full list of faculties
     * @return : the string of the whole faculty directory
     */
    public String printFaculties()
    {
                //creates a string builder variable
                StringBuilder sb = new StringBuilder();

                sb.append("\n\n--Faculty List--\n");
        
                //adds the list to the string builder c
                for (Faculty f : facultyDirectory)
                {
                    sb.append(f.toString());
                    sb.append("\n\n\n");
                }
        
                sb.append("--END--\n\n");
        
                //returns the string builder to the caller
                return sb.toString();
    }

    /**
     * This method will output the following information to the scheduledStudentsOutput file:
     *      1. Outputs each student's full details (i.e. name, address, contact info)
     *      2. Outputs the courses the student is taking, along with the description of the course
     *      3. Outputs the unique session id of the course the student is in
     * @throws Exception : throws an file exception if the file cannot be found
     */
    public void scheduledStudentsOutput() throws Exception
    {
        //creates a file variable
        File scheduledStudentsOutput = new File("outputFiles/ScheduledStudents.txt");
        
        //try with code block
        try (
            PrintWriter output = new PrintWriter(scheduledStudentsOutput);
        )
        {
            output.println("-------------------------------------------------------------------------");
            output.println("---------------------------SCHEDULED STUDENTS----------------------------");
            output.println("-------------------------------------------------------------------------\n");
            //goes through the list of students in the directory
            for(int i = 0; i < studentDirectory.size(); i++)
            {
                //checks if the student is has any courses scheduled
                if(!(studentDirectory.get(i).getCourses().isEmpty()))
                {
                    output.println("\n-----STUDENT #" + (i + 1) + "-----");
                    //prints the student information
                    output.println(studentDirectory.get(i).toString() + "\n");

                    //goes through the list of courses for each student

                    output.println("-----COURSES----");
                    for(int j = 0; j < studentDirectory.get(i).getCourses().size(); j++)
                    {
                        //prints the first course
                        output.println(studentDirectory.get(i).getCourses().get(j).toString());

                        //prints the unique id of that particular course the student is in
                        output.println("Session ID: " + studentDirectory.get(i).getSessions().get(j).getUniqueId() + "\n");
                    }
                    output.println("-------------------------------------------------------------------------\n");
                }
            }

        }
    }

    /**
     * This method will sort the courses for students. It will look at the list of courses student has and sees if the course they chose is
     * going to be taught or not. If it is taught and they are signed up it will go ahead and check if the session the student is in is going to
     * be scheduled. If they do not qualify for those conditions the student will be taken out of that course.
     */
    public void sortStudentCourses()
    {
        //goes through the list of students
        for(int i = 0; i < studentDirectory.size(); i++)
        {
                //for each student we go thorugh the courses they are signed up for
                for(int j = 0; j < studentDirectory.get(i).getFullCourses().size() && j < studentDirectory.get(i).getCourses().size(); j++)
                {
                    //checking if the course is scheduled or unscheduled
                    if(studentDirectory.get(i).getFullCourses().get(j).canTeachCourse())
                    {
                        //if the course is scheduled we check through the session for that course
                        for(int k = 0; k < studentDirectory.get(i).getFullCourses().get(j).getSessions().size(); k++)
                        {
                            //if the session is going to be scheduled then we do noting else we go in the loop and remove the course from the
                            //students course list
                            if(!(studentDirectory.get(i).getFullCourses().get(j).getSessions().get(k).canSchedule()))
                            {
                                studentDirectory.get(i).getCourses().remove(j);
                                break;
                            }
                        }
                    }
                    //if the course is unscheduled we remove the course
                    else
                    {
                        studentDirectory.get(i).getCourses().remove(j);
                    }
                }
        }
    }    
    
    /**
     * This method will sort the courses for each faculty. It will go through each faculty in the list and will check if a course
     * is scheduled or not.
     * If it is scheduled the course stands inside the faculty's data or else the course will be deleted from the faculy's course
     * list.
     */
    public void sortFacultyCourses()
    {
        //goes through the list of faculties
        for(int i = 0; i < facultyDirectory.size(); i++)
        {
            //for each faculty we go thorugh the courses they are teaching
            for(int j = 0; j < facultyDirectory.get(i).getCourses().size(); j++)
            {
                //checking if the course is scheduled or unscheduled
                //if the course is unscheduled then the course is removed
                if(!(facultyDirectory.get(i).getCourses().get(j).canTeachCourse()))
                {
                    facultyDirectory.get(i).getCourses().remove(j);
                }
            }
        }
    } 

    /**
     * This method will sort the session's of a course for each faculty. It will check if a certain session inside the course is not
     * going to be scheduled because of the minimum student requirement not being met. If the session is not scheduled then the course session
     * will be deleted from the faculty's list of courses and sessions
     */
    public void sortFacultySessions()
    {
        //goes through the list of faculties
        for(int i = 0; i < facultyDirectory.size(); i++)
        {
            //goes through the list of sessions inside the faculty object
            for(int j = 0; j < facultyDirectory.get(i).getSessions().size(); j++)
            {
                //checks if the session can be scheduled if it can't then will proceed to remove the course and session from the list
                if(!(facultyDirectory.get(i).getSessions().get(j).canSchedule()))
                {
                    facultyDirectory.get(i).getCourses().remove(j);
                    facultyDirectory.get(i).getSessions().remove(j);
                }
            }
        }
    }

    /**
     * This method will output the following information to the unscheduledStudents.txt file:
     *      1. Outputs each student's full details (i.e. name, address, contact info)
     * @throws Exception : throws an file exception if the file cannot be found
     */
    public void unscheduledStudentsOutput() throws Exception
    {
        //creates a file variable
        File unscheduledStudentsOutput = new File("outputFiles/UnscheduledStudents.txt");
        
        //try with block
        try (
            PrintWriter output = new PrintWriter(unscheduledStudentsOutput);
        )
        {
            output.println("---------------------------------------------------------------------------");
            output.println("---------------------------UNSCHEDULED STUDENTS----------------------------");
            output.println("---------------------------------------------------------------------------\n");
            //goes through each student in the directory
            for(int i = 0; i < studentDirectory.size(); i++)
            {
                //checks if the student has no course to take
                if((studentDirectory.get(i).getCourses().isEmpty()))
                {
                    output.println("\n-----STUDENT #" + (i + 1) + "-----");
                    output.println(studentDirectory.get(i).toString() + "\n");
                    output.println("---------------------------------------------------------------------------\n");
                    unscheduledStudents++;
                }
                
            }
        }
    }

    /**
     * This method will print the following information to FacultyOutput.txt text file:
     *      1) Details of each faculty member (i.e. Name, address, contact info, etc)
     *      2) Full course detail of each course along with the session they are scheduled in
     *      3) Session ID and number of students in each session
     * @throws Exception : File exceptions
     */
    public void facultyOutput() throws Exception
    {
        //creates a file variable
        File facultyOutput = new File("outputFiles/FacultyOuput.txt");

        //try with code block
        try (
            PrintWriter output = new PrintWriter(facultyOutput);
        )
        {
            output.println("---------------------------------------------------------------------------");
            output.println("-------------------------------FACULTY LIST--------------------------------");
            output.println("---------------------------------------------------------------------------\n");

            //goes through the list of faculties
            for(int i = 0; i < facultyDirectory.size(); i++)
            {
                //if the faculty has a course scheduled
                if(!(facultyDirectory.get(i).getCourses().isEmpty()))
                {
                    output.println("\n-----FACULTY#" + (i + 1) + "------");
                    output.println(facultyDirectory.get(i).toString() + "\n");

                    for(int j = 0; j < facultyDirectory.get(i).getCourses().size(); j++)
                    {
                        output.print("-----COURSES#" + (j + 1) + "-----");
                        //prints the first course
                        output.println(facultyDirectory.get(i).getCourses().get(j).toString());

                        output.println("\n---SESSIONS---");
                        //prints the unique id of that particular course the student is in
                        output.println("Session ID: " + facultyDirectory.get(i).getSessions().get(j).getUniqueId());

                        output.println("Number of Students in the session above: " + facultyDirectory.get(i).getSessions().get(j).getStudents().size() + "\n");
                    }
                    output.println("--------------------------------------------------------------------\n");
                }
            }
        }
    }

    /**
     * This method will parse the courses that the faculty would like to teach. It will create a list of string which will contain the courses
     * wishlist for each faculty
     * @throws Exception : File exceptions
     */
    public void facultyWishlist() throws Exception
    {
       //creates a file object
       File wishlistFile = new File("inputFiles/facultyWishlist.txt");

       //try with code block
       try(
           Scanner input = new Scanner(wishlistFile);
       )
       {
           while(input.hasNext())
           {
            String courses = input.nextLine();

            try{
                input.nextLine();
             }
             catch(Exception e)
             {
                 //test
             }


            //adds the courses faculty wants to teach to the array list
            fWishlist.add(courses);
           }
       }
   }

   /**
    * Returns the total number of students
    * @return the number of unscheduled students
    */
   public int numberOfUnscheduledStudents()
   {
       return unscheduledStudents;
   }
}