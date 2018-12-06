package dao.impl;

import dao.ExtJdbcDao;
import entity.ExtEntity;
import entity.ExtLoginEntity;
import utils.databases.MyMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExtJdbcDaoImpl implements ExtJdbcDao {

    @Override
    public ExtLoginEntity getExtByExtid(String extid){
        String sql = "SELECT * FROM ext_infos_table WHERE extid=?";

        Connection conn = MyMysql.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ExtEntity extEntity = null;
        ExtLoginEntity extLoginEntity = null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, extid);
            rs = ps.executeQuery();
            while(rs.next()){
                extEntity = new ExtEntity();
                extLoginEntity = new ExtLoginEntity();
                extLoginEntity.setLineid(rs.getString("lineid"));
                extLoginEntity.setExtid(rs.getString("extid"));
                extLoginEntity.setGroupid(rs.getString("groupid"));
                extLoginEntity.setPassword(rs.getString("password"));
                extLoginEntity.setAuthcode(rs.getString("authcode"));
                extLoginEntity.setExtEntity(extEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyMysql.closeConnection(rs, ps, conn);
        }

        return extLoginEntity;
    }

}
