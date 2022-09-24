import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Employee {
	private int id;
	private String name;
	private String email;
	private String department;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date joinedDate;
	private boolean trainningActivity;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the deparment
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param deparment the deparment to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * @return the joinedDate
	 */
	public Date getJoinedDate() {
		return joinedDate;
	}
	/**
	 * @param joinedDate the joinedDate to set
	 */
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	/**
	 * @return the trainningActivity
	 */
	public boolean isTrainningActivity() {
		return trainningActivity;
	}
	/**
	 * @param trainningActivity the trainningActivity to set
	 */
	public void setTrainningActivity(boolean trainningActivity) {
		this.trainningActivity = trainningActivity;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", department=" + department
				+ ", joinedDate=" + joinedDate + ", trainningActivity=" + trainningActivity + "]";
	}
	//SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

}
