package dao;

public interface OMTransferDao {

    /**
     * 连接
     *
     */
    public boolean Connect();

    /**
     * 强拆（挂断）
     *
     */
    public boolean Clear(String param, int id);

    /**
     * 呼叫保持（保持）
     *
     */
    public boolean Hold(String param, int id);

    /**
     * 呼叫接回（抓回）
     *
     */
    public boolean Unhold(String param, int id);

    /**
     * 来电转分机队列
     *
     */
    public boolean QueueExt(int visitorid, int extid);

    /**
     * 来电转分机组队列
     *
     */
    public boolean QueueExtGroup(int visitorid, int groupid);


}
