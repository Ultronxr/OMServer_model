package dao;

import entity.ExtLoginEntity;

public interface ExtJdbcDao {

    /**
     * 从数据库中获取Ext分机的相关信息
     */
    public ExtLoginEntity getExtByExtid(String extid);

}
