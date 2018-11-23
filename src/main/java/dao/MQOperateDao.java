package dao;

import entity.QueryEntity;

public interface MQOperateDao {

    public boolean transferToMQ(QueryEntity queryEntity);

}
