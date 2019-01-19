package dao;

/**
 * 这个interface里面的方法都是向OM设备发送 连接、挂断等命令 的方法
 */
public interface OMApiOrderDao {

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
    boolean connectExtToExt(String extid1, String extid2);

    /**
     * 连接：分机呼外部电话
     * @param extid 分机id
     * @param outerto 外部电话号码
     * @param trunkid 加上这个参数的意思是指定中继外呼
     */
    boolean connectExtToOuter(String extid, String outerto, String trunkid);

    /**
     * 强拆（挂断）
     * param可以是ext、visitor、outer
     *      分别是强拆分机、强拆来电、强拆去电
     */
    boolean clear(String param, String id);

    /**
     * 呼叫保持（保持）
     */
    boolean hold(String extid);

    /**
     * 呼叫接回（抓回）
     */
    boolean unhold(String id);

    /**
     * 来电转分机队列
     */
    boolean queueExt(String visitorid, String extid);

    /**
     * 来电转分机组队列
     */
    boolean queueExtGroup(String visitorid, String groupid);


}
