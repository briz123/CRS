import java.io.Serializable;
import java.util.ArrayList;
//@Brizen Britt
public class Course implements Serializable {
	//Course name, course id, maximum number of students registered in the course, current number of
	//registered students, a list of names of students being registered in the given course, course instructor,
	//course section number, course location.
	
	
	//figure out deserialization later just focus on building course class
	
	public  String Course_Name;
	public  String Course_ID;
	public  int Max;
	public  int Num_Registered_Students;
	public  ArrayList<String> Student_Names;
	public  String Instructor;
	public  int Section_Number;
	public  String Location;
	public String status;
	//no arg constructor
	public Course() {
	}
	public Course(String cn,String c_id,int max,int num_stu,ArrayList<String> names,String instruct,int section_num,String local) {
		this.Course_ID=c_id;
		this.Course_Name=cn;
		this.Max=max;
		this.Num_Registered_Students=num_stu;
		this.Student_Names=names;
		this.Instructor=instruct;
		this.Section_Number=section_num;
		this.Location=local;
		this.status = "open";
		if(this.Num_Registered_Students>=this.Max) 
			this.status = "full";
		this.Student_Names = new ArrayList<String>();
	}
	//add/remove a student from the list of kids in the class
	public void addStudentName(String n) {
		if (!this.status.equalsIgnoreCase("full")){
			Student_Names.add(n);
			//so every time u have a student u have to count it
			Num_Registered_Students++;
		}
	}
	
	public void deleteStudentName(String n) {
		Student_Names.remove(n);
		Num_Registered_Students--;
	}
	//getters:
	public String getCourseName() {
		return this.Course_Name;
	}
	public String getCourseID() {
		return this.Course_ID;
	}
	public int getCourseMax() {
		return this.Max;
	}
	public int getCourseNumRegStudents() {
		return this.Num_Registered_Students;
	}
	public ArrayList<String> getCourseStudentNames() {
		return this.Student_Names;
	}
	public String getCourseInstructor() {
		return this.Instructor;
	}
	public int getCourseSectionNum() {
		return this.Section_Number;
	}
	public String getCourseLocal() {
		return this.Location;
	}
	//setters:
	public void setCourseName(String n) {
		this.Course_Name=n;
	}
	public void setCourseID(String id) {
		this.Course_ID=id;
	}	
	public void setCourseMax(int m) {
		this.Max=m;
	}
	public void setCourseNumRegStudents(int num) {
		this.Num_Registered_Students=num;
	}
	public void capacityIssue() {
		if(this.Num_Registered_Students>=this.Max) {
			this.status = "full";
			System.out.println("Class is full");
		}
	}
	//This is a little more complicated do it later. User should be able to change a students name in the arraylist
	/*public void setCourseStudentNames(int num) {
		=num;
	}*/
	public void setCourseInstructor(String na) {
		this.Instructor=na;
	}
	public void setCourseSectionNum(int numb) {
		this.Section_Number=numb;
	}
	public void setCourseLocal(String lo) {
		this.Location=lo;
	}
	//course info
	@Override
	public String toString() {
		return this.Course_Name + "\t"+ this.Course_ID + "\t" + this.Max + "\t" + this.Num_Registered_Students + "\t" + this.Section_Number+ "\t" + this.Instructor + "\t" + this.Location;
	}
	
		
}
