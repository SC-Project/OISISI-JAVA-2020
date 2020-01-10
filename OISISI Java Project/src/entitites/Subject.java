package entitites;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	
	private String subjectId;
	private String name;
	private Integer semesterNumber;
	private Integer studentYear;
	private String professor;
	private List<String> students;
	
	public Subject(){
		students = new ArrayList<String>();
	}

	public Subject(String subjectId, String name, Integer semesterNumber, Integer studentYear, String professor) {
		this();
		this.subjectId = subjectId;
		this.name = name;
		this.semesterNumber = semesterNumber;
		this.studentYear = studentYear;
		this.professor = professor;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSemesterNumber() {
		return semesterNumber;
	}

	public void setSemesterNumber(Integer semesterNumber) {
		this.semesterNumber = semesterNumber;
	}

	public Integer getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(Integer studentYear) {
		this.studentYear = studentYear;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public List<String> getStudents() {
		return students;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", name=" + name + ", semesterNumber=" + semesterNumber
				+ ", studentYear=" + studentYear + ", professor=" + professor + "]";
	}

}
