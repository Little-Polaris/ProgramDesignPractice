//Created by MrQi on 2023/12/9.

public class Room {
    private String RName;
    private String RNum;
    private String RMemberCount;
    private String RArea;
    private String RPicDir;
    private String RUsage;
    private String RUserName;
    private String RUserId;
    private String RUserTel;
    private String RUserEmail;
    private String UseDate;
    private String StartTime;
    private String EndTime;
    private String RUserUsage;
    private String Status;
    public Room(){}
    public Room(String RName, String RNum, String RMemberCount, String RArea, String RPicDir, String RUsage, String RUserName, String RUserId, String RUserTel, String RUserEmail, String UseDate, String StartTime, String EndTime, String RUserUsage, String Status){
        this.RName = RName;
        this.RNum = RNum;
        this.RMemberCount = RMemberCount;
        this.RArea = RArea;
        this.RPicDir = RPicDir;
        this.RUsage = RUsage;
        this.RUserName = RUserName;
        this.RUserId = RUserId;
        this.RUserTel = RUserTel;
        this.RUserEmail = RUserEmail;
        this.UseDate = UseDate;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.RUserUsage = RUserUsage;
        this.Status = Status;
    }
    public String getRName() {
        return RName;
    }
    public String getRNum() {
        return RNum;
    }
    public String getRMemberCount() {
        return RMemberCount;
    }
    public String getRArea() {
        return RArea;
    }
    public String getRPicDir() {
        return RPicDir;
    }
    public String getRUsage() {
        return RUsage;
    }
    public String getRUserName() {
        return RUserName;
    }
    public String getRUserId() {
        return RUserId;
    }
    public String getRUserTel() {
        return RUserTel;
    }
    public String getRUserEmail() {
        return RUserEmail;
    }
    public String getUseDate() {
        return UseDate;
    }
    public String getStartTime() {
        return StartTime;
    }
    public String getEndTime() {
        return EndTime;
    }
    public String getRUserUsage() {
        return RUserUsage;
    }
    public String getStatus() {
        return Status;
    }
    public void setRName(String RName) {
        this.RName = RName;
    }
    public void setRNum(String RNum) {
        this.RNum = RNum;
    }
    public void setRMemberCount(String RMemberCount) {
        this.RMemberCount = RMemberCount;
    }
    public void setRArea(String RArea) {
        this.RArea = RArea;
    }
    public void setRPicDir(String RPicDir) {
        this.RPicDir = RPicDir;
    }
    public void setRUsage(String RUsage) {
        this.RUsage = RUsage;
    }
    public void setRUserName(String RUserName) {
        this.RUserName = RUserName;
    }
    public void setRUserId(String RUserId) {
        this.RUserId = RUserId;
    }
    public void setRUserTel(String RUserTel) {
        this.RUserTel = RUserTel;
    }
    public void setRUserEmail(String RUserEmail) {
        this.RUserEmail = RUserEmail;
    }
    public void setUseDate(String UseDate) {
        this.UseDate = UseDate;
    }
    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }
    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }
    public void setRUserUsage(String RUserUsage) {
        this.RUserUsage = RUserUsage;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }
}
