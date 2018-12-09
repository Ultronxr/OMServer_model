package dao;

import entity.__QueryEntity;

public interface __MQOperateDao {

    public boolean transferToMQ(__QueryEntity queryEntity);

}
