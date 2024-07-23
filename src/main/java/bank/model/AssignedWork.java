package bank.model;

public class AssignedWork {
	private String name;
	private String  projectname  ;
	public AssignedWork(String name, String  projectname) {
		this.name=name;
		this.projectname=projectname;
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
