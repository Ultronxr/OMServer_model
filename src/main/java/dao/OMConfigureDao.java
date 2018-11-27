package dao;

/**
 * 检测到OM系统启动时，执行内部的函数，对系统进行配置
 */
public interface OMConfigureDao {

    /**
     * 分机配置
     * @return boolean代表是否配置成功
     */
    public boolean setExt();

}
