package cn.edu.hust.logMonitor.bean;

import java.sql.Timestamp;

public class AppType {
    private int id;
    private String name;
    private Timestamp createDate;
    private Timestamp udpateDate;

    public AppType(int id, String name, Timestamp createDate, Timestamp udpateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.udpateDate = udpateDate;
    }

    public AppType() {
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

    @Override
    public String toString() {
        return "AppType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", udpateDate=" + udpateDate +
                '}';
    }
}
