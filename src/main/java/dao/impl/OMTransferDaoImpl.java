package dao.impl;

import dao.OMTransferDao;

public class OMTransferDaoImpl implements OMTransferDao {

    /**
     * 连接：分机呼分机
     *
     */
    @Override
    public boolean ConnectExtToExt(String param, int extid1, int extid2){

        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Transfer attribute=\"Connect\">\n" +
                "    <ext id=\""+extid1+"\"/>\n" +
                "    <ext id=\""+extid2+"\"/>\n" +
                "</Transfer>";
        OMTransferBase.OMTransferBase("Connect 连接：分机呼分机", xmlStr);

        return true;
    }

    /**
     * 强拆（挂断）
     *
     */
    @Override
    public boolean Clear(String param, int id){

        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Control attribute=\"Clear\">\n" +
                "    <"+param+" id=\""+id+"\"/>\n" +
                "</Control>";
        OMTransferBase.OMTransferBase("Clear 强拆（挂断）", xmlStr);

        return true;
    }

    /**
     * 呼叫保持（保持）
     *
     */
    @Override
    public boolean Hold(String param, int id){
        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Control attribute=\"Hold\">\n" +
                "    <ext id=\""+id+"\"/>\n" +
                "</Control>";
        OMTransferBase.OMTransferBase("Hold 呼叫保持（保持）", xmlStr);

        return true;
    }

    /**
     * 呼叫接回（抓回）
     *
     */
    @Override
    public boolean Unhold(String param, int id){
        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Control attribute=\"Unhold\">\n" +
                "    <ext id=\""+id+"\"/>\n" +
                "</Control>";
        OMTransferBase.OMTransferBase("Unhold 呼叫接回（抓回）", xmlStr);

        return true;
    }

    /**
     * 来电转分机队列
     *
     */
    @Override
    public boolean QueueExt(int visitorid, int extid){

        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Transfer attribute=\"Queue\">\n" +
                "  <visitor id=\""+visitorid+"\"/>\n" +
                "    <ext id=\""+extid+"\"/>\n" +
                "</Transfer>";
        OMTransferBase.OMTransferBase("QueueExt 来电转分机队列", xmlStr);

        return true;
    }

    /**
     * 来电转分机组队列
     *
     */
    @Override
    public boolean QueueExtGroup(int visitorid, int groupid){
        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<Transfer attribute=\"Queue\">\n" +
                "  <visitor id=\""+visitorid+"\"/>\n" +
                "  <group id=\""+groupid+"\"/>\n" +
                "</Transfer>";
        OMTransferBase.OMTransferBase("QueueExtGroup 来电转分机组队列", xmlStr);

        return true;
    }

}
