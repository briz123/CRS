
public class Student extends User implements StudentMng {
	/*1. View all courses
	2. View all courses that are not FULL
	3. Register on a course (in this case the student must enter the course name, section, and
	student full name, the name will be added to the appropriate course)
	4. Withdraw from a course (in this case the student will be asked to enter her/his student
	name and the course, then the name of the student will be taken off from the given course’
	list)
	5. View all courses that the current student is being registered in
	6. Exit*/
	private String fn;
	private String ln;
	private String username;
	private String password;
	public Student(String f,String l) {
		this.username = "Student";
		this.password = "Student001";
		this.fn=f;
		this.ln=l;
		
	}
	@Override
	public void ViewAllCourses() {
		// TODO Auto-generated method stub
		Course course = new Course();
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			course = CourseRegistrationSystem.MasterList.get(i);
			
				System.out.println(course.toString());
			
		}
		
	}
	@Override
	public void ViewOpenCourses() {
		// TODO Auto-generated method stub
		System.out.println("Open Courses:");
		// TODO Auto-generated method stub
		Course course = new Course();
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			course = CourseRegistrationSystem.MasterList.get(i);
			if (course.status.equalsIgnoreCase("open")) {
				System.out.println(course.toString());
			}
		}
		
	}
	@Override
	public void Register(String id) {
		// TODO Auto-generated method stub
		//add student name to student_names
		//increase amount of enrolled students
		Course course = new Course();
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			course = CourseRegistrationSystem.MasterList.get(i);
			if (course.Course_ID.equalsIgnoreCase(id)) {
				course.addStudentName(this.fn+" "+this.ln);
				System.out.println("Student Registered");
			}
		}
	}
	@Override
	public void Withdraw(String id) {
		// TODO Auto-generated method stub
		//remove student name from the array list
		//we are assuming the student is already enrolled
		//decrease amount of enrolled students
		Course course = new Course();
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			course = CourseRegistrationSystem.MasterList.get(i);
			if (course.Course_ID.equalsIgnoreCase(id)) {
				course.deleteStudentName(this.fn+" "+this.ln);
				System.out.println("Withdrew From Course");
			}
		}
		
	}
	@Override
	public void ViewRegisteredCourses() {
		// TODO Auto-generated method stub
		//go through each course
		//if the course student array list has student names
		//then print out course name
		Course course = new Course();
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			course = CourseRegistrationSystem.MasterList.get(i);
			if (course.Student_Names.contains(fn+" "+ln)) {
				System.out.println(course.Course_Name);
			}
		}
	}

}
