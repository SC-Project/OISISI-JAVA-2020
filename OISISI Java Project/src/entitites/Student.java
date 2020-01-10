package entitites;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private String name;
	private String lastName;
	private String date;
	private String adress;
	private String telephone;
	private String email;
	private String index;
	private Integer startDate;//godina upisa
	private Integer studentYear;
	private String status;
	private Double avgMark;
	private List<String> subjects;
	
	public Student(){
		subjects = new ArrayList<String>();
	}
	
	public Student(String name, String lastName, String date, String adress, String telephone, String email,
			String index, Integer startDate, Integer studentYear, String status, Double avgMark) {
		this();
		this.name = name;
		this.lastName = lastName;
		this.date = date;
		this.adress = adress;
		this.telephone = telephone;
		this.email = email;
		this.index = index;
		this.startDate = startDate;
		this.studentYear = studentYear;
		this.status = status;
		this.avgMark = avgMark;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Integer getStartDate() {
		return startDate;
	}

	public void setStartDate(Integer startDate) {
		this.startDate = startDate;
	}

	public Integer getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(Integer studentYear) {
		this.studentYear = studentYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAvgMark() {
		return avgMark;
	}

	public void setAvgMark(Double avgMark) {
		this.avgMark = avgMark;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", lastName=" + lastName + ", date=" + date + ", adress=" + adress
				+ ", telephone=" + telephone + ", email=" + email + ", index=" + index + ", startDate=" + startDate
				+ ", studentYear=" + studentYear + ", status=" + status + ", avgMark=" + avgMark + "]";
	}
}
