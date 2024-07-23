package bank.model;

public class Work {
    private int id;
    private int employeeId;
    private String work;
    private String startTime;
    private String endTime;
    private String totalTime;

    public Work(int id, int employeeId, String work, String startTime, String endTime, String totalTime) {
        this.id = id;
        this.employeeId = employeeId;
        this.work = work;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTime = totalTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}
