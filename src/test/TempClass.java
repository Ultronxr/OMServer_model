import java.io.Serializable;

public class TempClass implements Serializable {
    private String msg;

    public TempClass(){
        msg = "dsafsa";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString(){
        return "msg="+msg;
    }
}
