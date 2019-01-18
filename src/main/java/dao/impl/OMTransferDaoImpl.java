package dao.impl;

import dao.OMTransferDao;
import utils.GetCurrentTime;
import utils.MD5Hash;
import utils.OMConfig;
import utils.RandomNumber;

public class OMTransferDaoImpl implements OMTransferDao {

    @Override
    public String getSigString(){
        String xmlHead = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
        String apiPwdReceive = OMConfig.getApiPwdReceive();
        String nonce = RandomNumber.simpleStringRandNum();
        String timestamp = GetCurrentTime.sysTimestamp();
        String signature = MD5Hash.stringToMd5LowerCase(apiPwdReceive+nonce+timestamp);

        String authStr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Auth>\n" +
                "    <Timestamp>"+timestamp+"</Timestamp>\n" +
                "    <nonce>"+nonce+"</nonce>\n" +
                "    <Signature>"+signature+"</Signature>\n" +
                "</Auth>\n";
        return authStr;
    }

    /**
     * 连接：分机呼分机
     *
     */
    @Override
    public boolean connectExtToExt(String param, int extid1, int extid2){

        String xmlStr = getSigString() +
                "<Transfer attribute=\"Connect\">\n" +
                "    <ext id=\""+extid1+"\"/>\n" +
                "    <ext id=\""+extid2+"\"/>\n" +
                "</Transfer>";
        OMTransferBase.omTransferBase("Connect 连接：分机呼分机", xmlStr);

        return true;
    }

    /**
     * 强拆（挂断）
     *
     */
    @Override
    public boolean clear(String param, int id){

        String xmlStr = getSigString() +
                "<Control attribute=\"Clear\">\n" +
                "    <"+param+" id=\""+id+"\"/>\n" +
                "</Control>";
        OMTransferBase.omTransferBase("Clear 强拆（挂断）", xmlStr);

        return true;
    }

    /**
     * 呼叫保持（保持）
     *
     */
    @Override
    public boolean hold(String param, int id){
        String xmlStr = getSigString() +
                "<Control attribute=\"Hold\">\n" +
                "    <ext id=\""+id+"\"/>\n" +
                "</Control>";
        OMTransferBase.omTransferBase("Hold 呼叫保持（保持）", xmlStr);

        return true;
    }

    /**
     * 呼叫接回（抓回）
     *
     */
    @Override
    public boolean unhold(String param, int id){
        String xmlStr = getSigString() +
                "<Control attribute=\"Unhold\">\n" +
                "    <ext id=\""+id+"\"/>\n" +
                "</Control>";
        OMTransferBase.omTransferBase("Unhold 呼叫接回（抓回）", xmlStr);

        return true;
    }

    /**
     * 来电转分机队列
     *
     */
    @Override
    public boolean queueExt(int visitorid, int extid){

        String xmlStr = getSigString() +
                "<Transfer attribute=\"Queue\">\n" +
                "  <visitor id=\""+visitorid+"\"/>\n" +
                "    <ext id=\""+extid+"\"/>\n" +
                "</Transfer>";
        OMTransferBase.omTransferBase("QueueExt 来电转分机队列", xmlStr);

        return true;
    }

    /**
     * 来电转分机组队列
     *
     */
    @Override
    public boolean queueExtGroup(int visitorid, int groupid){
        String xmlStr = getSigString() +
                "<Transfer attribute=\"Queue\">\n" +
                "  <visitor id=\""+visitorid+"\"/>\n" +
                "  <group id=\""+groupid+"\"/>\n" +
                "</Transfer>";
        OMTransferBase.omTransferBase("QueueExtGroup 来电转分机组队列", xmlStr);

        return true;
    }

}
