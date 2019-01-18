package dao;

public interface OMTransferDao {

    /**
     * 获取设备数字签名认证MD5字符串
     */
    String getSigString();

    /**
     * 分机配置
     */
    boolean setExt();

    /**
     * 分机组配置
     */
    boolean setExtGroup();

    /**
     * 连接：分机呼分机
     */
    boolean connectExtToExt(String param, int extid1, int extid2);

    /**
     * 强拆（挂断）
     */
    boolean clear(String param, int id);

    /**
     * 呼叫保持（保持）
     */
    boolean hold(String param, int id);

    /**
     * 呼叫接回（抓回）
     */
    boolean unhold(String param, int id);

    /**
     * 来电转分机队列
     */
    boolean queueExt(int visitorid, int extid);

    /**
     * 来电转分机组队列
     */
    boolean queueExtGroup(int visitorid, int groupid);


}
