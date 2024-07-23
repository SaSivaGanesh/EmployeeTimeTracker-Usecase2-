package bank.model;

public class Employee {
	private String name;
	private String skill;
	private String mobile;
	private String email;
	public Employee(String name, String skill, String mobile, String email) {
		this.name=name;
		this.skill=skill;
		this.mobile=mobile;
		this.email=email;
		
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
