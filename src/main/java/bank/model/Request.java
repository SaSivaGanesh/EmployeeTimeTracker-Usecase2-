package bank.model;



public class Request {
    private String project;
    private String name;
    private String id;
    private String skill;

    public Request(String project, String name, String id, String skill) {
        this.project = project;
        this.name = name;
        this.id = id;
        this.skill = skill;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
