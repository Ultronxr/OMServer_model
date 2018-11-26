package dao.impl;

import dao.OMTransferDao;

public class OMTransferDaoImpl implements OMTransferDao {

    /**
     * 连接
     *
     */
    @Override
    public boolean Connect(){
        return false;
    }

    /**
     * 强拆（挂断）
     *
     */
    @Override
    public boolean Clear(){
        return false;
    }

    /**
     * 呼叫保持（保持）
     *
     */
    @Override
    public boolean Hold(){
        return false;
    }

    /**
     * 呼叫接回（抓回）
     *
     */
    @Override
    public boolean Unhold(){
        return false;
    }

    /**
     * 来电转分机队列
     *
     */
    @Override
    public boolean QueueExt(int visitorid, int extid){
        return false;
    }

    /**
     * 来电转分机组队列
     *
     */
    @Override
    public boolean QueueExtGroup(int visitorid, int groupid){
        return false;
    }



}
