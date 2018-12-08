package dao;

import entity.__QueryEntity;

public interface MQOperateDao {

    public boolean transferToMQ(__QueryEntity queryEntity);

}
