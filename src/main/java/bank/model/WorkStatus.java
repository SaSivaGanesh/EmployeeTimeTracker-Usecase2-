package bank.model;

public class WorkStatus {
    private int employeeId;
    private String employeeName;
    private String work;
    private String totalTime;
    private java.sql.Timestamp startTime;
    private java.sql.Timestamp endTime;

    // Constructor
    public WorkStatus(int employeeId, String employeeName, String work, String totalTime, java.sql.Timestamp startTime, java.sql.Timestamp endTime) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.work = work;
        this.totalTime = totalTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getWork() { return work; }
    public void setWork(String work) { this.work = work; }
    public String getTotalTime() { return totalTime; }
    public void setTotalTime(String totalTime) { this.totalTime = totalTime; }
    public java.sql.Timestamp getStartTime() { return startTime; }
    public void setStartTime(java.sql.Timestamp startTime) { this.startTime = startTime; }
    public java.sql.Timestamp getEndTime() { return endTime; }
    public void setEndTime(java.sql.Timestamp endTime) { this.endTime = endTime; }
}
