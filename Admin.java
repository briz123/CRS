import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
public class Admin extends User implements AdminMng {
	private String fn;
	private String ln;
	private String username;
	private String password;
	public Admin(String f,String l) {
		this.username = "Admin";
		this.password = "Admin001";
		this.fn=f;
		this.ln=l;
		
	}
	
	@Override
	public void ViewAllCourses() {
		//System.out.println(CourseRegistrationSystem.MasterList.toString());
		Course course = new Course();
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			course = CourseRegistrationSystem.MasterList.get(i);
			
				System.out.println(course.toString());
			
		}
	}
	@Override
	public void ViewFullCourse() {
		System.out.println("Full Courses:");
		// TODO Auto-generated method stub
		Course course = new Course();
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			course = CourseRegistrationSystem.MasterList.get(i);
			if (course.status.equalsIgnoreCase("full")) {
				System.out.println(course.toString());
			}
		}
	}
	@Override
	public void WritefileFullCourses() {
		// TODO Auto-generated method stub
		Course course = new Course();
		//generic writing to a file code from the internet
		//https://intellipaat.com/community/70327/java-writing-strings-to-a-csv-file
		try (PrintWriter writer = new PrintWriter(new File("FullCourses.csv"))) {
			StringBuilder stringBuilder = new StringBuilder();
			//go through each course on the list, and if status is full then add to file
			for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
				course = CourseRegistrationSystem.MasterList.get(i);
				if (course.status.equalsIgnoreCase("full")) {
					stringBuilder.append(course.toString());
					stringBuilder.append('\n');
				}
			}
			writer.write(stringBuilder.toString());

		    System.out.println("done!");
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());

			e.printStackTrace();
		}
		
	}
	@Override
	public void ViewStudentsForACourse(String id) {
		// TODO Auto-generated method stub
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			Course course = CourseRegistrationSystem.MasterList.get(i);
			if (course.getCourseID().equalsIgnoreCase(id)) {
				System.out.println(course.Student_Names.toString());
			}
		}
		
	}
	@Override
	public void ViewCoursesByStudent(String fn, String ln) {
		// TODO Auto-generated method stub
		System.out.println("Courses for this student:");
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			Course course = CourseRegistrationSystem.MasterList.get(i);
			if (course.Student_Names.contains(fn+" "+ln)) {
				
				System.out.println(course.toString());
			}
		}
		
	}
	@Override
	public void SortCourses() {
		// TODO Auto-generated method stub
		CourseRegistrationSystem.MasterList.sort(Comparator.comparing(Course::getCourseName));
		
	}
	@Override
	public void CreateCourse(String cn, String c_id, int max, int num_stu, ArrayList<String> names, String instruct,
			int section_num, String local) {
		// TODO Auto-generated method stub
		Course course = new Course(cn, c_id,  max,num_stu, names, instruct,
			section_num, local);
		CourseRegistrationSystem.MasterList.add(course);
	}
	@Override
	public void DeleteCourse(String id) {
		// TODO Auto-generated method stub
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			Course course = CourseRegistrationSystem.MasterList.get(i);
			if (course.getCourseID().equalsIgnoreCase(id)) {
				CourseRegistrationSystem.MasterList.remove(course);
				System.out.println("Deleted");
			}
		}
		
	}
	@Override
	public void EditCourse(String id) {
		// TODO Auto-generated method stub
		//they can edit Max, instructor , or location
		Scanner input = new Scanner(System.in);
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			Course course = CourseRegistrationSystem.MasterList.get(i);
			if (course.getCourseID().equalsIgnoreCase(id)) {
				System.out.println("Edit Options:");
				System.out.println("1.Max\n 2.Instructor\n 3.Location\n");
				int choice = input.nextInt();
				switch(choice){
					case 1:
						System.out.println("What is the new max amount of students?:");
						int new_max = input.nextInt();
						CourseRegistrationSystem.MasterList.get(i).setCourseMax(new_max);                                                 
						break;
					case 2:
						System.out.println("Who is the new instructor?:");
						String new_ins = input.next();
						CourseRegistrationSystem.MasterList.get(i).setCourseInstructor(new_ins);    
						break;
					case 3:
						System.out.println("What is the new location?:");
						String new_lo = input.next();
						CourseRegistrationSystem.MasterList.get(i).setCourseLocal(new_lo);; 
						break;
				}
				System.out.println("Changes have been made to the system.");
			}
		}
		
	}
	@Override
	public void DisplayInfo(String id) {
		// TODO Auto-generated method stub
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			Course course = CourseRegistrationSystem.MasterList.get(i);
			if (course.getCourseID().equalsIgnoreCase(id)) {
				System.out.println(course.toString());
			}
		}
		
	}

	@Override
	public void RegisterStudent(String fn, String ln,String id) {
		// TODO Auto-generated method stub
		Course course = new Course();
		for (int i = 0; i<CourseRegistrationSystem.MasterList.size(); i++) {
			course = CourseRegistrationSystem.MasterList.get(i);
			if (course.Course_ID.equalsIgnoreCase(id)&& course.status.equalsIgnoreCase("open")) {
				course.addStudentName(fn+" "+ln);
				System.out.println("Student Registered");
			}
			if(course.Num_Registered_Students >= course.getCourseMax()) {
				course.status = "full";
				System.out.println("Course is full");
			}
		}
		
		
	}
}
