package entity;

import java.io.Serializable;

public class ExtLoginEntity implements Serializable {

    private String lineid;
    private String extid;
    private String groupid;
    private String password;
    private String authcode;

    private ExtEntity extEntity;

    public String getLineid() {
        return lineid;
    }

    public void setLineid(String lineid) {
        this.lineid = lineid;
    }

    public String getExtid() {
        return extid;
    }

    public void setExtid(String extid) {
        this.extid = extid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public ExtEntity getExtEntity() {
        return extEntity;
    }

    public void setExtEntity(ExtEntity extEntity) {
        this.extEntity = extEntity;
    }
}
