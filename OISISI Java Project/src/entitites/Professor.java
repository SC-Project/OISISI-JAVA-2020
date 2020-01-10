package entitites;

import java.util.ArrayList;
import java.util.List;

public class Professor {
	
	private String name;
	private String lastName;
	private String date;
	private String adress;
	private String telephone;
	private String email;
	private String officeAdress;
	private String idNumber;
	private String titel;
	private String profession;
	private List<String> subjects;
	
	public Professor(){
		subjects = new ArrayList<String>();
	}

	public Professor(String name, String lastName, String date, String adress, String telephone, String email,
			String officeAdress, String idNumber, String titel, String profession) {
		this();
		this.name = name;
		this.lastName = lastName;
		this.date = date;
		this.adress = adress;
		this.telephone = telephone;
		this.email = email;
		this.officeAdress = officeAdress;
		this.idNumber = idNumber;
		this.titel = titel;
		this.profession = profession;
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

	public String getOfficeAdress() {
		return officeAdress;
	}

	public void setOfficeAdress(String officeAdress) {
		this.officeAdress = officeAdress;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Professor [name=" + name + ", lastName=" + lastName + ", date=" + date + ", adress=" + adress
				+ ", telephone=" + telephone + ", email=" + email + ", officeAdress=" + officeAdress + ", idNumber="
				+ idNumber + ", titel=" + titel + ", profession=" + profession + "]";
	}
	
}
