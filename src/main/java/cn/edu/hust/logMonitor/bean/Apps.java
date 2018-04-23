package cn.edu.hust.logMonitor.bean;

import java.sql.Timestamp;

public class Apps {
    private int id;
    private String name;
    private String desc;
    private int isOnline;
    private int typeId;
    private Timestamp createDate;
    private Timestamp udpateDate;
    private String createUser;
    private String udpateUser;

    public Apps() {
    }

    public Apps(int id, String name, String desc, int isOnline, int typeId, Timestamp createDate, Timestamp udpateDate, String createUser, String udpateUser) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.isOnline = isOnline;
        this.typeId = typeId;
        this.createDate = createDate;
        this.udpateDate = udpateDate;
        this.createUser = createUser;
        this.udpateUser = udpateUser;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUdpateDate() {
        return udpateDate;
    }

    public void setUdpateDate(Timestamp udpateDate) {
        this.udpateDate = udpateDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUdpateUser() {
        return udpateUser;
    }

    public void setUdpateUser(String udpateUser) {
        this.udpateUser = udpateUser;
    }

    @Override
    public String toString() {
        return "Apps{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", isOnline=" + isOnline +
                ", typeId=" + typeId +
                ", createDate=" + createDate +
                ", udpateDate=" + udpateDate +
                ", createUser='" + createUser + '\'' +
                ", udpateUser='" + udpateUser + '\'' +
                '}';
    }
}
