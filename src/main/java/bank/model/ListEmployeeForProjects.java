package bank.model;

public class ListEmployeeForProjects {
	private int id;
	private String name;
	private String projectname;
	public  ListEmployeeForProjects(int id, String name, String projectname) {
		this.id=id;
		this.name=name;
		this.projectname=projectname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

}
