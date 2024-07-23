package bank.model;

public class Projects {
    private String projects;
    private String description;
    private String languages;
    private String status;
    private String manager_name;

    public Projects(String projects, String description, String languages, String status, String manager_name) {
        this.projects = projects;
        this.description = description;
        this.languages = languages;
        this.status = status;
        this.manager_name = manager_name;
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

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }
}
