
public class User {
	//Common methods for both admin and user:
	/*1.View all courses (for every course the admin should be able to see the list of course name,
	course id, number of students registered, and the maximum number of students allowed to
	be registered)*/
	
	private String username;
	private String password;
	private String fn;
	private String ln;
	
	public User() {
		
	}
	public String getUserName() {
		return this.username;
	}
	public String getPassWord() {
		return this.password;
	}
	public String getFirstName() {
		return this.fn;
	}
	public String getLastName() {
		return this.ln;
	}
	
	//print user info
	public void printUserInfo() {
		System.out.println("User Info:");
		System.out.println(username);
		System.out.println(password);
		System.out.println(fn);
		System.out.println(ln);
	}
}
