package bank.model;

public class Project {
	private String projects;
    private String description;
    private String languages;
    private String status;
   

    public Project(String projects, String description, String languages, String status) {
        this.projects = projects;
        this.description = description;
        this.languages = languages;
        this.status = status;
        
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
