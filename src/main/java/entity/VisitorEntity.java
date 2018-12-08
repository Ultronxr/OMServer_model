package entity;

import java.io.Serializable;

public class VisitorEntity implements Serializable {
    private int id; //来电编号
    private String from; //电话号码
    private String to; //中继线
    private int callid; //没有实际作用的参数，但是设备需要
    private int extid; //转向分机编号
    private String outer; //转向外部电话的号码
    private String state; //通话状态

    private String nick; //称呼
    private String site; //归属

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getCallid() {
        return callid;
    }

    public void setCallid(int callid) {
        this.callid = callid;
    }

    public int getExtid() {
        return extid;
    }

    public void setExtid(int extid) {
        this.extid = extid;
    }

    public String getOuter() {
        return outer;
    }

    public void setOuter(String outer) {
        this.outer = outer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
