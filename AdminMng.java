import java.util.ArrayList;

public interface AdminMng {
	//Reports;
	/*.View all courses (for every course the admin should be able to see the list of course name,
	course id, number of students registered, and the maximum number of students allowed to
	be registered)*/
	public void ViewAllCourses();
	//.View all courses that are FULL (reached the maximum number of students) 
	public void ViewFullCourse();
	//Write to a file the list of course that are Full
	public void WritefileFullCourses();
	//View the names of the students being registered in a specific course
	public void ViewStudentsForACourse(String id);
	/*View the list of courses that a given student is being registered on (given a student
	first name and last name the system shall display all the courses that students is being
	registered in)*/
	public void ViewCoursesByStudent(String fn,String ln);
	//Sort courses based on the current number of student registers
	public void SortCourses();
	//Course Management:
	//1.Create a new course
	public void CreateCourse(String cn,String c_id,int max,int num_stu,ArrayList<String> names,String instruct,int section_num,String local);
	//Delete a course
	
	//Edit a course (this will allow the admin to edit any information on the course except for
	//course ID and name)
	public void EditCourse(String id);
	//4.Display information for a given course (by course ID)
	public void DisplayInfo(String id);
	/*5.Register a student (this option will allow the admin to add a student without assigning to
	a course check Req 11 for student’s information – Hint: You might need to have an ArrayList of
	Students where you store Student objects)*/
	public void DeleteCourse(String id);
	public void RegisterStudent(String fn,String ln,String id);
}
