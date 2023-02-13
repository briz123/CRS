import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
public class CourseRegistrationSystem {
	
	//dont know why but course list needs to be a public static or else errors pop up in student and admin
	//i guess it has to be global and not local so each thing can access it
	public static ArrayList<Course> MasterList = new ArrayList<Course>();
	//https://www.javatpoint.com/how-to-read-csv-file-in-java
	public static void main(String [] args) throws FileNotFoundException {
		//ArrayList<Course> MasterList = new ArrayList<Course>();
		File courseFile = new File("courseregistrationsystem.ser");
		if (!courseFile.exists()) {
			String line = "";  
			try {  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("MyUniversityCourses.csv"));  
			//SKIP FIRST LINE
			//RAN into a LOT of problems bc didnt realize this
			//https://www.webm.in/skip-first-line-with-filereader-java/
			br.readLine();
			while ((line = br.readLine()) != null){  
				String[] entries = line.split(",");
				//assign variables
				String cn = entries[0];
				String cid = entries[1];
				//String remove_max = entries[2].replaceAll("\\s", "");
				//System.out.println(entries[2]);
				int max = Integer.parseInt(entries[2]);
				int num_students = Integer.parseInt(entries[3]);
				ArrayList<String> stu = null;
				String instruct = entries[5];
				int section = Integer.parseInt(entries[6]);
				String local = entries[7];
				//Add each course to the masterlist
				Course course = new Course( cn,cid,max,num_students,stu,instruct,section,local);
				MasterList.add(course);
			}
			br.close();
			}   
			catch (IOException e){
				System.out.println(e.getMessage());
				e.printStackTrace();  
			}
		}
		else {
			//read info from the serialized file
			//so deserialize
			//ArrayList<Course> list = null;
			//https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/

			try (FileInputStream fis = new FileInputStream("courseregistrationsystem.ser");
			    ObjectInputStream ois = new ObjectInputStream(fis);) {

			  ArrayList<Course> readObject = (ArrayList<Course>) ois.readObject();
			MasterList = readObject;
			} catch (IOException ioe) {
			  ioe.printStackTrace();
			} catch (ClassNotFoundException c) {
			  System.out.println("Class not found");
			  c.printStackTrace();
			}

			//Verify list data
			/*for (Course name : MasterList) {
			  System.out.println(name);
			}*/
		}
		Login();
		
	}
	public static void exitStrategy() {
		//https://www.geeksforgeeks.org/how-to-serialize-arraylist-in-java/
		try {
            // an OutputStream file
            // "namesListData" is
            // created
            FileOutputStream fos
                = new FileOutputStream("courseregistrationsystem.ser");
  
            // an ObjectOutputStream object is
            // created on the FileOutputStream
            // object
            ObjectOutputStream oos
                = new ObjectOutputStream(fos);
  
            // calling the writeObject()
            // method of the
            // ObjectOutputStream on the
            // OutputStream file "namesList"
            oos.writeObject(MasterList);
  
            // close the ObjectOutputStream
            oos.close();
  
            // close the OutputStream file
            fos.close();
  
            System.out.println("System serialized");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
		finally {
			System.exit(0);
		}
		
	}
	public static void Login() {
		Scanner input= new Scanner(System.in);
		System.out.println("1.Admin or 2.Student or 3.Exit Program");
		String type;
		switch(input.nextInt()){
		case 1:
			type = "Admin";
			break;
		case 2:
			type = "Student";
			break;
		case 3:
			exitStrategy();
			break;
		}
		String user;
		String pass;
		System.out.println("Enter Username: ");
		user = input.next();
		System.out.println("Enter Password: ");
		pass = input.next();
		boolean admin = false;
		boolean student = false;
		if(user.equalsIgnoreCase("Admin")&&pass.equalsIgnoreCase("Admin001")) {
			admin = true;
		}
		else if(user.equalsIgnoreCase("Student")&&pass.equalsIgnoreCase("Student001")) {
			student = true;
		}
		else {
			System.out.println("Invalid login information.");
		}
		System.out.println("Enter Firstname: ");
		String first_n = input.next();
		System.out.println("Enter Lastname: ");
		String last_n = input.next();
		if (admin)
			adminMenu(first_n,last_n);
		else if (student)
			studentMenu(first_n,last_n);
		
		
		//maybe do full system exit here and serialize her
		//so the login menu would contain the real exit strategy
		
		
		
	}
	public static void adminMenu(String fn, String ln) {
		Admin admin_user= new Admin(fn,ln);
		Scanner input = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			System.out.println("-------------------------");
			System.out.println("1.Course Managment \t 2.Reports");
			int choice  = input.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Course Managment:");
				System.out.println("-------------------------");
				System.out.println("\n 1.Create a new course\n 2.Delete a course\n 3.Edit a course\n 4.Display information for a given course\n 5.Register a student\n 6.Exit");
				int c_choice = input.nextInt();
				switch(c_choice){
					case 1:
						//create course
						System.out.println("Course Name:");
						String cn = input.next();
						System.out.println("Course ID:");
						String cid = input.next();
						System.out.println("Course Max:");
						int m = input.nextInt();
						System.out.println("Number of Students:");
						int n = input.nextInt();
						System.out.println("Course Instructor:");
						String in = input.next();
						System.out.println("Course Section:");
						int s = input.nextInt();
						System.out.println("Course Location:");
						String l = input.next();
						//cn,cid,max,num_students,stu,instruct,section,local
						admin_user.CreateCourse(cn, cid, m, n, null, in, s, l);
						System.out.println("Course added");
						break;
					case 2:
						//delete course
						//public void DeleteCourse(String id);
						System.out.println("What is the Course ID");
						String id = input.next();
						admin_user.DeleteCourse(id);
						System.out.println("Course Deleted");
						break;
					case 3:
						//edit
						//	public void EditCourse(String id);
						System.out.println("What is the Course ID");
						String c_editid = input.next();
						admin_user.EditCourse(c_editid);
						break;
					case 4:
						//display cousre info
						System.out.println("What is the Course ID");
						String c_displayid = input.next();
						
						admin_user.DisplayInfo(c_displayid);
						break;
					case 5:
						//register student
						System.out.println("What is the Course ID:");
						String c_regid = input.next();
						System.out.println("What is the first name:");
						String c_fn = input.next();
						System.out.println("What is the last name:");
						String c_ln = input.next();
						admin_user.RegisterStudent(c_fn, c_ln, c_regid);
						break;
					case 6:
						Login();
						loop = false;
						break;
				}
				//do later
				break;
			case 2:
				System.out.println("Reports:");
				System.out.println("-------------------------");
				System.out.println("\n 1.View all courses\n 2.View all courses that are FULL\n 3.Write to a file the list of course that are Full\n 4.View the names of the students being registered in a specific course\n 5.View the list of courses that a given student is being registered on\n 6.Sort\n 7.Exit");
				//System.out.println("Brizen, work on this later.");
				int r_choice = input.nextInt();
				switch(r_choice) {
				case 1:
					//view course
					admin_user.ViewAllCourses();
					break;
				case 2:
					//view full
					admin_user.ViewFullCourse();
					break;
				case 3:
					//write to list
					admin_user.WritefileFullCourses();
					break;
				case 4:
					//view registered students
					System.out.println("Course ID:");
					String c_id = input.next();
					admin_user.ViewStudentsForACourse(c_id);
					break;
				case 5:
					//view cousres for a specific student
					System.out.println("First Name:");
					String reg_fn = input.next();
					System.out.println("Last Name:");
					String reg_ln = input.next();
					admin_user.ViewCoursesByStudent(reg_fn, reg_ln);
					break;
				case 6:
					//sort
					admin_user.SortCourses();
					System.out.println("Courses Sorted");
					break;
				case 7:
					Login();
					loop = false;
					break;
				}
				break;
			}
		}
	}
	public static void studentMenu(String fn,String ln) {
		Student student_user= new Student(fn,ln);
		Scanner input = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			System.out.println("-------------------------");
			System.out.println("Course Managment");
			System.out.println("-------------------------");
			System.out.println("\n 1.View All Courses\n 2.View All Courses that are not Full\n 3.Register on a Course\n 4.Withdraw from a Course\n 5.View all Courses you are currently registered in\n 6.Exit");
			int choice  = input.nextInt();
			switch(choice) {
			case 1:
				student_user.ViewAllCourses();
				break;
			case 2:
				student_user.ViewOpenCourses();
				break;
			case 3:
				//ask id for what class they would like to register
				System.out.println("Register:");
				System.out.println("---------------------");
				System.out.println("Course ID:");
				String register_id = input.next();
				student_user.Register(register_id);
				
				break;
			case 4:
				//ask id for what class they would like to register
				System.out.println("Withdraw:");
				System.out.println("---------------------");
				System.out.println("Course ID:");
				String withdraw_id = input.next();
				student_user.Withdraw(withdraw_id);
				break;
			case 5:
				student_user.ViewRegisteredCourses();
				break;
			case 6:
				Login();
				loop=false;
				break;
			}
		}
		//Gameplan:
		//finish up the edit method in admin
		//do case 3 and 4 in student
		//do all the cases for admin
		//add an opyion to exit program in login menu
		//you could make login  menu a boolean
		//then in the main if its false you can serialize and system exit
		//you need to add deserialization to the main method
		//figure out how to serialize the course and students
		//
				
	}
	
	
}
